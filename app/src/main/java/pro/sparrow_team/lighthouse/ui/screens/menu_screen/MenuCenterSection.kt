package pro.sparrow_team.lighthouse.ui.screens.menu_screen

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pro.sparrow_team.lighthouse.R
import pro.sparrow_team.lighthouse.navigation.Screens
import pro.sparrow_team.lighthouse.ui.screens.main_screen.TitleText
import pro.sparrow_team.lighthouse.ui.theme.LighthouseTheme
import pro.sparrow_team.lighthouse.utils.MySpacerHeight
import pro.sparrow_team.lighthouse.viemodel.MainViewmodel


@Composable
fun MenuCenterSection(
    modifier: Modifier = Modifier,
    mainViewmodel: MainViewmodel,
    context: Context,
    navController: NavController
) {

    Column(
        modifier = modifier
            .padding(top = 75.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TitleText()


        MySpacerHeight(height = 20.dp)

        MenuButtonRow(
            painter = painterResource(R.drawable.star),
            text = stringResource(R.string.invite_friends)
        ) {
            mainViewmodel.share(context,mainViewmodel.status.url)
        }

        MySpacerHeight(height = 20.dp)

        MenuButtonRow(
            painter = painterResource(R.drawable.privacy),
            text = stringResource(R.string.privacy_policy)
        ) {
            navController.navigate(Screens.PrivacyPolicy.route){
                launchSingleTop = true
                restoreState = true
            }
        }

        MySpacerHeight(height = 20.dp)

        MenuButtonRow(
            painter = painterResource(R.drawable.issue),
            text = "${stringResource(R.string.report_issue)}  "
        ) {
            mainViewmodel.openGmail(mainViewmodel.status.email ?: "info@sparrow-team.pro",context)
        }


    }

}