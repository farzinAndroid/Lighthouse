package pro.sparrow_team.lighthouse.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pro.sparrow_team.lighthouse.ui.theme.lightONColor1

@Composable
fun OnShadow(modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .size(70.dp)
            .shadow(
                color = MaterialTheme.colorScheme.lightONColor1,
                borderRadius = 100.dp,
                blurRadius = 10.dp,
                spread = 10.dp,
            )
    ) {

        /**
         * inner circle commented
         * because the inner circle is already in the lighthouse_on drawable
         * it changes based on if the VPN is connected
         * change if found a better way.
         */
        /*Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .align(Alignment.Center)
                .background(MaterialTheme.colorScheme.lightONColor2)
        )*/
    }

}