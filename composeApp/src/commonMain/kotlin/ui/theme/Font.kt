package ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun Typography(): Typography {
    return Typography(
        bodySmall = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        ),
        labelSmall = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal
        )
    )
}
