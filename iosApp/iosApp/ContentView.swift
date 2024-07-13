import SwiftUI
import Shared

struct ContentView: View {
    @State private var password = ""
    @AppStorage("passwordLength") private var passwordLength = 20.0

    private let lightBlue = Color(red: 0.85, green: 0.90, blue: 1.0)
    private let utils = Utils()

    var body: some View {
        TabView {
            NavigationView {
                VStack(spacing: 4) {
                    Text(createAttributedString())
                        .id("passwordText" + password)
                        .multilineTextAlignment(.center)
                        .transition(.opacity.animation(.easeInOut(duration: 0.5)))

                    ButtonView(title: "Generate password") {
                        password = utils.getRandomString(length: Int32(passwordLength))
                    }
                    .padding(.top, 10)

                    ButtonView(title: "Copy password") {
                        UIPasteboard.general.string = password
                    }

                    HStack {
                        Slider(value: $passwordLength, in: 10...25) { _ in
                            password = utils.getRandomString(length: Int32(passwordLength))
                        }
                        .frame(width: 220)
                        .tint(lightBlue)

                        Text(String(Int(passwordLength.rounded())))
                            .font(.caption)
                            .foregroundStyle(.gray)
                    }
                }
                .task {
                    password = utils.getRandomString(length: Int32(passwordLength))
                }
                .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
                .padding()
            }
            .tabItem {
                Label("Home", systemImage: "house.fill")
            }

            NavigationView {
                VStack {
                    Text("Settings")
                }
            }
            .tabItem {
                Label("Settings", systemImage: "gear")
            }
        }
        .tint(lightBlue)
    }

    @ViewBuilder
    private func ButtonView(
        title: String,
        action: @escaping () -> Void
    ) -> some View {
        Button(title) {
            action()
        }
        .frame(width: 220, height: 50)
        .background(lightBlue)
        .clipShape(Capsule())
        .tint(.primary)
    }

    private func createAttributedString() -> AttributedString {
        var attributedString = AttributedString(password)

        let numbersRanges = password.ranges(of: "\\d+", options: .regularExpression)
        let symbolsRanges = password.ranges(of: "[\\W]", options: .regularExpression)

        numbersRanges.forEach { attributedString[$0].foregroundColor = .systemTeal }
        symbolsRanges.forEach {
            attributedString[$0].foregroundColor = .systemPink
        }

        return attributedString
    }

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

#Preview {
    ContentView()
}
