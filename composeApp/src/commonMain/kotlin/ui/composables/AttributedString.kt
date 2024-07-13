package ui.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString

@Composable
fun AttributedString(string: String): AnnotatedString {
    val letterMatches =  Regex("[A-Za-z]").findAll(string)
    val numberMatches = Regex("\\d+").findAll(string)
    val symbolMatches = Regex("\\W").findAll(string)

    val lightPink = Color(0xFFE292C4)

    return buildAnnotatedString {
        letterMatches.forEach {
            addStyle(
                style = SpanStyle(color = MaterialTheme.colorScheme.onSurface),
                start = it.range.first,
                end = it.range.last + 1
            )
        }
        numberMatches.forEach {
            addStyle(
                style = SpanStyle(color = MaterialTheme.colorScheme.secondary),
                start = it.range.first,
                end = it.range.last + 1
            )
        }
        symbolMatches.forEach {
            addStyle(
                style = SpanStyle(color = lightPink),
                start = it.range.first,
                end = it.range.last + 1
            )
        }
        append(string)
    }
}
