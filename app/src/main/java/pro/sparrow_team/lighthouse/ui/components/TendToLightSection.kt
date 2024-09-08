package pro.sparrow_team.lighthouse.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import pro.sparrow_team.lighthouse.R
import pro.sparrow_team.lighthouse.ui.theme.switchOFFColor

@Composable
fun TendToLightSection(
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier
) {

    ShadowSection(
        modifier = modifier
    )

    Text(
        text = stringResource(R.string.tend_to_light),
        style = TextStyle(
            color = MaterialTheme.colorScheme.switchOFFColor,
            fontFamily = FontFamily(Font(R.font.sourcesanspro_regular)),
            fontSize = 16.sp
        ),
        modifier = textModifier
    )

}