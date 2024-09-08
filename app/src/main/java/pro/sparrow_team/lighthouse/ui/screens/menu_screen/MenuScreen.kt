package pro.sparrow_team.lighthouse.ui.screens.menu_screen

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
import pro.sparrow_team.lighthouse.ui.components.TendToLightSection
import pro.sparrow_team.lighthouse.ui.theme.mainBackgroundColor
import pro.sparrow_team.lighthouse.viemodel.DataStoreViewModel
import pro.sparrow_team.lighthouse.viemodel.MainViewmodel

@Composable
fun MenuScreen(
    navController: NavController,
    mainViewmodel: MainViewmodel,
    dataStoreViewModel: DataStoreViewModel,
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
                    isCloseActive = true
                    navController.navigateUp()
                }
        )


        MenuCenterSection(
            modifier = Modifier.align(Alignment.TopCenter),
            mainViewmodel = mainViewmodel,
            context = context,
            navController = navController
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