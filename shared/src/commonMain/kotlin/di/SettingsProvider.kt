package di

import com.russhwolf.settings.Settings
import data.SettingsRepository
import data.SettingsRepositoryImpl

object SettingsProvider {
    fun provideSettingsRepository(): SettingsRepository = SettingsRepositoryImpl(settings = Settings())
}
