// swift-tools-version:6.0
import PackageDescription

let package = Package(
    name: "AuroraKMPKit",
    platforms: [
        .macOS(.v13)
    ],
    products: [
        .library(
            name: "AuroraKMPKit",
            targets: ["AuroraKMPKit"]
        ),
    ],
    targets: [
        .binaryTarget(
            name: "AuroraKMPKit",
            path: "./AuroraKMPKit.xcframework"
        ),
    ]
)
