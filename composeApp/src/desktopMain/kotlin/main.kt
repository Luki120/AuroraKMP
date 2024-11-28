import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import di.PasswordGeneratorProvider.providePasswordGeneratorRepository
import di.SettingsProvider.provideSettingsRepository
import ui.presentation.App
import ui.presentation.AppViewModel

fun main() {
    val viewModel = AppViewModel(
        passwordGeneratorRepository = providePasswordGeneratorRepository(),
        settingsRepository = provideSettingsRepository()
    )

    application {
        Window(onCloseRequest = ::exitApplication, title = "AuroraKMP") {
            Box(
                modifier = Modifier
                    .background(if (isSystemInDarkTheme()) Color.Black else Color.White)
                    .fillMaxSize()
            ) {
                App(darkTheme = isSystemInDarkTheme(), dynamicColor = false, viewModel = viewModel)
            }
        }
    }
}
