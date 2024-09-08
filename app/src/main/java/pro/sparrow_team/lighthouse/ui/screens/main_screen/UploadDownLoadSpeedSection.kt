package pro.sparrow_team.lighthouse.ui.screens.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pro.sparrow_team.lighthouse.R
import pro.sparrow_team.lighthouse.utils.MySpacerHeight
import pro.sparrow_team.lighthouse.utils.MySpacerWidth
import pro.sparrow_team.lighthouse.viemodel.ConnectionState

@Composable
fun UploadDownLoadSpeedSection(
    uploadSpeed: String,
    downloadSpeed: String,
    connectionState: ConnectionState
) {



        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.Absolute.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            if (connectionState == ConnectionState.Connected){
                UploadDownloadSpeedText(painterResource(R.drawable.arrow_down), text = downloadSpeed)
                MySpacerWidth(width = 10.dp)
                UploadDownloadSpeedText(painterResource(R.drawable.arrow_up), text = uploadSpeed)
            }

        }


}