import Foundation
import UIKit.UIImage
import func SwiftUI.withAnimation

extension SettingsView {
	/// View model class for SettingsView
	@MainActor
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
			fetchImage()
		}

		private func fetchImage() {
			Task.detached(priority: .background) {
				guard let url = URL(string: self.developer.lukiIcon) else { return }

				do {
					let (data, _) = try await URLSession.shared.data(from: url)
					guard let image = UIImage(data: data) else { return }

					await MainActor.run {
						withAnimation(.smooth) {
							self.image = image
						}
					}
				}
				catch {
					print(error.localizedDescription)
				}
			}
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
