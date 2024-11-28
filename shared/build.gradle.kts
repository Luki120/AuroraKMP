import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.multiplatform.swiftPackage)
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs()

    js(IR) {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions.target = "es2015"

        browser()
    }

    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    jvm()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
        macosX64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.multiplatform.settings)
        }
    }
}

android {
    namespace = "me.luki.aurorakmp.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

multiplatformSwiftPackage {
    packageName("AuroraKMPKit")
    swiftToolsVersion("5.10")
    targetPlatforms {
        macOS { v("13") }
    }
}
