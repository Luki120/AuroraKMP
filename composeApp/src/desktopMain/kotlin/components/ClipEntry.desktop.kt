package components

import androidx.compose.ui.platform.ClipEntry
import java.awt.datatransfer.StringSelection

actual fun clipEntry(string: String): ClipEntry = ClipEntry(StringSelection(string))
