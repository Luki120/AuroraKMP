import Shared
import SwiftUI

/// Password manager singleton class
@MainActor
final class PasswordManager: ObservableObject {
	static let shared = PasswordManager()
	private(set) var passwordGeneratorRepository = PasswordGeneratorProvider.shared.providePasswordGeneratorRepository()

	/// Function to generate a random string with a given length
	/// - Parameters:
	/// 	- length: The given length
	/// - Returns: A random string
	func generateRandomString(length: Double) -> String {
		return passwordGeneratorRepository.getRandomString(length: Int32(length))
	}
}

extension PasswordManager {
	// MARK: - Bindings
	private func binding<T>(for keyPath: WritableKeyPath<PasswordGeneratorRepository, T>) -> Binding<T> {
		Binding<T>(
			get: {
				self.passwordGeneratorRepository[keyPath: keyPath]
			},
			set: {
				self.passwordGeneratorRepository[keyPath: keyPath] = $0
				self.objectWillChange.send()
			}
		)
	}

	var includeUppercase: Binding<Bool> {
		binding(for: \.includeUppercase)
	}
	var includeNumbers: Binding<Bool> {
		binding(for: \.includeNumbers)
	}
	var includeSymbols: Binding<Bool> {
		binding(for: \.includeSymbols)
	}
}
