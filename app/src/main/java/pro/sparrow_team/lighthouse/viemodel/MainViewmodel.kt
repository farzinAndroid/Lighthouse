package pro.sparrow_team.lighthouse.viemodel

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.dev7.lib.v2ray.V2rayController
import dev.dev7.lib.v2ray.utils.V2rayConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import pro.sparrow_team.lighthouse.data.datstore.DataStoreRepoImpl
import pro.sparrow_team.lighthouse.data.remote.NetworkResult
import pro.sparrow_team.lighthouse.model.ConfigurationResponse
import pro.sparrow_team.lighthouse.model.DecodedConfig
import pro.sparrow_team.lighthouse.model.Status
import pro.sparrow_team.lighthouse.model.UpdateTokenModel
import pro.sparrow_team.lighthouse.model.UserAuthenticityResponse
import pro.sparrow_team.lighthouse.model.UserPublicIDModel
import pro.sparrow_team.lighthouse.model.UserRegisterModel
import pro.sparrow_team.lighthouse.model.UserRegisterResponse
import pro.sparrow_team.lighthouse.repository.MainRepository
import pro.sparrow_team.lighthouse.utils.Constants
import java.util.Objects
import javax.inject.Inject

@HiltViewModel
class MainViewmodel @Inject constructor(
    private val mainRepo: MainRepository,
    private val dataStoreRepo: DataStoreRepoImpl,
    @ApplicationContext private val context: Context,
    private val firebaseMessaging: FirebaseMessaging,
) : ViewModel() {

    var isApiHealthy by mutableStateOf(true)

    val statusFlow = MutableStateFlow<NetworkResult<Status>>(NetworkResult.Loading())
    var status by mutableStateOf(Status())
    fun getStatus() {
        viewModelScope.launch {
            statusFlow.emit(mainRepo.getStatus())
        }
    }

    val registerUser =
        MutableStateFlow<NetworkResult<UserRegisterResponse>>(NetworkResult.Loading())

    fun registerUser(userRegisterModel: UserRegisterModel) {
        viewModelScope.launch {
            registerUser.emit(mainRepo.registerUser(userRegisterModel))
        }
    }


    val userAuthenticity =
        MutableStateFlow<NetworkResult<UserAuthenticityResponse>>(NetworkResult.Loading())

    fun getUserAuthenticity(id: String) {
        viewModelScope.launch {
            userAuthenticity.emit(mainRepo.getUserAuthenticity(id))
        }
    }

    val updateUserToken =
        MutableStateFlow<NetworkResult<UserRegisterResponse>>(NetworkResult.Loading())

    fun updateUserToken(id: String, updateTokenModel: UpdateTokenModel) {
        viewModelScope.launch {
            updateUserToken.emit(mainRepo.updateUserToken(id, updateTokenModel))
        }
    }


    val userPublicIDResponse =
        MutableStateFlow<NetworkResult<UserRegisterResponse>>(NetworkResult.Loading())

    fun sendUserPublicID(id: String, userPublicIDModel: UserPublicIDModel) {
        viewModelScope.launch {
            userPublicIDResponse.emit(mainRepo.sendUserPublicID(id, userPublicIDModel))
        }
    }


    var loadingText by mutableStateOf("")
    val configurations =
        MutableStateFlow<NetworkResult<List<ConfigurationResponse>>>(NetworkResult.Loading())
    var decodedConfigList = mutableStateOf<List<DecodedConfig>>(emptyList())
    var selectedServer by mutableStateOf<DecodedConfig?>(null)
    fun getConfigurations(id: String) {
        viewModelScope.launch {
            configurations.emit(mainRepo.getConfigurations(id))
        }
    }


    /**
     * in this code we find the server with the least delay
     * and filter them out
     */
    suspend fun findServerWithLeastDelay(configs: List<DecodedConfig>): DecodedConfig? {
        val dispatcher = Dispatchers.IO

        val deferredDelays = configs.map { config ->
            viewModelScope.async(dispatcher) {
                val delay =
                    V2rayController.getV2rayServerDelay(config.decodedConfig)
//                Log.i("TAG", "config ${config.name} delay $delay")
                Pair(config, delay)
            }
        }

        val results = deferredDelays.map { it.await() }
        val filteredResults =
            /**
             *filter -1 delays
             */
            results.filter { it.second > -1 }

        /**
         * check if all delays are -1
         */
        val allNegative = filteredResults.all { it.second.toInt() == -1 }

        return if (allNegative) {
            /**
             * if all delays are -1 we return the first config to prevent crashing
             */
//            Log.i("TAG", "allNegative: $allNegative")
            configs.firstOrNull()
        } else {
            /**
             * otherwise we will return the config with least delay
             * excluding -1
             */
            val (serverWithLeastDelay, delay) = filteredResults.minByOrNull { (_, delay) -> delay }
                ?: return null
//            Log.i("TAG", "Selected server: ${serverWithLeastDelay.id} delay : $delay")
            serverWithLeastDelay
        }
    }


    /**
     * in this code we decode the base64 configs into
     * decoded configs
     */
    fun turnEncodedConfigsToDecodedConfigs(configList: List<ConfigurationResponse>) {
        viewModelScope.launch(Dispatchers.IO) {
            val list = configList ?: return@launch
            val decodedConfigs = list.map { data ->
                val decodedConfig = if (data.configuration.isNotBlank()) {
                    decodeBase64(data.configuration)
                } else {
                    ""
                }
                if (DecodedConfig::class.java.isInstance(data)) {
                    data as DecodedConfig
                } else {
                    DecodedConfig(
                        decodedConfig = decodedConfig!!,
                        id = data.id,
                        location = data.location,
                        name = data.name
                    )
                }
            }

            /**
             * return decodedConfigList
             */
            decodedConfigList.value = decodedConfigs
        }
    }


    fun decodeBase64(encodedString: String): String? {
        try {
            val decodedBytes =
                android.util.Base64.decode(encodedString, android.util.Base64.DEFAULT)
            return String(decodedBytes)
        } catch (e: Exception) {
//            Log.e("TAG", "Error decoding base64: $e")
            return null
        }
    }


    /**
     * V2RAY
     * we observe the V2RAY library broadcast receiver
     * and get the connection states
     * and the timer
     */
    private val _connectionState = MutableStateFlow<ConnectionState>(ConnectionState.Disconnected)
    var connectionState = _connectionState
    private val _time = MutableStateFlow("")
    var time = _time

    private val _uploadSpeed = MutableStateFlow("")
    var uploadSpeed = _uploadSpeed

    private val _downloadSpeed = MutableStateFlow("")
    var downloadSpeed = _downloadSpeed

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val state =
                (intent.extras?.getSerializable(V2rayConstants.SERVICE_CONNECTION_STATE_BROADCAST_EXTRA) as V2rayConstants.CONNECTION_STATES?)
                    ?: return
            when (state) {
                V2rayConstants.CONNECTION_STATES.CONNECTED -> {
                    _connectionState.value =
                        ConnectionState.Connected
                    /**
                     * in this part we have to manually set the connection states
                     * into dataStore for when we disconnect the VPN using the notification bar
                     */
                    viewModelScope.launch {
                        dataStoreRepo.putBoolean(true, Constants.CONNECTION_STATE_ID)
                    }
                }

                V2rayConstants.CONNECTION_STATES.DISCONNECTED -> {
                    _connectionState.value =
                        ConnectionState.Disconnected
                    /**
                     * in this part we have to manually set the connection states
                     * into dataStore for when we disconnect the VPN using the notification bar
                     */
                    viewModelScope.launch {
                        dataStoreRepo.putBoolean(false, Constants.CONNECTION_STATE_ID)
                    }
                }

                V2rayConstants.CONNECTION_STATES.CONNECTING -> _connectionState.value =
                    ConnectionState.Connecting

                else -> {}
            }

            _time.value = Objects.requireNonNull(intent.extras)
                ?.getString(V2rayConstants.SERVICE_DURATION_BROADCAST_EXTRA)
                .toString()

            _uploadSpeed.value = Objects.requireNonNull(intent.extras)
                ?.getString(V2rayConstants.SERVICE_UPLOAD_SPEED_BROADCAST_EXTRA)
                .toString()

            _downloadSpeed.value = Objects.requireNonNull(intent.extras)
                ?.getString(V2rayConstants.SERVICE_DOWNLOAD_SPEED_BROADCAST_EXTRA)
                .toString()
        }
    }


    init {
        registerReceiver()
    }

    private fun registerReceiver() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.applicationContext.registerReceiver(
                receiver,
                IntentFilter(V2rayConstants.V2RAY_SERVICE_STATICS_BROADCAST_INTENT),
                Context.RECEIVER_EXPORTED
            )
        } else {
            context.applicationContext.registerReceiver(
                receiver,
                IntentFilter(V2rayConstants.V2RAY_SERVICE_STATICS_BROADCAST_INTENT)
            )
        }
    }

    private fun unregisterReceiver() {
        context.applicationContext.unregisterReceiver(receiver)
    }

    override fun onCleared() {
        super.onCleared()
        unregisterReceiver()
    }

    fun startV2ray(
        remark: String,
        config: String,
        activity: Activity,
        blockedApps: ArrayList<String>,
    ) {
        viewModelScope.launch {
            mainRepo.startV2ray(
                activity = activity,
                remark = remark,
                config = config,
                blockedApps = blockedApps
            )
        }
    }

    fun stopV2ray(activity: Activity) {
        viewModelScope.launch {
            mainRepo.stopV2ray(activity)
        }
    }


    /**
     * Other
     */


    fun openGmail(
        email: String,
        context: Context,
    ) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_EMAIL, arrayOf(email)) // Assuming validated email
            putExtra(Intent.EXTRA_SUBJECT, "Subject")
            putExtra(Intent.EXTRA_TEXT, "")
            setPackage("com.google.android.gm")
            putExtra(Intent.EXTRA_CC, email) // Optional CC field
            type = "text/html"
        }
        ContextCompat.startActivity(context, Intent.createChooser(intent, "Send mail"), null)
    }

    fun share(
        context: Context,
        url: String,
    ) {

        val shareIntent = Intent(Intent.ACTION_SEND)

        shareIntent.type = "text/plain"

        shareIntent.putExtra(
            Intent.EXTRA_TEXT,
            "Download Lighthouse VPN at $url"
        )

        context.startActivity(Intent.createChooser(shareIntent, "Share to..."))

    }

    fun openPlayStore(context: Activity, mainViewModel: MainViewmodel) {
        try {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(mainViewModel.status.url)
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun getFCMToken(onSuccess: (String) -> Unit, onError: (Exception) -> Unit) {
        firebaseMessaging.token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                onError(task.exception!!)
                return@addOnCompleteListener
            } else {
                val token = task.result
                token?.let { onSuccess(it) } ?: onError(Exception("Token is null"))
            }
        }
    }


}