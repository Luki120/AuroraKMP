import Shared
import SwiftUI

/// Password manager singleton class
@MainActor
final class PasswordManager: ObservableObject {
	static let shared = PasswordManager()

	@Published var repository = PasswordGeneratorProvider.shared.providePasswordGeneratorRepository()

	/// Function to generate a random string with a given length
	/// - Parameter length: A `Double` that represents the length
	/// - Returns: `String`
	func generateRandomString(length: Double) -> String {
		return repository.getRandomString(length: Int32(length))
	}
}
