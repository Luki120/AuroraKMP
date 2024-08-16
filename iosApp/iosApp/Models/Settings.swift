import Foundation
import struct SwiftUI.Image


/// Enum to represent a developer for the GitHub cell
@frozen enum Developer: String {
	case luki = "Luki120"

	var devName: String { rawValue }

	var lukiIcon: String {
		return "https://avatars.githubusercontent.com/u/74214115?v=4"
	}

	var gitHubURL: URL? {
		return URL(string: "https://github.com/Luki120")
	}
}

/// Enum to represent each app for the app cell
@frozen enum App: String, CaseIterable {
	case areesha = "Areesha"
	case auroraKMP = "Aurora"

	var name: String { rawValue }

	var description: String {
		switch self {
			case .areesha: return "Keep track of your favorite TV shows"
			case .auroraKMP: return "CMP version of this app, targetting Android, Desktop & Web"
		}
	}

	var gitHubURL: URL? {
		switch self {
			case .areesha: return URL(string: "https://github.com/Luki120/Areesha")
			case .auroraKMP: return URL(string: "https://github.com/Luki120/AuroraKMP")
		}
	}

}

/// Enum to represent each funding platform for the funding cell
@frozen enum FundingPlatform: String, CaseIterable {
	case kofi = "Ko-fi"
	case paypal = "PayPal"

	var name: String { rawValue }

	var image: Image {
		switch self {
			case .kofi: return Image(.kofi)
			case .paypal: return Image(.payPal)
		}
	}

	var url: URL? {
		switch self {
			case .kofi: return URL(string: "https://ko-fi.com/Luki120")
			case .paypal: return URL(string: "https://paypal.me/Luki120")
		}
	}
}
