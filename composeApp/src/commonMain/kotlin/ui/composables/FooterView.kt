package ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.application

@Composable
fun FooterView() {
    AttributedFooterText(string = "This $application is ", subString = "open source")
}

@Composable
private fun AttributedFooterText(string: String, subString: String) {
    val uriHandler = LocalUriHandler.current

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.Gray)) {
            append(string)
        }
        pushStringAnnotation(tag = subString, annotation = "https://github.com/Luki120/AuroraKMP")
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
            append(subString)
        }
        pop()
    }

    Box(modifier = Modifier.padding(bottom = 30.dp)) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ClickableText(
                annotatedString,
                onClick = { offset ->
                    annotatedString.getStringAnnotations(tag = "open source", start = offset, end = offset)
                        .firstOrNull()?.let {
                            uriHandler.openUri(it.item)
                        }
                },
                modifier = Modifier.pointerHoverIcon(PointerIcon.Hand)
            )
            Text("© 2024 Luki120", fontSize = 10.sp, color = Color.Gray)
        }
    }
}
