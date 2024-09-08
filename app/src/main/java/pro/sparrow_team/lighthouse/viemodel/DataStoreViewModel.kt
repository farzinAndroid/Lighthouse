package pro.sparrow_team.lighthouse.viemodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import pro.sparrow_team.lighthouse.data.datstore.DataStoreRepoImpl
import pro.sparrow_team.lighthouse.utils.Constants.CONNECTION_STATE_ID
import pro.sparrow_team.lighthouse.utils.Constants.FCM_TOKEN_ID
import pro.sparrow_team.lighthouse.utils.Constants.REGISTER_ID
import pro.sparrow_team.lighthouse.utils.Constants.SELECTED_SERVER_ID
import pro.sparrow_team.lighthouse.utils.Constants.UUID_ID
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(
    private val dataStoreRepo: DataStoreRepoImpl
) : ViewModel() {


    fun saveToken(value:String){
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepo.putString(value, FCM_TOKEN_ID)
        }
    }

    fun getToken() = runBlocking {
        dataStoreRepo.getString(FCM_TOKEN_ID) ?: ""
    }


    fun saveUUID(value:String){
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepo.putString(value, UUID_ID)
        }
    }

    fun getUUID() = runBlocking {
        dataStoreRepo.getString(UUID_ID)
    }

    fun saveRegisterState(value:Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepo.putBoolean(value, REGISTER_ID)
        }
    }

    fun getRegisterState() = runBlocking {
        dataStoreRepo.getBoolean(REGISTER_ID) ?: false
    }

    fun saveSelectedServerId(value:Int){
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepo.putInt(value, SELECTED_SERVER_ID)
        }
    }

    fun getSelectedServerId() = runBlocking {
        dataStoreRepo.getInt(SELECTED_SERVER_ID) ?: -1
    }


    fun saveConnectionState(value:Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepo.putBoolean(value, CONNECTION_STATE_ID)
        }
    }

    fun getConnectionState() = runBlocking {
        dataStoreRepo.getBoolean(CONNECTION_STATE_ID) ?: false
    }


}