package ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.application
import components.copyrightYear

@Composable
fun FooterView() {
    AttributedFooterText(string = "This $application is ", subString = "open source")
}

@Composable
private fun AttributedFooterText(string: String, subString: String) {
    val uriHandler = LocalUriHandler.current

    Box(modifier = Modifier.padding(bottom = 30.dp)) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row {
                Text(
                    text = string,
                    color = Color.Gray,
                    fontSize = MaterialTheme.typography.labelSmall.fontSize
                )
                Text(
                    text = subString,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = MaterialTheme.typography.labelSmall.fontSize,
                    modifier = Modifier
                        .clickable {
                            uriHandler.openUri("https://github.com/Luki120/AuroraKMP")
                        }
                        .pointerHoverIcon(PointerIcon.Hand)
                )
            }
            Text("© 2024-$copyrightYear Luki120", fontSize = 10.sp, color = Color.Gray)
        }
    }
}
