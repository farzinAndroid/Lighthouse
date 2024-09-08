package pro.sparrow_team.lighthouse.ui.screens.disabled_user_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pro.sparrow_team.lighthouse.ui.components.TendToLightSection
import pro.sparrow_team.lighthouse.ui.theme.LighthouseTheme
import pro.sparrow_team.lighthouse.ui.theme.mainBackgroundColor
import pro.sparrow_team.lighthouse.viemodel.DataStoreViewModel
import pro.sparrow_team.lighthouse.viemodel.MainViewmodel

@Composable
fun DisabledUserRegistrationScreen(
    navController: NavController? = null,
    mainViewmodel: MainViewmodel? = null,
    dataStoreViewModel: DataStoreViewModel? = null,
) {

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.mainBackgroundColor)
            .statusBarsPadding()
    ) {

        DisabledUserRegistrationDescriptionSection(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 75.dp),
            context = context,
            mainViewmodel = mainViewmodel!!
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

@Preview
@Composable
private fun DisabledUserRegistrationScreenPrev() {
    LighthouseTheme {
        DisabledUserRegistrationScreen()
    }
}