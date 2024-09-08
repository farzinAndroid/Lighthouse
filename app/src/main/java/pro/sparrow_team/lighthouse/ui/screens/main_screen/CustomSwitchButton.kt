package pro.sparrow_team.lighthouse.ui.screens.main_screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pro.sparrow_team.lighthouse.R
import pro.sparrow_team.lighthouse.ui.theme.mainBackgroundColor
import pro.sparrow_team.lighthouse.ui.theme.switchOFFColor
import pro.sparrow_team.lighthouse.ui.theme.switchONColor
import pro.sparrow_team.lighthouse.viemodel.ConnectionState
import pro.sparrow_team.lighthouse.viemodel.MainViewmodel

@Composable
fun CustomSwitchButton(
    modifier: Modifier = Modifier,
    scale: Float = 1.5f,
    width: Dp = 140.dp,
    height: Dp = 60.dp,
    strokeWidth: Dp = 0.dp,
    switchONColor: Color = MaterialTheme.colorScheme.switchONColor,
    switchOFFColor: Color = MaterialTheme.colorScheme.switchOFFColor,
    thumbColor: Color = MaterialTheme.colorScheme.mainBackgroundColor,
    gapBetweenThumbAndTrackEdge: Dp = 8.dp,
    mainViewmodel: MainViewmodel,
    connectionState: ConnectionState,
    onClick:()->Unit
) {

    val thumbRadius = 24.dp

    val thumbText =
        if (connectionState == ConnectionState.Connected) stringResource(R.string.on_text)
        else
            stringResource(R.string.off_text)

    val thumbTextColor = if (connectionState == ConnectionState.Connected) switchONColor else switchOFFColor


    // To move thumb, we need to calculate the position (along x axis)
    val animatePosition by animateFloatAsState(
        targetValue = if (connectionState == ConnectionState.Connected)
            with(LocalDensity.current) { (width - thumbRadius - gapBetweenThumbAndTrackEdge).toPx() }
        else
            with(LocalDensity.current) { (thumbRadius + gapBetweenThumbAndTrackEdge).toPx() },
        label = "",
        animationSpec = tween(500)
    )

    val textMeasurer = rememberTextMeasurer()



    Canvas(
        modifier = modifier
            .size(width = width, height = height)
            .scale(scale = scale)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        onClick()
                    }
                )
            }
    ) {

        // Track
        drawRoundRect(
            color = if (connectionState == ConnectionState.Connected) switchONColor else switchOFFColor,
            cornerRadius = CornerRadius(x = 40.dp.toPx(), y = 40.dp.toPx()),
            style = Fill,
        )

        // Thumb
        drawCircle(
            color = thumbColor,
            radius = thumbRadius.toPx(),
            center = Offset(
                x = animatePosition,
                y = size.height / 2
            )
        )


        // Text on Thumb
        val textLayoutResult =
            textMeasurer.measure(
                thumbText, style = TextStyle(
                    color = thumbTextColor,
                    fontSize = 10.sp,
                    fontFamily = FontFamily(Font(R.font.sourcesanspro_regular))
                )
            )

        val textOffsetX = (animatePosition - textLayoutResult.size.width / 2)
        val textOffsetY = (size.height - textLayoutResult.size.height) / 2

        drawText(
            textLayoutResult = textLayoutResult,
            topLeft = Offset(textOffsetX, textOffsetY),
        )

    }


}
