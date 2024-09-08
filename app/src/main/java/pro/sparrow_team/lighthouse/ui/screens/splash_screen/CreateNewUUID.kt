package pro.sparrow_team.lighthouse.ui.screens.splash_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import pro.sparrow_team.lighthouse.viemodel.DataStoreViewModel
import java.util.UUID

@Composable
fun CreateNewUUID(
    dataStoreViewModel: DataStoreViewModel,
    onUUIDCreated: (String) -> Unit
) {

    LaunchedEffect(true) {
        if (dataStoreViewModel.getUUID() == null){
            val uuid = UUID.randomUUID().toString()
            dataStoreViewModel.saveUUID(uuid)
            onUUIDCreated(uuid)
        }else{
            val uuid = dataStoreViewModel.getUUID()
            onUUIDCreated(uuid!!)
        }
    }

}