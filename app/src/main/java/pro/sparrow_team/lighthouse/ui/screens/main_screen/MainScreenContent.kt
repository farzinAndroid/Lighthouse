package pro.sparrow_team.lighthouse.ui.screens.main_screen

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import pro.sparrow_team.lighthouse.navigation.Screens
import pro.sparrow_team.lighthouse.ui.theme.mainBackgroundColor
import pro.sparrow_team.lighthouse.viemodel.ConnectionState
import pro.sparrow_team.lighthouse.viemodel.DataStoreViewModel
import pro.sparrow_team.lighthouse.viemodel.MainViewmodel

@Composable
fun MainScreenContent(
    mainViewmodel: MainViewmodel,
    dataStoreViewmodel: DataStoreViewModel,
    navController: NavController,
) {

    val context = LocalContext.current as Activity
    val scope = rememberCoroutineScope()
    val connectionState by mainViewmodel.connectionState.collectAsState()
    val uploadSpeed by mainViewmodel.uploadSpeed.collectAsState()
    val downloadSpeed by mainViewmodel.downloadSpeed.collectAsState()

    // Observe connection state and update the connecting state accordingly
    LaunchedEffect(connectionState) {
        val connectionBoolean = connectionState == ConnectionState.Connected
        dataStoreViewmodel.saveConnectionState(connectionBoolean)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.mainBackgroundColor)
    ) {


        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            /** * Title and switch section*/
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
            ) {

                TopBarRow(
                    modifier = Modifier.align(Alignment.TopCenter),
                    onMenuClicked = {
                        navController.navigate(Screens.Menu.route) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    UploadDownLoadSpeedSection(uploadSpeed, downloadSpeed, connectionState)

                    CustomSwitchButton(
                        mainViewmodel = mainViewmodel,
                        connectionState = connectionState,
                        onClick = {
                            scope.launch {
                                if (connectionState == ConnectionState.Connected) {
                                    dataStoreViewmodel.saveConnectionState(false)
                                    mainViewmodel.stopV2ray(context)
                                } else {
                                    mainViewmodel.startV2ray(
                                        mainViewmodel.selectedServer?.name ?: "",
                                        mainViewmodel.selectedServer?.decodedConfig ?: "",
                                        context,
                                        arrayListOf("pro.sparrow_team.lighthouse")
                                    )
                                }
                            }
                        }
                    )
                    TitleText(Modifier.padding(top = 24.dp))
                }

            }


            /** *ShadowSection and lighthouse section*/
            LighthouseSection(connectionState)

        }

    }


}