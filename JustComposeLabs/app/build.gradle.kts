plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    /*
        Compose Compiler Gradle plugin
        https://developer.android.com/develop/ui/compose/compiler
     */
    alias(libs.plugins.compose.compiler)
    id("com.google.devtools.ksp")
    id("com.google.gms.google-services")
    kotlin("plugin.serialization") version "2.0.21"
    // Add the Crashlytics Gradle plugin
    id("com.google.firebase.crashlytics")
    id("kotlin-parcelize")
}

android {
    namespace = "com.br.justcomposelabs"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.br.justcomposelabs"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // This block is different from the one you use to link Gradle
        // to your CMake or ndk-build script.
        externalNativeBuild {
            // For ndk-build, instead use the ndkBuild block.
            cmake {
                // Passes optional arguments to CMake.
                arguments += listOf("-DANDROID_SUPPORT_FLEXIBLE_PAGE_SIZES=ON")
            }
        }
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

    packagingOptions {
        jniLibs {
            useLegacyPackaging = true
        }
    }
}

configurations.all {
    resolutionStrategy {
        force(libs.androidx.espresso.idling.resource)
        force(libs.androidx.espresso.core)
        force(libs.androidx.junit)
    }
}

dependencies {
    implementation(project(":referencelinbraries"))

    // View
    // https://github.com/material-components/material-components-android/blob/master/docs/getting-started.md
    // https://developer.android.com/develop/ui/views/theming/look-and-feel
    implementation("com.google.android.material:material:1.13.0")

    // end views
    implementation(libs.androidx.compose.ui.ui.test.junit4)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.compose.material3.material3)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.fragment.compose)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.foundation.layout)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    annotationProcessor(libs.androidx.room.ktx)
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

    /*
        Select colors with the Palette API
        https://developer.android.com/develop/ui/views/graphics/palette-colors
     */
    implementation("androidx.palette:palette:1.0.0")


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


    // camera x
    implementation(libs.androidx.camera.core)
    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.camera.extensions)
    implementation(libs.androidx.camera.video)
    implementation(libs.androidx.camera.view)

    // ktx
    implementation(libs.androidx.collection.ktx)

    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.graphics.core)
    implementation(libs.androidx.graphics.path)
    implementation(libs.androidx.graphics.shapes)

    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    implementation("io.coil-kt.coil3:coil-svg:3.3.0")

    implementation(libs.material)
    implementation(libs.timber)

    // Import the BoM for the Firebase platform
   implementation(platform(libs.firebase.bom))

    // Add the dependencies for the Remote Config and Analytics libraries
    // When using the BoM, you don't specify versions in Firebase library dependencies
   implementation(libs.google.firebase.config)
   implementation(libs.google.firebase.analytics)
   implementation(libs.google.firebase.crashlytics)

    // compose
    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

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
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:6.0.1")
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



    // optional - Paging 3 Integration
    implementation(libs.androidx.room.paging)






    val inkVersion = "1.0.0-alpha05"

    implementation("androidx.ink:ink-authoring:$inkVersion")
    implementation("androidx.ink:ink-brush:$inkVersion")
    implementation("androidx.ink:ink-geometry:$inkVersion")
    implementation("androidx.ink:ink-nativeloader:$inkVersion")
    implementation("androidx.ink:ink-rendering:$inkVersion")
    implementation("androidx.ink:ink-strokes:$inkVersion")

    implementation("androidx.hilt:hilt-work:1.3.0")
    // When using Kotlin.
    ksp("androidx.hilt:hilt-compiler:1.3.0")
    // When using Java.
    annotationProcessor("androidx.hilt:hilt-compiler:1.3.0")


    /*
        Getting started with WorkManager
        https://developer.android.com/develop/background-work/background-tasks/persistent/getting-started
     */
    implementation(libs.androidx.ui.viewbinding)
    implementation(libs.androidx.webkit)
    implementation(libs.androidx.work.gcm)
    implementation(libs.androidx.work.multiprocess)
    implementation(libs.androidx.work.runtime)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.work.rxjava2 )
    implementation(libs.androidx.work.testing)




    // Test rules and transitive dependencies:
    androidTestImplementation(libs.ui.test.junit4)
    // Needed for createComposeRule(), but not for createAndroidComposeRule<YourActivity>():
    debugImplementation(libs.ui.test.manifest)
    testImplementation(libs.robolectric)
    testImplementation(libs.androidx.paging.common)
    // optional - Test helpers
    testImplementation(libs.androidx.room.testing)

    /*
        https://github.com/android/testing-samples
     */

    // Optional -- Robolectric environment
    testImplementation("androidx.test:core:1.7.0")
    testImplementation("androidx.test:core-ktx:1.7.0")
    // Optional -- Mockito framework
    testImplementation("org.mockito:mockito-core:5.11.0")
    // Optional -- mockito-kotlin
    testImplementation("org.mockito.kotlin:mockito-kotlin:6.0.0")
    testImplementation(libs.mockk)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(17)
}
