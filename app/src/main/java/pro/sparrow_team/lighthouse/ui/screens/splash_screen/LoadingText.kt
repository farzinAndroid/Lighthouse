package pro.sparrow_team.lighthouse.ui.screens.splash_screen

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import pro.sparrow_team.lighthouse.R
import pro.sparrow_team.lighthouse.ui.theme.switchOFFColor

@Composable
fun LoadingText(loadingText:String,modifier: Modifier = Modifier) {

    Text(
        text = loadingText,
        color = MaterialTheme.colorScheme.switchOFFColor,
        fontFamily = FontFamily(Font(R.font.sourcesanspro_regular)),
        fontSize = 14.sp,
        modifier = modifier
    )

}