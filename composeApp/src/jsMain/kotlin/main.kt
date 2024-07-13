import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.CanvasBasedWindow
import di.PasswordGeneratorProvider.providePasswordGeneratorRepository
import di.SettingsProvider.provideSettingsRepository
import org.jetbrains.skiko.wasm.onWasmReady
import ui.presentation.App
import ui.presentation.AppViewModel

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    onWasmReady {
        val viewModel = AppViewModel(
            passwordGeneratorRepository = providePasswordGeneratorRepository(),
            settingsRepository = provideSettingsRepository()
        )
        CanvasBasedWindow(title = "AuroraKMP", canvasElementId = "ComposeTarget") {
            Box(
                modifier = Modifier
                    .background(if (isSystemInDarkTheme()) Color.Black else Color.White)
                    .fillMaxSize()
            ) {
                App(darkTheme = isSystemInDarkTheme(), dynamicColor = false, viewModel = remember { viewModel })
            }
        }
    }
}
