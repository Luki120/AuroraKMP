package data

interface PasswordGeneratorRepository {
    var includeUppercase: Boolean
    var includeNumbers: Boolean
    var includeSymbols: Boolean
    fun getRandomString(length: Int): String
}

internal class PasswordGeneratorRepositoryImpl: PasswordGeneratorRepository {
    private val numberCharacters = "0123456789"
    private val specialCharacters = "!@#$%^&*()_+-=[]{}|;':,./<>?`~"
    private val uppercaseCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    private val characters = "abcdefghijklmnopqrstuvwxyz"

    override var includeUppercase = true
    override var includeNumbers = true
    override var includeSymbols = true

    override fun getRandomString(length: Int): String {
        val definitiveCharacters = buildString {
            append(characters)
            if (includeUppercase) append(uppercaseCharacters)
            if (includeNumbers) append(numberCharacters)
            if (includeSymbols) append(specialCharacters)
        }
        return buildString { repeat(length) { append(definitiveCharacters.random()) } }
    }
}
