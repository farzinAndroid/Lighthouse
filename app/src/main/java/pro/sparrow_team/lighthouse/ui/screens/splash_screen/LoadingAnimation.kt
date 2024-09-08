package pro.sparrow_team.lighthouse.ui.screens.splash_screen

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import pro.sparrow_team.lighthouse.R

@Composable
fun LoadingAnimation(modifier: Modifier = Modifier) {


    val lottie by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading_1))

    LottieAnimation(
        composition =  lottie,
        modifier = modifier
            .size(100.dp),
        speed = 1f,
        iterations = Int.MAX_VALUE
    )

}