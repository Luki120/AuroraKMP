import SwiftUI

struct ContentView: View {
	@State private var password = ""
	@State private var isAnimating = false
	@State private var shouldShowToast = false
	@AppStorage("passwordLength") private var passwordLength = 20.0

	private let passwordManager = PasswordManager.shared

	var body: some View {
		TabView {
			HomeView()
			SettingsView()
		}
		.tint(.auroraBlue)
	}

	@ViewBuilder
	private func HomeView() -> some View {
		NavigationView {
			VStack(spacing: 4) {
				Text(createAttributedString())
					.id("passwordText" + password)
					.multilineTextAlignment(.center)
					.transition(.opacity.animation(.easeInOut(duration: 0.5)))

				ButtonView(title: "Generate password") {
					password = passwordManager.generateRandomString(length: passwordLength)
				}
				.padding(.top, 10)

				ButtonView(title: "Copy password") {
					UIPasteboard.general.string = password

					isAnimating = true
					shouldShowToast = true
				}

				HStack {
					Slider(value: $passwordLength, in: 10...25) { _ in
						password = passwordManager.generateRandomString(
							length: passwordLength
						)
					}
					.frame(width: 220)
					.tint(.auroraBlue)

					Text(String(describing: Int(passwordLength.rounded())))
						.font(.caption)
						.foregroundStyle(.gray)
				}

				Spacer()

				VStack {
					ToastView(isAnimating: $isAnimating, shouldShowToast: $shouldShowToast)
						.frame(width: 120)
				}
				.frame(height: 100)
			}
			.task {
				password = passwordManager.generateRandomString(length: passwordLength)
			}
			.frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
			.navigationBarTitle("Home", displayMode: .inline)
			.padding()
		}
		.tabItem {
			Label("Home", systemImage: "house.fill")
		}
	}

	@ViewBuilder
	private func ButtonView(
		title: String,
		action: @escaping () -> Void
	) -> some View {
		Button(title, action: action)
			.frame(width: 220, height: 50)
			.background(Color.auroraBlue, in: .capsule)
			.tint(.primary)
	}

	private func createAttributedString() -> AttributedString {
		var attributedString = AttributedString(password)

		let numbersRanges = password.ranges(of: "\\d+", options: .regularExpression)
		let symbolsRanges = password.ranges(of: "\\W", options: .regularExpression)

		numbersRanges.forEach { attributedString[$0].foregroundColor = .auroraBlue }
		symbolsRanges.forEach {
			attributedString[$0].foregroundColor = .auroraPink
		}

		return attributedString
	}

}

private struct ToastView: UIViewRepresentable {
	@Binding var isAnimating: Bool
	@Binding var shouldShowToast: Bool

	func makeUIView(context: Context) -> UIToastView {
		return UIToastView(isAnimating: isAnimating)
	}

	func updateUIView(_ uiView: UIToastView, context: Context) {
		guard shouldShowToast else { return }
		uiView.animateToastView()

		shouldShowToast = false
	}
}

private final class UIToastView: UIView {
	private var isAnimating: Bool

	private lazy var toastView: UIView = {
		let view = UIView()
		view.alpha = 0
		view.backgroundColor = UIColor(Color.auroraBlue)
		view.transform = .init(scaleX: 0.1, y: 0.1)
		view.layer.cornerCurve = .continuous
		view.layer.cornerRadius = 20
		view.translatesAutoresizingMaskIntoConstraints = false
		addSubview(view)
		return view
	}()

	private lazy var toastViewLabel: UILabel = {
		let label = UILabel()
		label.font = .systemFont(ofSize: 14)
		label.text = "Copied"
		label.textColor = .label
		label.numberOfLines = 0
		label.textAlignment = .center
		label.translatesAutoresizingMaskIntoConstraints = false
		toastView.addSubview(label)
		return label
	}()

	// ! Lifecycle

	required init?(coder: NSCoder) {
		fatalError()
	}

	/// Designated initializer
	/// - Parameters:
	///		- isAnimating: Bool value to indicate wether the view is currently animating
	init(isAnimating: Bool) {
		self.isAnimating = isAnimating
		super.init(frame: .zero)

		setupToastView()
	}

	private func setupToastView() {
		NSLayoutConstraint.activate([
			toastView.centerYAnchor.constraint(equalTo: centerYAnchor),
			toastView.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 10),
			toastView.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -10),
			toastView.heightAnchor.constraint(equalToConstant: 40),

			toastViewLabel.centerXAnchor.constraint(equalTo: toastView.centerXAnchor),
			toastViewLabel.centerYAnchor.constraint(equalTo: toastView.centerYAnchor)
		])
	}
}

// MARK: - Public

extension UIToastView {
	/// Function to animate the toast view
	func animateToastView() {
		guard !isAnimating else { return }

		UIView.animate(withDuration: 0.5) {
			self.isAnimating = true

			self.toastView.alpha = 1
			self.toastView.transform = .init(scaleX: 1, y: 1)
		} completion: { _ in
			UIView.animate(withDuration: 0.5, delay: 1.5) {
				self.toastView.alpha = 0
				self.toastView.transform = .init(scaleX: 0.1, y: 0.1)
			} completion: { _ in
				self.isAnimating = false
			}
		}
	}
}

#Preview {
	ContentView()
}
