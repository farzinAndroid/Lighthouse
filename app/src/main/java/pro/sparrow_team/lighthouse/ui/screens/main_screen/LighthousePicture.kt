package pro.sparrow_team.lighthouse.ui.screens.main_screen

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale

@Composable
fun LighthousePicture(modifier: Modifier = Modifier,picture:Painter) {

    Image(
        painter = picture,
        contentDescription ="",
        modifier = modifier,
         contentScale = ContentScale.Fit
    )

}