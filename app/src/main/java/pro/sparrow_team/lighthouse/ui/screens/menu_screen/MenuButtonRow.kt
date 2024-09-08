package pro.sparrow_team.lighthouse.ui.screens.menu_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pro.sparrow_team.lighthouse.R
import pro.sparrow_team.lighthouse.ui.theme.switchOFFColor
import pro.sparrow_team.lighthouse.utils.MySpacerWidth

@Composable
fun MenuButtonRow(
    painter: Painter,
    text: String,
    onClick: () -> Unit,
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
    ) {

            Image(
                painter = painter,
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp),
            )



        MySpacerWidth(width = 4.dp)

        Text(
            text = text,
            style = TextStyle(
                color = MaterialTheme.colorScheme.switchOFFColor,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.sourcesanspro_regular))
            )
        )

    }

}