package pro.sparrow_team.lighthouse.ui.screens.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pro.sparrow_team.lighthouse.R

@Composable
fun TopBarRow(
    modifier: Modifier = Modifier,
    onMenuClicked:()->Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(R.drawable.menu),
            contentDescription ="",
            modifier = Modifier
                .padding(top = 20.dp, start = 20.dp)
                .size(22.dp)
                .clickable { onMenuClicked() }

        )

    }

}