package pro.sparrow_team.lighthouse.ui.screens.main_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import pro.sparrow_team.lighthouse.viemodel.DataStoreViewModel
import pro.sparrow_team.lighthouse.viemodel.MainViewmodel

@Composable
fun MainScreen(navController: NavController,mainViewmodel: MainViewmodel,dataStoreViewmodel: DataStoreViewModel) {


    Main(navController,mainViewmodel,dataStoreViewmodel)

}



@Composable
fun Main(navController: NavController,mainViewmodel: MainViewmodel,dataStoreViewmodel:DataStoreViewModel) {


    MainScreenContent(
        mainViewmodel=mainViewmodel,
        dataStoreViewmodel = dataStoreViewmodel,
        navController = navController
    )


}
