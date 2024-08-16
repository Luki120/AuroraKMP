// swift-tools-version:5.10
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
