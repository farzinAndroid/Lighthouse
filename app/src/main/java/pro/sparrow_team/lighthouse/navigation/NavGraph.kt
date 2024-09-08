package pro.sparrow_team.lighthouse.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pro.sparrow_team.lighthouse.ui.screens.disabled_user_screen.DisabledUserRegistrationScreen
import pro.sparrow_team.lighthouse.ui.screens.main_screen.MainScreen
import pro.sparrow_team.lighthouse.ui.screens.menu_screen.MenuScreen
import pro.sparrow_team.lighthouse.ui.screens.privacy_policy_screen.PrivacyPolicyScreen
import pro.sparrow_team.lighthouse.ui.screens.splash_screen.SplashScreen
import pro.sparrow_team.lighthouse.ui.screens.under_construction_screen.UnderConstructionScreen
import pro.sparrow_team.lighthouse.ui.screens.update_screen.UpdateScreen
import pro.sparrow_team.lighthouse.viemodel.DataStoreViewModel
import pro.sparrow_team.lighthouse.viemodel.MainViewmodel

@Composable
fun NavGraph(
    navController: NavHostController,
    mainViewmodel: MainViewmodel,
    dataStoreViewModel: DataStoreViewModel
) {

    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route
    ) {

        composable(route = Screens.Main.route){
            MainScreen(navController,mainViewmodel,dataStoreViewModel)
        }

        composable(route = Screens.Splash.route) {
            SplashScreen(navController,mainViewmodel,dataStoreViewModel)
        }

        composable(route = Screens.Menu.route) {
            MenuScreen(navController,mainViewmodel,dataStoreViewModel)
        }

        composable(route = Screens.DisabledUserRegistration.route) {
            DisabledUserRegistrationScreen(navController,mainViewmodel,dataStoreViewModel)
        }

        composable(route = Screens.PrivacyPolicy.route) {
            PrivacyPolicyScreen(navController,mainViewmodel)
        }

        composable(route = Screens.UnderConstruction.route) {
            UnderConstructionScreen(navController,mainViewmodel)
        }

        composable(route = Screens.Update.route){
            UpdateScreen(navController,mainViewmodel)
        }



    }
}