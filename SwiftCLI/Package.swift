// swift-tools-version: 5.10
// The swift-tools-version declares the minimum version of Swift required to build this package.

import PackageDescription

let package = Package(
    name: "SwiftCLI",
    platforms: [.macOS(.v13)],
    dependencies: [
        .package(path: "AuroraKMPKit"),
        .package(url: "https://github.com/apple/swift-argument-parser", from: "1.3.0")
    ],
    targets: [
        .executableTarget(
            name: "SwiftCLI",
            dependencies: [
                .product(name: "AuroraKMPKit", package: "AuroraKMPKit"),
                .product(name: "ArgumentParser", package: "swift-argument-parser")
            ]
        ),
    ]
)
