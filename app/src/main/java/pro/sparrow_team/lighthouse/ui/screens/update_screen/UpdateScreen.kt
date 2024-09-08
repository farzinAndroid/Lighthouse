package pro.sparrow_team.lighthouse.ui.screens.update_screen

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pro.sparrow_team.lighthouse.R
import pro.sparrow_team.lighthouse.navigation.Screens
import pro.sparrow_team.lighthouse.ui.components.TendToLightSection
import pro.sparrow_team.lighthouse.ui.screens.splash_screen.isForceUpdate
import pro.sparrow_team.lighthouse.ui.theme.mainBackgroundColor
import pro.sparrow_team.lighthouse.viemodel.MainViewmodel

@Composable
fun UpdateScreen(
    navController: NavController,
    mainViewmodel: MainViewmodel,
) {


    val context = LocalContext.current

    var isCloseActive by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.mainBackgroundColor)
            .statusBarsPadding()
    ) {
        Image(
            painter = painterResource(R.drawable.close),
            contentDescription = "",
            modifier = Modifier
                .padding(top = 20.dp, end = 20.dp)
                .size(24.dp)
                .align(Alignment.TopEnd)
                .clickable(enabled = !isCloseActive) {
                    if (!isForceUpdate(mainViewmodel,context)){
                        isCloseActive = true
                        navController.navigate(Screens.Main.route) {
                            popUpTo(Screens.Update.route) {
                                inclusive = true
                            }
                        }
                    }
                }
        )



        UpdateDescriptionSection(
            mainViewmodel = mainViewmodel,
            context = context,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 75.dp),
            isCancelButtonEnabled = !isForceUpdate(mainViewmodel, context),
            onUpdateButtonClicked = {
                mainViewmodel.openPlayStore(
                    context as Activity,
                    mainViewmodel
                )
            },
            onCancelButtonClicked = {
                isCloseActive = true
                    navController.navigate(Screens.Main.route) {
                        popUpTo(Screens.Update.route) {
                            inclusive = true
                        }
                    }
            }
        )



        TendToLightSection(
            modifier = Modifier.align(Alignment.BottomCenter)
            /** *shadow modifier */
            ,
            textModifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 60.dp)
        )

    }

}