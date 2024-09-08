package pro.sparrow_team.lighthouse.ui.screens.disabled_user_screen

import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.sp
import pro.sparrow_team.lighthouse.R
import pro.sparrow_team.lighthouse.ui.theme.switchOFFColor
import pro.sparrow_team.lighthouse.ui.theme.switchONColor

@Composable
fun DisabledUserText(modifier: Modifier = Modifier) {


    val text = stringResource(R.string.disabled_user_register)
    val startIndex = text.indexOf("Disabled")
    val endIndex = startIndex + "Disabled".length

    val annotatedText = buildAnnotatedString {
        append(text.substring(0, startIndex))


        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.switchONColor)) {
            append(text.substring(startIndex, endIndex))
        }

        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.switchOFFColor)) {
            append(text.substring(endIndex))
        }
    }

    Text(
        text = annotatedText,
        fontFamily = FontFamily(Font(R.font.sourcesanspro_semibold)),
        fontSize = 20.sp,
        modifier = modifier
            .fillMaxWidth(),
        textAlign = TextAlign.Center
    )

}