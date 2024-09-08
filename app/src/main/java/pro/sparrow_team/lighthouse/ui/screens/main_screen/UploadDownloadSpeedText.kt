package pro.sparrow_team.lighthouse.ui.screens.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pro.sparrow_team.lighthouse.R
import pro.sparrow_team.lighthouse.ui.theme.switchOFFColor
import pro.sparrow_team.lighthouse.ui.theme.switchONColor
import pro.sparrow_team.lighthouse.utils.MySpacerWidth

@Composable
fun UploadDownloadSpeedText(painter: Painter,text:String) {


    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.Center
    ) {
        Icon(
            painter =painter,
            contentDescription ="",
            modifier = Modifier
                .size(18.dp),
            tint = MaterialTheme.colorScheme.switchONColor
        )

        MySpacerWidth(width = 2.dp)

        Text(
            text = text,
            style = TextStyle(
                color = MaterialTheme.colorScheme.switchOFFColor,
                fontFamily = FontFamily(Font(R.font.sourcesanspro_regular)),
                fontSize = 16.sp
            )
        )
    }

}



@Composable
fun UploadDownloadSpeedText(imageVector: ImageVector, text:String) {


    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.Center
    ) {
        Icon(
            imageVector =imageVector,
            contentDescription ="",
            modifier = Modifier
                .size(16.dp),
            tint = MaterialTheme.colorScheme.switchONColor
        )

        MySpacerWidth(width = 2.dp)

        Text(
            text = text,
            style = TextStyle(
                color = MaterialTheme.colorScheme.switchOFFColor,
                fontFamily = FontFamily(Font(R.font.sourcesanspro_regular)),
                fontSize = 12.sp
            )
        )
    }

}