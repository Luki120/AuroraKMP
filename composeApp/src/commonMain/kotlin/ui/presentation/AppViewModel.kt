package ui.presentation

import data.PasswordGeneratorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import data.SettingsRepository

class AppViewModel(
    private val passwordGeneratorRepository: PasswordGeneratorRepository,
    private val settingsRepository: SettingsRepository
) {
    private var _passwordState = MutableStateFlow(PasswordState())
    val passwordState: StateFlow<PasswordState> = _passwordState

    fun getRandomString(length: Int): String {
        return passwordGeneratorRepository.getRandomString(length = length)
    }

    fun handlePasswordIntent(intent: PasswordIntent) {
        when (intent) {
            is PasswordIntent.IncludeUppercase -> {
                settingsRepository.saveData(
                    key = SettingsRepository.Constants.INCLUDE_UPPERCASE_KEY,
                    value = intent.includeUppercase
                )
                _passwordState.update { it.copy(includeUppercase = intent.includeUppercase) }
                passwordGeneratorRepository.includeUppercase = intent.includeUppercase
            }
            is PasswordIntent.IncludeNumbers -> {
                settingsRepository.saveData(
                    key = SettingsRepository.Constants.INCLUDE_NUMBERS_KEY,
                    value = intent.includeNumbers
                )
                _passwordState.update { it.copy(includeNumbers = intent.includeNumbers) }
                passwordGeneratorRepository.includeNumbers = intent.includeNumbers
            }
            is PasswordIntent.IncludeSymbols -> {
                settingsRepository.saveData(
                    key = SettingsRepository.Constants.INCLUDE_SYMBOLS_KEY,
                    value = intent.includeSymbols
                )
                _passwordState.update { it.copy(includeSymbols = intent.includeSymbols) }
                passwordGeneratorRepository.includeSymbols = intent.includeSymbols
            }
            is PasswordIntent.LaunchApp -> {
                val includeUppercase = settingsRepository.retrieveData(key = SettingsRepository.Constants.INCLUDE_UPPERCASE_KEY)
                val includeNumbers = settingsRepository.retrieveData(key = SettingsRepository.Constants.INCLUDE_NUMBERS_KEY)
                val includeSymbols = settingsRepository.retrieveData(key = SettingsRepository.Constants.INCLUDE_SYMBOLS_KEY)

                _passwordState.update { it.copy(includeUppercase = includeUppercase) }
                _passwordState.update { it.copy(includeNumbers = includeNumbers) }
                _passwordState.update { it.copy(includeSymbols = includeSymbols) }

                passwordGeneratorRepository.includeUppercase = includeUppercase
                passwordGeneratorRepository.includeNumbers = includeNumbers
                passwordGeneratorRepository.includeSymbols = includeSymbols
            }
        }
    }
}

data class PasswordState(
    var includeUppercase: Boolean = true,
    val includeNumbers: Boolean = true,
    val includeSymbols: Boolean = true
)

sealed class PasswordIntent {
    data class IncludeUppercase(val includeUppercase: Boolean): PasswordIntent()
    data class IncludeNumbers(val includeNumbers: Boolean): PasswordIntent()
    data class IncludeSymbols(val includeSymbols: Boolean): PasswordIntent()
    data object LaunchApp: PasswordIntent()
}
