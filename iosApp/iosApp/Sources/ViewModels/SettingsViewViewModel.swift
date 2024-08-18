import Foundation
import UIKit.UIImage


extension SettingsView {
	/// View model class for SettingsView
	final class SettingsViewViewModel: ObservableObject {

		@Published
		private(set) var image = UIImage()

		var devName: String { developer.devName }

		let apps = App.allCases
		let fundingPlatforms = FundingPlatform.allCases

		let developer: Developer

		/// Designated initializer
		/// - Parameters:
		/// 	- developer: The Developer object
		init(developer: Developer = .luki) {
			self.developer = developer

			Task {
				do {
					let image = try await fetchImage()

					await MainActor.run {
						self.image = image
					}
				}
				catch {
					print(error.localizedDescription)
				}
			}
		}

		private func fetchImage() async throws -> UIImage {
			guard let url = URL(string: developer.lukiIcon) else { throw URLError(.badURL) }

			let (data, _) = try await URLSession.shared.data(from: url)
			guard let image = UIImage(data: data) else { throw URLError(.badServerResponse) }

			return image
		}

	}
}

// MARK: - Public

extension SettingsView.SettingsViewViewModel {
	/// Function to open the  specified url
	func openURL(_ url: URL?) {
		guard let url else { return }
		UIApplication.shared.open(url)
	}
}
