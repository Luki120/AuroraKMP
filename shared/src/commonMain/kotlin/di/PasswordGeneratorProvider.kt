package di

import data.PasswordGeneratorRepository
import data.PasswordGeneratorRepositoryImpl

object PasswordGeneratorProvider {
    fun providePasswordGeneratorRepository(): PasswordGeneratorRepository = PasswordGeneratorRepositoryImpl()
}
