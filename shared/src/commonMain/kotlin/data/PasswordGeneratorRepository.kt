package data

interface PasswordGeneratorRepository {
    var includeUppercase: Boolean
    var includeNumbers: Boolean
    var includeSymbols: Boolean
    fun getRandomString(length: Int): String
}

internal class PasswordGeneratorRepositoryImpl(
    private val settingsRepository: SettingsRepository
): PasswordGeneratorRepository {
    override var includeUppercase: Boolean
        get() = settingsRepository.retrieveData(SettingsRepository.Constants.INCLUDE_UPPERCASE_KEY)
        set(value) {
            settingsRepository.saveData(key = SettingsRepository.Constants.INCLUDE_UPPERCASE_KEY, value = value)
        }

    override var includeNumbers: Boolean
        get() = settingsRepository.retrieveData(SettingsRepository.Constants.INCLUDE_NUMBERS_KEY)
        set(value) {
            settingsRepository.saveData(key = SettingsRepository.Constants.INCLUDE_NUMBERS_KEY, value = value)
        }

    override var includeSymbols: Boolean
        get() = settingsRepository.retrieveData(SettingsRepository.Constants.INCLUDE_SYMBOLS_KEY)
        set(value) {
            settingsRepository.saveData(key = SettingsRepository.Constants.INCLUDE_SYMBOLS_KEY, value = value)
        }

    override fun getRandomString(length: Int): String {
        val definitiveCharacters = buildString {
            append("abcdefghijklmnopqrstuvwxyz")
            if (includeUppercase) append("ABCDEFGHIJKLMNOPQRSTUVWXYZ")
            if (includeNumbers) append("0123456789")
            if (includeSymbols) append("!@#$%^&*()_+-=[]{}|;':,./<>?`~")
        }
        return buildString { repeat(length) { append(definitiveCharacters.random()) } }
    }
}
