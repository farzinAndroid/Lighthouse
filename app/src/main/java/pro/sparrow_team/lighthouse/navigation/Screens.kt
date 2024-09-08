package pro.sparrow_team.lighthouse.navigation

import kotlinx.serialization.Serializable

abstract class Screens(val route:String) {

    data object Main : Screens("main_screen")

    data object Splash: Screens("splash_screen")

    data object Menu: Screens("menu_screen")

    data object DisabledUserRegistration: Screens("disabled_ser_register_screen")

    data object PrivacyPolicy: Screens("privacy_policy_screen")

    data object UnderConstruction: Screens("under_construction_screen")

    data object Update: Screens("update_screen")

}