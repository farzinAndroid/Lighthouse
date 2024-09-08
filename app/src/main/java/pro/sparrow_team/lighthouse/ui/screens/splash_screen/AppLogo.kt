package pro.sparrow_team.lighthouse.ui.screens.splash_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pro.sparrow_team.lighthouse.R

@Composable
fun AppLogo(modifier: Modifier = Modifier) {

    Image(
        painter = painterResource(R.drawable.app_logo_with_light),
        contentDescription = "",
        modifier = modifier
            .size(125.dp)
    )

}