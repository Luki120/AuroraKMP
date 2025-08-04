package components

import android.content.ClipData
import androidx.compose.ui.platform.ClipEntry

actual fun clipEntry(string: String): ClipEntry = ClipEntry(ClipData.newPlainText(string, string))
