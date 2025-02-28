package ui.presentation

import data.PasswordGeneratorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AppViewModel(private val passwordGeneratorRepository: PasswordGeneratorRepository) {
    private var _passwordState = MutableStateFlow(PasswordState())
    val passwordState: StateFlow<PasswordState> = _passwordState

    fun getRandomString(length: Int): String {
        return passwordGeneratorRepository.getRandomString(length = length)
    }

    fun handlePasswordIntent(intent: PasswordIntent) {
        when (intent) {
            is PasswordIntent.IncludeUppercase -> {
                _passwordState.update { it.copy(includeUppercase = intent.includeUppercase) }
                passwordGeneratorRepository.includeUppercase = intent.includeUppercase
            }
            is PasswordIntent.IncludeNumbers -> {
                _passwordState.update { it.copy(includeNumbers = intent.includeNumbers) }
                passwordGeneratorRepository.includeNumbers = intent.includeNumbers
            }
            is PasswordIntent.IncludeSymbols -> {
                _passwordState.update { it.copy(includeSymbols = intent.includeSymbols) }
                passwordGeneratorRepository.includeSymbols = intent.includeSymbols
            }
            is PasswordIntent.LaunchApp -> {
                _passwordState.update {
                    it.copy(
                        includeUppercase = passwordGeneratorRepository.includeUppercase,
                        includeNumbers = passwordGeneratorRepository.includeNumbers,
                        includeSymbols = passwordGeneratorRepository.includeSymbols
                    )
                }
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
    data class IncludeUppercase(val includeUppercase: Boolean) : PasswordIntent()
    data class IncludeNumbers(val includeNumbers: Boolean) : PasswordIntent()
    data class IncludeSymbols(val includeSymbols: Boolean) : PasswordIntent()
    data object LaunchApp : PasswordIntent()
}
