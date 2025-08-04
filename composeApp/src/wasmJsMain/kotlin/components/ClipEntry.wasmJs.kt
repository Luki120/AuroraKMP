package components

import androidx.compose.ui.platform.ClipEntry

actual fun clipEntry(string: String): ClipEntry = ClipEntry.withPlainText(string)
