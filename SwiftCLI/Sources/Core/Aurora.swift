import ArgumentParser
import Shared

@main
private struct Aurora: ParsableCommand {
	static let configuration = CommandConfiguration(
		abstract: AuroraConfig.name,
		usage: AuroraConfig.usage,
		discussion: AuroraConfig.description,
		version: AuroraConfig.version
	)

	@Option(name: .shortAndLong, help: .lengthHelp)
	private var length = 25

	@Flag(help: .noUppercaseHelp)
	private var noUppercase = false

	@Flag(help: .noNumbersHelp)
	private var noNumbers = false

	@Flag(help: .noSymbolsHelp)
	private var noSymbols = false

	mutating func run() throws {
		do {
			guard 10...125 ~= length else { throw AuroraError.invalidRange }
			print(generatePassword())
		}
		catch let error as AuroraError {
			print(error.description)
		}
	}

	private func generatePassword() -> String {
		let passwordGeneratorRepository = PasswordGeneratorProvider.shared.providePasswordGeneratorRepository()
		passwordGeneratorRepository.includeUppercase = !noUppercase
		passwordGeneratorRepository.includeNumbers = !noNumbers
		passwordGeneratorRepository.includeSymbols = !noSymbols

		let password = passwordGeneratorRepository.getRandomString(length: Int32(length))
		var result = ""

		password.forEach { char in
			if char.isNumber {
				result.append(char.mediumPurple())
			}
			else if !char.isLetter {
				result.append(char.skyBlue())
			}
			else {
				result.append(char)
			}
		}

		return result
	}
}

private struct AuroraConfig {
	static let name = "Aurora - © 2024-\(Date.now.formatted(.dateTime.year())) Luki120".dodgerBlue()
	static let description = "Aurora's the CLI version of AuroraKMP, a password generator made with ".skyBlue()
	+ "Kotlin Multiplatform, ".mediumPurple()
	+ "targetting Android, Desktop, iOS (using SwiftUI) & Web.".skyBlue()
	static let usage = "aurora <options>".violet()
	static let version = "0.9.0"
}

private extension ArgumentHelp {
	static let lengthHelp = ArgumentHelp("The password's length, valid range is 10-125.")
	static let noUppercaseHelp = ArgumentHelp("Don't include uppercase characters.")
	static let noNumbersHelp = ArgumentHelp("Don't include numbers.")
	static let noSymbolsHelp = ArgumentHelp("Don't include symbols.")
}

private enum AuroraError: Error {
	case invalidRange

	var description: String {
		return "Aurora error: length must be between 10 & 125".red()
	}
}
