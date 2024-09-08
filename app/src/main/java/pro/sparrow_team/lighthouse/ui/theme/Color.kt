package pro.sparrow_team.lighthouse.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


val ColorScheme.mainBackgroundColor: Color
    get() =  Color(0xFF171D20)

val ColorScheme.switchONColor: Color
    get() =  Color(0xFFFDDC2E)

val ColorScheme.switchOFFColor: Color
    get() =  Color(0xFFFFFFFF)

val ColorScheme.lighthouseBrown: Color
    get() =  Color(0xCCFFB786)

val ColorScheme.lightONColor1: Color
    get() =  Color(0xFFFEE1CE)

val ColorScheme.lightONColor2: Color
    get() =  Color(0xFFFFF7F2)


val ColorScheme.transparentBrush: Brush
    get() =  Brush.verticalGradient(
        colors = listOf(
            Color.Transparent,
            Color.Transparent,
        ),
    )