package pro.sparrow_team.lighthouse.ui.screens.splash_screen

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.collectLatest
import pro.sparrow_team.lighthouse.R
import pro.sparrow_team.lighthouse.data.remote.NetworkResult
import pro.sparrow_team.lighthouse.utils.appConfig
import pro.sparrow_team.lighthouse.viemodel.DataStoreViewModel
import pro.sparrow_team.lighthouse.viemodel.MainViewmodel
import java.util.UUID

@Composable
fun GetConfigurationsSection(
    isUserRegistered: Boolean,
    mainViewModel: MainViewmodel,
    dataStoreViewModel: DataStoreViewModel,
    context: Context,
    isConfigProcessComplete: (Boolean) -> Unit,
    uuid: String
) {


    var isConfigsDecoded by remember {
        mutableStateOf(false)
    }


    /**
     * when the user is registered we will
     * get the servers (Configs) in this LaunchedEffect
     * the configs are base64 encoded so we have to decode them
     */
    LaunchedEffect(isUserRegistered) {
        if (isUserRegistered) {
            mainViewModel.loadingText = context.getString(R.string.loading_servers)
            mainViewModel.getConfigurations(uuid)
            mainViewModel.configurations.collectLatest {
                when (it) {
                    is NetworkResult.Success -> {
                        mainViewModel.turnEncodedConfigsToDecodedConfigs(it.data ?: emptyList())
                        isConfigsDecoded = true


//                        Log.e("TAG", "config OK")
                    }

                    is NetworkResult.Error -> {
                        isConfigsDecoded = false
//                        Log.e("TAG", "getConfiguration error ${it.message}")
                    }

                    is NetworkResult.Loading -> {}
                }
            }
        }
    }

    /**
     * if the configs are decoded and we are already connected to VPN (User may have closed the app)
     * we will call the appConfig method (more info in the appConfig fun)
     * and if the user is not connected we will get the config with the least delay
     */
    LaunchedEffect(isConfigsDecoded) {
        if (isConfigsDecoded) {
            if (dataStoreViewModel.getConnectionState()) {
//                Log.d("TAG", "${dataStoreViewModel.getSelectedServerId()}")
                appConfig(dataStoreViewModel, mainViewModel)
                isConfigProcessComplete(true)
            } else {
                mainViewModel.loadingText = context.getString(R.string.getting_server_delays)
                val serverWithLeastDelay =
                    mainViewModel.findServerWithLeastDelay(mainViewModel.decodedConfigList.value)
                mainViewModel.selectedServer = serverWithLeastDelay
                dataStoreViewModel.saveSelectedServerId(serverWithLeastDelay?.id ?: 1)
                isConfigProcessComplete(true)
            }
        }


    }


}