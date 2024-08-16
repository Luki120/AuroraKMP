import SwiftUI

@main
struct iOSApp: SwiftUI.App {
	@StateObject private var viewModel = AuroraViewModel()

	var body: some Scene {
		WindowGroup {
			ContentView()
				.environmentObject(viewModel)
		}
	}
}
