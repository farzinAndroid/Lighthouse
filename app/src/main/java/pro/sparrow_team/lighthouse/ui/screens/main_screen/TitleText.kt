package pro.sparrow_team.lighthouse.ui.screens.main_screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pro.sparrow_team.lighthouse.R
import pro.sparrow_team.lighthouse.ui.theme.switchOFFColor
import pro.sparrow_team.lighthouse.ui.theme.switchONColor

@Composable
fun TitleText(
    modifier: Modifier = Modifier
) {

    // Define the starting and ending indices for "Light"
    val text = stringResource(R.string.app_name)
    val startIndex = text.indexOf("Light")
    val endIndex = startIndex + "Light".length

    val annotatedText = buildAnnotatedString {
        append(text.substring(0, startIndex)) // Add "Light"

        // Apply specific style to "Light"
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.switchONColor)) { // Change Color.Red to your desired color
            append(text.substring(startIndex, endIndex))
        }

        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.switchOFFColor)) {
            append(text.substring(endIndex)) // Add "house"
        }
    }

    Text(
        text = annotatedText,
        fontFamily = FontFamily(Font(R.font.sourcesanspro_regular)),
        fontSize = 48.sp,
        modifier = modifier
            .fillMaxWidth(),
        textAlign = TextAlign.Center
    )

}