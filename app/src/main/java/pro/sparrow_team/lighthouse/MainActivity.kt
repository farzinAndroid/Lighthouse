package pro.sparrow_team.lighthouse

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.dev7.lib.v2ray.V2rayController
import pro.sparrow_team.lighthouse.navigation.NavGraph
import pro.sparrow_team.lighthouse.ui.theme.LighthouseTheme
import pro.sparrow_team.lighthouse.ui.theme.mainBackgroundColor
import pro.sparrow_team.lighthouse.utils.ChangeStatusBarAndNavigationBarColor
import pro.sparrow_team.lighthouse.utils.showToast
import pro.sparrow_team.lighthouse.viemodel.DataStoreViewModel
import pro.sparrow_team.lighthouse.viemodel.MainViewmodel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@AndroidEntryPoint
/**
 * we have to use AppCompatActivity to work with the v2ray module
 * i tried using ComponentActivity but when we are connected to VPN
 * and close the app an exception comes up that says the receiver is not registered
 */
class MainActivity : AppCompatActivity() {

    private val mainViewmodel by viewModels<MainViewmodel>()
    private val dataStoreViewModel by viewModels<DataStoreViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        V2rayController.init(
            this@MainActivity,
            R.drawable.app_logo_with_shadow,
            this.getString(R.string.app_name)
        )
        setContent {
            LighthouseTheme {

                val navController = rememberNavController()
                val isDarkMode = isSystemInDarkTheme()

//                Log.e("TAG","from main activity ${V2rayController.getCoreVersion()}")
                ChangeStatusBarAndNavigationBarColor(
                    isDarkMode = isDarkMode,
                    context = this
                )

                CompositionLocalProvider(LocalLayoutDirection.provides(LayoutDirection.Ltr)) {
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.mainBackgroundColor)
                    ) {
                        NavGraph(
                            navController = navController,
                            mainViewmodel = mainViewmodel,
                            dataStoreViewModel = dataStoreViewModel
                        )
                    }
                }
            }
        }
    }
}