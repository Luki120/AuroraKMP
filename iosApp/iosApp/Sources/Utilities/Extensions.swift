import Foundation
import struct SwiftUI.Color


extension Color {
	static let auroraBlue = Color(red: 0.64, green: 0.79, blue: 1.0)
	static let auroraPink = Color(red: 0.89, green: 0.57, blue: 0.77)
}

extension StringProtocol {
	func ranges<T: StringProtocol>(
		of stringToFind: T,
		options: String.CompareOptions = [],
		locale: Locale? = nil
	) -> [Range<AttributedString.Index>] {

		var ranges: [Range<String.Index>] = []
		var attributedRanges: [Range<AttributedString.Index>] = []
		let attributedString = AttributedString(self)

		while let result = range(
			of: stringToFind,
			options: options,
			range: (ranges.last?.upperBound ?? startIndex)..<endIndex,
			locale: locale
		) {
			ranges.append(result)
			let start = AttributedString.Index(result.lowerBound, within: attributedString)!
			let end = AttributedString.Index(result.upperBound, within: attributedString)!
			attributedRanges.append(start..<end)
		}
		return attributedRanges
	}
}
