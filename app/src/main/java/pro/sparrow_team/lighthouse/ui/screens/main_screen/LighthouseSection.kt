package pro.sparrow_team.lighthouse.ui.screens.main_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pro.sparrow_team.lighthouse.R
import pro.sparrow_team.lighthouse.ui.components.OnShadow
import pro.sparrow_team.lighthouse.ui.components.ShadowSection
import pro.sparrow_team.lighthouse.viemodel.ConnectionState

@Composable
fun LighthouseSection(connectionState: ConnectionState) {


    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        ShadowSection(
            modifier = Modifier
                .padding(bottom = 270.dp)
                .align(Alignment.BottomCenter)

        )

        if (connectionState == ConnectionState.Connected) {
            OnShadow(
                modifier = Modifier
                    .padding(bottom = 280.dp)
                    .align(Alignment.BottomCenter)
            )
        }

        LighthousePicture(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(390.dp),
            picture = if (connectionState == ConnectionState.Connected) painterResource(R.drawable.lighthouse_on) else painterResource(R.drawable.lighthouse_off)
        )
    }


}