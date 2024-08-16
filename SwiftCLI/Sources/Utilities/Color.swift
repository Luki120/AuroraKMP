import Foundation

struct Color {
	private enum TerminalColor: UInt8 {
		case red = 9
		case dodgerBlue1 = 33
		case skyBlue2 = 111
		case mediumPurple1 = 135
		case violet = 177
	}

	private
	static func ansiForegroundColor(for color: TerminalColor) -> String {
		return "\u{001B}[38;5;" + String(describing: color.rawValue) + "m"
	}

	static let dodgerBlue = ansiForegroundColor(for: .dodgerBlue1)
	static let mediumPurple = ansiForegroundColor(for: .mediumPurple1)
	static let red = ansiForegroundColor(for: .red)
	static let skyBlue = ansiForegroundColor(for: .skyBlue2)
	static let violet = ansiForegroundColor(for: .violet)

	static let reset = "\u{001B}[0;0m"
}
