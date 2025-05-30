import SwiftUI

struct SettingsView: View {
	@StateObject private var passwordManager = PasswordManager.shared
	@StateObject private var settingsViewModel = SettingsViewViewModel()

	var body: some View {
		NavigationView {
			List {
				GitHubCellView()
				OptionsView()
				AppCellView()
				FooterView()
			}
			.navigationBarTitle("Settings", displayMode: .inline)
		}
		.tabItem {
			Label("Settings", systemImage: "gear")
		}
    }

	@ViewBuilder
	private func GitHubCellView() -> some View {
		Section("Developer") {
			HStack {
				Image(uiImage: settingsViewModel.image)
					.resizable()
					.aspectRatio(contentMode: .fit)
					.frame(width: 40, height: 40)
					.clipShape(.circle)

				Text(settingsViewModel.devName)
			}
			.frame(maxWidth: .infinity, alignment: .leading)
			.contentShape(.rect)
			.onTapGesture {
				settingsViewModel.openURL(settingsViewModel.developer.gitHubURL)
			}
		}
	}

	@ViewBuilder
	private func OptionsView() -> some View {
		Section("Options") {
			Group {
				Toggle("A-Z",isOn: passwordManager.includeUppercase)
				Toggle("0-9", isOn: passwordManager.includeNumbers)
				Toggle("!@#$%^&*", isOn: passwordManager.includeSymbols)
			}
			.tint(.auroraBlue)
		}
	}

	@ViewBuilder
	private func AppCellView() -> some View {
		Section("Other apps you may like") {
			ForEach(settingsViewModel.apps, id: \.rawValue) { app in
				VStack(alignment: .leading) {
					Text(app.name)

					Text(app.description)
						.font(.system(size: 10))
						.foregroundColor(.secondary)
				}
				.frame(maxWidth: .infinity, alignment: .leading)
				.contentShape(.rect)
				.onTapGesture {
					settingsViewModel.openURL(app.gitHubURL)
				}
			}
		}
	}

	@ViewBuilder
	private func FooterView() -> some View {
		Section(footer: Text("© 2024-\(Date.now.formatted(.dateTime.year())) Luki120")) {
			HStack {
				ForEach(settingsViewModel.fundingPlatforms, id: \.rawValue) { platform in
					platform.image
						.resizable()
						.aspectRatio(contentMode: .fit)
						.frame(width: 25, height: 25)
						.contentShape(.rect)
						.onTapGesture {
							settingsViewModel.openURL(platform.url)
						}
				}
			}
			.listRowBackground(Color.clear)
		}
		.frame(maxWidth: .infinity)
	}
}

#Preview {
    SettingsView()
}
