package pro.sparrow_team.lighthouse.ui.screens.update_screen

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pro.sparrow_team.lighthouse.R
import pro.sparrow_team.lighthouse.navigation.Screens
import pro.sparrow_team.lighthouse.ui.components.CustomButton
import pro.sparrow_team.lighthouse.ui.components.FillYellowButton
import pro.sparrow_team.lighthouse.ui.screens.splash_screen.isForceUpdate
import pro.sparrow_team.lighthouse.ui.theme.switchOFFColor
import pro.sparrow_team.lighthouse.ui.theme.switchONColor
import pro.sparrow_team.lighthouse.utils.MySpacerHeight
import pro.sparrow_team.lighthouse.utils.MySpacerWidth
import pro.sparrow_team.lighthouse.viemodel.MainViewmodel

@Composable
fun UpdateDescriptionSection(
    modifier: Modifier = Modifier,
    mainViewmodel: MainViewmodel,
    context: Context,
    isCancelButtonEnabled:Boolean,
    onCancelButtonClicked:()->Unit,
    onUpdateButtonClicked:()->Unit
) {

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        UpdateText()

        MySpacerHeight(height = 16.dp)

        Text(
            text = mainViewmodel.status.descriptions.update[0],
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


        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            FillYellowButton(text = stringResource(R.string.update)) {
                onUpdateButtonClicked()
            }

            MySpacerWidth(width = 16.dp)

            CustomButton(
                text = stringResource(R.string.cancel),
                textColor = MaterialTheme.colorScheme.switchOFFColor,
                onClick = {
                    onCancelButtonClicked()
                },
                shapes = RoundedCornerShape(50.dp),
                borderColor = if (isCancelButtonEnabled) MaterialTheme.colorScheme.switchONColor else Color.Gray,
                backgroundColor = if (isCancelButtonEnabled) Color.Transparent else Color.Gray,
                borderStroke = 2.dp,
                enabled =isCancelButtonEnabled
            )


        }


    }

}

