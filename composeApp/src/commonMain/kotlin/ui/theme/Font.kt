package ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import aurorakmp.composeapp.generated.resources.Res
import aurorakmp.composeapp.generated.resources.roboto_regular
import org.jetbrains.compose.resources.Font

@Composable
fun RobotoFamily() = FontFamily(Font(Res.font.roboto_regular))

@Composable
fun RobotoTypography(): Typography {
    val Roboto = RobotoFamily()

    return Typography(
        bodySmall = TextStyle(
            fontFamily = Roboto,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        ),
        labelSmall = TextStyle(
            fontFamily = Roboto,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal
        )
    )
}
