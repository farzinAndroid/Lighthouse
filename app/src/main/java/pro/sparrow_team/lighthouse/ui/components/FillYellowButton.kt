package pro.sparrow_team.lighthouse.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pro.sparrow_team.lighthouse.R
import pro.sparrow_team.lighthouse.ui.theme.switchOFFColor
import pro.sparrow_team.lighthouse.ui.theme.switchONColor

@Composable
fun FillYellowButton(
    modifier: Modifier = Modifier,
    text:String,
    onClick:()->Unit
) {


    Button(
        onClick = { onClick() },
        modifier = Modifier
            .height(40.dp)
            .width(120.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.switchONColor,
        ),
        shape = Shapes().extraLarge,
    ) {

        Text(text = text,
            style = TextStyle(
                color = MaterialTheme.colorScheme.switchOFFColor,
                fontFamily = FontFamily(Font(R.font.sourcesanspro_regular)),
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )
        )

    }

}