package me.luki.aurorakmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.remember
import di.PasswordGeneratorProvider.providePasswordGeneratorRepository
import di.SettingsProvider.provideSettingsRepository
import ui.presentation.App
import ui.presentation.AppViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = AppViewModel(
            passwordGeneratorRepository = providePasswordGeneratorRepository(),
            settingsRepository = provideSettingsRepository()
        )

        setContent {
            App(darkTheme = isSystemInDarkTheme(), dynamicColor = true, viewModel = remember { viewModel })
        }
    }
}
