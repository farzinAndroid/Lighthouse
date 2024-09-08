package pro.sparrow_team.lighthouse.ui.screens.under_construction_screen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pro.sparrow_team.lighthouse.R
import pro.sparrow_team.lighthouse.ui.components.FillYellowButton
import pro.sparrow_team.lighthouse.ui.theme.switchOFFColor
import pro.sparrow_team.lighthouse.utils.MySpacerHeight
import pro.sparrow_team.lighthouse.viemodel.MainViewmodel

@Composable
fun UnderConstructionDescriptionSection(modifier: Modifier = Modifier, mainViewmodel: MainViewmodel, context: Context) {

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        UnderConstructionText()

        MySpacerHeight(height = 16.dp)

        Text(
            text = mainViewmodel.status.descriptions.maintenance ?: stringResource(R.string.under_construction_caption),
            style = TextStyle(
                color = MaterialTheme.colorScheme.switchOFFColor,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.sourcesanspro_regular)),
                textAlign = TextAlign.Justify
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 60.dp)
        )

        MySpacerHeight(height = 24.dp)

        FillYellowButton(text = stringResource(R.string.contact_us)) {
            mainViewmodel.openGmail(mainViewmodel.status.email ?: "info@sparrow-team.pro",context)
        }

    }

}

