package pro.sparrow_team.lighthouse.data.service

import com.google.firebase.messaging.FirebaseMessagingService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pro.sparrow_team.lighthouse.data.datstore.DataStoreRepoImpl
import pro.sparrow_team.lighthouse.utils.Constants
import pro.sparrow_team.lighthouse.utils.showToast
import javax.inject.Inject


@AndroidEntryPoint
class FCMService : FirebaseMessagingService() {

    /**
     * get the new token from FCM service
     * and save it into datastore
     */
    @Inject
    lateinit var mainCoroutineScope: CoroutineScope

    @Inject
    lateinit var dataStoreRepo: DataStoreRepoImpl

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        saveToken(token)
        mainCoroutineScope.launch(Dispatchers.Main) {
//            applicationContext.showToast("New token : $token")
        }
    }

    private fun saveToken(token:String){
        mainCoroutineScope.launch(Dispatchers.IO) {
            dataStoreRepo.putString(token, Constants.FCM_TOKEN_ID)
        }
    }

}

