package pro.sparrow_team.lighthouse.repository

import android.app.Activity
import dev.dev7.lib.v2ray.V2rayController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pro.sparrow_team.lighthouse.data.remote.BaseApiResponse
import pro.sparrow_team.lighthouse.data.remote.MainApiInterface
import pro.sparrow_team.lighthouse.model.UpdateTokenModel
import pro.sparrow_team.lighthouse.model.UserPublicIDModel
import pro.sparrow_team.lighthouse.model.UserRegisterModel
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val mainApiInterface: MainApiInterface
) : BaseApiResponse() {


    /**
     * APIs
     */
    suspend fun getStatus() = safeApiCall {
        mainApiInterface.getStatus()
    }

    suspend fun registerUser(userRegisterModel: UserRegisterModel) = safeApiCall {
        mainApiInterface.registerUser(userRegisterModel)
    }

    suspend fun getUserAuthenticity(id: String) = safeApiCall {
        mainApiInterface.getUserAuthenticity(id)
    }

    suspend fun updateUserToken(id: String, updateTokenModel: UpdateTokenModel) = safeApiCall {
        mainApiInterface.updateUserToken(id, updateTokenModel)
    }

    suspend fun sendUserPublicID(id: String, userPublicIDModel: UserPublicIDModel) = safeApiCall {
        mainApiInterface.sendUserPublicID(id, userPublicIDModel)
    }


    suspend fun getConfigurations(id: String) = safeApiCall {
        mainApiInterface.getConfigurations(id)
    }






    /**
     * V2ray Section
     */
    suspend fun startV2ray(
        remark: String,
        config: String,
        activity: Activity,
        blockedApps: ArrayList<String>,
    ) {
        withContext(Dispatchers.IO) {
            V2rayController.startV2ray(activity, remark, config, blockedApps)
        }
    }

    suspend fun stopV2ray(activity: Activity) {
        withContext(Dispatchers.Main) {
            V2rayController.stopV2ray(activity)
        }
    }

}