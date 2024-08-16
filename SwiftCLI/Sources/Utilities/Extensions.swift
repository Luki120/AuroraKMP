import Foundation

extension Character {
	func mediumPurple() -> String {
		return "\(Color.mediumPurple)\(self)\(Color.reset)"
	}
	
	func skyBlue() -> String {
		return "\(Color.skyBlue)\(self)\(Color.reset)"
	}
}

extension String {
	func dodgerBlue() -> String {
		return Color.dodgerBlue + self + Color.reset
	}
	
	func mediumPurple() -> String {
		return Color.mediumPurple + self + Color.reset
	}
	
	func red() -> String {
		return Color.red + self + Color.reset
	}
	
	func skyBlue() -> String {
		return Color.skyBlue + self + Color.reset
	}
	
	func violet() -> String {
		return Color.violet + self + Color.reset
	}
}
