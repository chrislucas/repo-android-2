
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)

    alias(libs.plugins.compose.compiler)
    id("com.google.devtools.ksp")
    id("com.google.gms.google-services")
    kotlin("plugin.serialization") version "2.0.21"
    id("kotlin-parcelize")
}

android {
    namespace = "com.br.funwithcameraxcompose"
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    /*
    // Compose Material 3

    https://developer.android.com/jetpack/androidx/releases/compose-material3

    Material Design 3 in Compose

     https://developer.android.com/develop/ui/compose/designsystems/material3
 */

    implementation("androidx.compose.material3:material3-window-size-class")
    implementation("androidx.compose.material3:material3-adaptive-navigation-suite")

    // https://developer.android.com/develop/ui/compose/layouts/adaptive/list-detail
    implementation("androidx.compose.material3.adaptive:adaptive")
    implementation("androidx.compose.material3.adaptive:adaptive-layout")
    implementation("androidx.compose.material3.adaptive:adaptive-navigation")


    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    implementation("io.coil-kt.coil3:coil-svg:3.3.0")

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
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.ui.graphics)
    testImplementation(libs.junit.jupiter)
    debugImplementation(libs.ui.tooling)

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.kotlinx.coroutines.test)

    // UI Tests
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.test.manifest)
    testImplementation(libs.robolectric)


    // Optional - Included automatically by material, only add when you need
    // the icons but not the material library (e.g. when using Material3 or a
    // custom design system based on Foundation)
    implementation(libs.androidx.material.icons.core)
    // Optional - Add full set of material icons
    implementation(libs.androidx.material.icons.extended)
    // Optional - Add window size utils
    implementation(libs.androidx.adaptive)

    // Optional - Integration with activities
    implementation(libs.androidx.activity.compose)
    // Optional - Integration with ViewModels
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    // Optional - Integration with LiveData
    implementation(libs.androidx.runtime.livedata)
    // Optional - Integration with RxJava
    implementation(libs.androidx.runtime.rxjava2)
    implementation(libs.androidx.runtime.rxjava3)

    // paging jetpack compose
    // https://developer.android.com/jetpack/androidx/releases/paging?authuser=1
    implementation(libs.androidx.paging.runtime)


    // alternatively - without Android dependencies for tests

    // optional - RxJava2 support
    implementation(libs.androidx.paging.rxjava2)

    // optional - RxJava3 support
    implementation(libs.androidx.paging.rxjava3)

    // optional - Guava ListenableFuture support
    implementation(libs.androidx.paging.guava)

    // optional - Jetpack Compose integration
    implementation(libs.androidx.paging.compose)


    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)


    // To use Kotlin Symbol Processing (KSP)
    ksp(libs.androidx.room.compiler)

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation(libs.androidx.room.ktx)

    // optional - RxJava2 support for Room
    implementation(libs.androidx.room.rxjava2)

    // optional - RxJava3 support for Room
    implementation(libs.androidx.room.rxjava3)

    // optional - Guava support for Room, including Optional and ListenableFuture
    implementation(libs.androidx.room.guava)

    // camera x
    // CameraX core library using the camera2 implementation
    val cameraxVersion = "1.5.1"
    // The following line is optional, as the core library is included indirectly by camera-camera2
    implementation("androidx.camera:camera-core:${cameraxVersion}")
    implementation("androidx.camera:camera-camera2:${cameraxVersion}")
    // If you want to additionally use the CameraX Lifecycle library
    implementation("androidx.camera:camera-lifecycle:${cameraxVersion}")
    // If you want to additionally use the CameraX VideoCapture library
    implementation("androidx.camera:camera-video:${cameraxVersion}")
    // If you want to additionally use the CameraX View class
    implementation("androidx.camera:camera-view:${cameraxVersion}")
    // If you want to additionally add CameraX ML Kit Vision Integration
    implementation("androidx.camera:camera-mlkit-vision:${cameraxVersion}")
    // If you want to additionally use the CameraX Extensions library
    implementation("androidx.camera:camera-extensions:${cameraxVersion}")


    // optional - Paging 3 Integration
    implementation(libs.androidx.room.paging)

    androidTestImplementation(platform(libs.androidx.compose.bom))


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

kotlin {
    jvmToolchain(17)
}
