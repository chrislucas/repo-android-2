plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)

    alias(libs.plugins.compose.compiler)
    id("com.google.devtools.ksp")
    id("com.google.gms.google-services")
    kotlin("plugin.serialization") version "2.0.21"
}

android {
    namespace = "com.br.funwithbroadcastreceiver"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }


}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    val core_version = "1.17.0"

    // Java language implementation
    implementation("androidx.core:core:$core_version")
    // Kotlin
    implementation("androidx.core:core-ktx:$core_version")

    // To use RoleManagerCompat
    implementation("androidx.core:core-role:1.1.0")

    // To use the Animator APIs
    implementation("androidx.core:core-animation:1.0.0")
    // To test the Animator APIs
    androidTestImplementation("androidx.core:core-animation-testing:1.0.0")

    // Optional - To enable APIs that query the performance characteristics of GMS devices.
    implementation("androidx.core:core-performance:1.0.0")

    // Optional - to use ShortcutManagerCompat to donate shortcuts to be used by Google
    implementation("androidx.core:core-google-shortcuts:1.1.0")

    // Optional - to support backwards compatibility of RemoteViews
    implementation("androidx.core:core-remoteviews:1.1.0")

    // Optional - APIs for SplashScreen, including compatibility helpers on devices prior Android 12
    implementation("androidx.core:core-splashscreen:1.2.0-rc01")

    implementation(libs.androidx.compose.ui.ui.test.junit4)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.junit.jupiter)
    lintChecks(libs.lint)
    implementation(libs.androidx.dynamicanimation)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // https://developer.android.com/develop/ui/compose/layouts/adaptive/list-detail
    implementation("androidx.compose.material3.adaptive:adaptive")
    implementation("androidx.compose.material3.adaptive:adaptive-layout")
    implementation("androidx.compose.material3.adaptive:adaptive-navigation")


    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.graphics.core)
    implementation(libs.androidx.graphics.path)
    implementation(libs.androidx.graphics.shapes)

    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    implementation("io.coil-kt.coil3:coil-svg:3.3.0")

    implementation(libs.material)


    // Import the BoM for the Firebase platform
    implementation(platform(libs.firebase.bom))

    // Add the dependencies for the Remote Config and Analytics libraries
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation(libs.google.firebase.config)
    implementation(libs.google.firebase.analytics)

    // compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.ui.viewbinding)
    // dependency without a version
    implementation(libs.androidx.runtime.tracing)

    // Capture a trace from terminal https://developer.android.com/develop/ui/compose/tooling/tracing#terminal
    implementation(libs.androidx.tracing.perfetto)
    implementation(libs.androidx.tracing.perfetto.binary)

    // Java language implementation
    implementation(libs.androidx.fragment)
    // Kotlin
    implementation(libs.androidx.fragment.ktx)

    // Glance setup

    // For AppWidgets support
    implementation(libs.androidx.glance.appwidget)
    // For interop APIs with Material 3
    implementation(libs.androidx.glance.material3)
    // For interop APIs with Material 2
    implementation(libs.androidx.glance.material)

    // Choose one of the following:
    // Material Design 3
    implementation(libs.material3)
    // or Material Design 2
    implementation(libs.androidx.material)
    // or skip Material Design and build directly on top of foundational components
    implementation(libs.androidx.foundation)
    // or only import the main APIs for the underlying toolkit systems,
    // such as input and measurement/layout
    implementation(libs.ui)

    // Android Studio Preview support
    implementation(libs.ui.tooling.preview)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit.jupiter)
    debugImplementation(libs.ui.tooling)

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.kotlinx.coroutines.test)

    // UI Tests
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.test.manifest)
    testImplementation(libs.robolectric)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

kotlin {
    jvmToolchain(17)
}