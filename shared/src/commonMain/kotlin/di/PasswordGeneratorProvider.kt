package di

import data.PasswordGeneratorRepository
import data.PasswordGeneratorRepositoryImpl

object PasswordGeneratorProvider {
    fun providePasswordGeneratorRepository(): PasswordGeneratorRepository {
        return PasswordGeneratorRepositoryImpl(
            settingsRepository = SettingsProvider.provideSettingsRepository()
        )
    }
}
