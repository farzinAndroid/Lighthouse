package pro.sparrow_team.lighthouse.ui.screens.splash_screen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest
import pro.sparrow_team.lighthouse.R
import pro.sparrow_team.lighthouse.data.remote.NetworkResult
import pro.sparrow_team.lighthouse.model.Status
import pro.sparrow_team.lighthouse.navigation.Screens
import pro.sparrow_team.lighthouse.ui.components.OnShadow
import pro.sparrow_team.lighthouse.ui.components.ShadowSection
import pro.sparrow_team.lighthouse.ui.theme.lighthouseBrown
import pro.sparrow_team.lighthouse.ui.theme.mainBackgroundColor
import pro.sparrow_team.lighthouse.utils.VersionHelper
import pro.sparrow_team.lighthouse.viemodel.ConnectionState
import pro.sparrow_team.lighthouse.viemodel.DataStoreViewModel
import pro.sparrow_team.lighthouse.viemodel.MainViewmodel

@Composable
fun SplashScreen(
    navController: NavController,
    mainViewmodel: MainViewmodel,
    dataStoreViewModel: DataStoreViewModel,
) {

    /**
     * observe on the connectionState
     * and save the connection state in dataStore
     * we may not need this part but i put as an insurance
     */
    LaunchedEffect(mainViewmodel.connectionState) {
        val connectionBoolean = mainViewmodel.connectionState.value == ConnectionState.Connected
        dataStoreViewModel.saveConnectionState(connectionBoolean)
    }

    val context = LocalContext.current

    var isStatusApiCallFinished by remember {
        mutableStateOf(false)
    }

    var isUserRegistrationComplete by remember {
        mutableStateOf(false)
    }
    var isConfigProcessComplete by remember {
        mutableStateOf(false)
    }
    var uuid by remember {
        mutableStateOf("")
    }

    LaunchedEffect(true) {
        mainViewmodel.loadingText = context.getString(R.string.loading)
        mainViewmodel.getStatus()
        mainViewmodel.statusFlow.collectLatest {
            when (it) {
                is NetworkResult.Success -> {
                    mainViewmodel.status = it.data ?: Status()
                    mainViewmodel.isApiHealthy = true
                    if (mainViewmodel.status.reject) {
                        navController.navigate(Screens.DisabledUserRegistration.route) {
                            popUpTo(Screens.Splash.route) {
                                inclusive = true
                            }
                        }
                    } else if (!mainViewmodel.status.functional) {
                        navController.navigate(Screens.UnderConstruction.route) {
                            popUpTo(Screens.Splash.route) {
                                inclusive = true
                            }
                        }
                    }
                    isStatusApiCallFinished = true
//                    Log.e("TAG,", "status api OK")
                }

                is NetworkResult.Loading -> {
                    mainViewmodel.isApiHealthy = true
                    isStatusApiCallFinished = false
                }

                is NetworkResult.Error -> {
                    mainViewmodel.isApiHealthy = false
                    isStatusApiCallFinished = true
//                    Log.e("TAG,", "status api failed ${it.message}")
                }
            }
        }
    }

    CreateNewUUID(
        dataStoreViewModel = dataStoreViewModel,
        onUUIDCreated = {
            uuid = it
        }
    )



    Splash(mainViewmodel)

    /**
     * if the Status api is called and correct
     * we will get on with registering user
     */
    if (isStatusApiCallFinished) {
        RegisterUserSection(
            mainViewmodel = mainViewmodel,
            dataStoreViewModel = dataStoreViewModel,
            context = context,
            isUserRegistrationComplete = {
                isUserRegistrationComplete = it
            },
            navController = navController,
            uuid = uuid
        )
    }


    /**
     * GetConfigurationsSection
     */
    GetConfigurationsSection(
        isUserRegistered = isUserRegistrationComplete,
        mainViewModel = mainViewmodel,
        dataStoreViewModel = dataStoreViewModel,
        context = context,
        isConfigProcessComplete = {
            isConfigProcessComplete = it
        },
        uuid = uuid
    )


    /**
     * change if you have a better way
     * in this part we observe isStatusApiCallFinished,isUserRegistrationComplete,isConfigProcessComplete
     * if api has problems we will go to main screen and handle it
     * if api is healthy and first api is called and register is complete and configProcess is also complete
     * we will go to the next screen
     */
    LaunchedEffect(isStatusApiCallFinished, isUserRegistrationComplete, isConfigProcessComplete) {
        if (!mainViewmodel.isApiHealthy) {
            navController.navigate(Screens.UnderConstruction.route) {
                popUpTo(Screens.Splash.route) {
                    inclusive = true
                }
            }
        } else {
            if (isStatusApiCallFinished && isUserRegistrationComplete && !isConfigProcessComplete) {
//                Log.e("TAG", "There is a problem try again later")
//            context.showToast("There is a problem try again later")
            } else if (isStatusApiCallFinished && isUserRegistrationComplete && isConfigProcessComplete) {
//                Log.e("TAG", "go to main and user is legit")
                if (isUpdateAvailable(mainViewmodel, context)) {
                    navController.navigate(Screens.Update.route) {
                        popUpTo(Screens.Splash.route) {
                            inclusive = true
                        }
                    }
                } else {
                    navController.navigate(Screens.Main.route) {
                        popUpTo(Screens.Splash.route) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun Splash(mainViewmodel: MainViewmodel) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.mainBackgroundColor)
    ) {

        ShadowSection(
            modifier = Modifier
                .align(Alignment.Center)
                .size(100.dp),
            blurRadius = 30.dp,
            spread = 10.dp,
            color = MaterialTheme.colorScheme.lighthouseBrown.copy(0.7f)
        )
        OnShadow(
            modifier = Modifier
                .padding(bottom = 12.dp)
                .size(30.dp)
                .align(Alignment.Center)

        )
        AppLogo(modifier = Modifier.align(Alignment.Center))
        Column(
            modifier = Modifier
                .padding(top = 220.dp)
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                LoadingAnimation()
                LoadingText(
                    loadingText = mainViewmodel.loadingText,
                    modifier = Modifier
                        .padding(top = 100.dp)
                )

            }
        }



    }

}


/**
 * we have 2 kinds of update
 * a forced update and a normal update in forced update we should not be able t work with the app.
 */

fun isForceUpdate(mainViewModel: MainViewmodel, context: Context): Boolean {
    return when {
        mainViewModel.status.minVersion > VersionHelper.getVersionCode(context)!! -> {
            true
        }

        mainViewModel.status.maxVersion > VersionHelper.getVersionCode(context)!! -> {
            false
        }

        else -> {
            false
        }
    }
}

fun isUpdateAvailable(mainViewModel: MainViewmodel, context: Context) =
    mainViewModel.status.maxVersion > VersionHelper.getVersionCode(context)!! ||
            mainViewModel.status.minVersion > VersionHelper.getVersionCode(context)!!