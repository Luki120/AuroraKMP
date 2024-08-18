import Shared
import SwiftUI

/// App view model class
final class AuroraViewModel: ObservableObject {

	@AppStorage("includeUppercase") var includeUppercase = true
	@AppStorage("includeNumbers") var includeNumbers = true
	@AppStorage("includeSymbols") var includeSymbols = true

	private let passwordGeneratorRepository = PasswordGeneratorProvider.shared.providePasswordGeneratorRepository()

	/// Function to generate a random string with a given length
	/// - Parameters:
	/// 	- withLength: The given length
	/// - Returns: The random string
	func generateRandomString(withLength length: Int32) -> String {
		passwordGeneratorRepository.includeUppercase = includeUppercase
		passwordGeneratorRepository.includeNumbers = includeNumbers
		passwordGeneratorRepository.includeSymbols = includeSymbols

		return passwordGeneratorRepository.getRandomString(length: length)
	}

}
