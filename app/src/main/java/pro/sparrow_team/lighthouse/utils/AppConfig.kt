package pro.sparrow_team.lighthouse.utils

import pro.sparrow_team.lighthouse.viemodel.DataStoreViewModel
import pro.sparrow_team.lighthouse.viemodel.MainViewmodel

fun appConfig(
    dataStore: DataStoreViewModel,
    mainViewModel: MainViewmodel
) {

    getDataStoreVariables(dataStore,mainViewModel)

}

private fun getDataStoreVariables(dataStore: DataStoreViewModel,mainViewModel: MainViewmodel) {
    /**
     * here we fill the selectedServer var with the server that is
     * already selected in the dataStore
     */
    dataStore.getSelectedServerId().let { id ->
        mainViewModel.selectedServer = mainViewModel.decodedConfigList.value.find { it.id == id }
    }
}