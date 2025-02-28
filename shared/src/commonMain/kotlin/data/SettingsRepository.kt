package data

import com.russhwolf.settings.Settings

internal interface SettingsRepository {
    object Constants {
        const val INCLUDE_UPPERCASE_KEY = "includeUppercase"
        const val INCLUDE_NUMBERS_KEY = "includeNumbers"
        const val INCLUDE_SYMBOLS_KEY = "includeSymbols"
    }
    fun retrieveData(key: String): Boolean
    fun saveData(key: String, value: Boolean)
}

internal class SettingsRepositoryImpl(private val settings: Settings): SettingsRepository {
    override fun retrieveData(key: String): Boolean {
        return settings.getBoolean(key = key, defaultValue = true)
    }

    override fun saveData(key: String, value: Boolean) {
        settings.putBoolean(key = key, value = value)
    }
}
