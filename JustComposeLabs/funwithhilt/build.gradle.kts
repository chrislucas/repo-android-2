plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    /*
        Compose Compiler Gradle plugin
        https://developer.android.com/develop/ui/compose/compiler
     */
    alias(libs.plugins.compose.compiler)

    id("com.google.devtools.ksp")
    id("com.google.gms.google-services")
    id("com.google.dagger.hilt.android")
    kotlin("plugin.serialization") version "2.0.21"
    id("kotlin-parcelize")
}

android {
    namespace = "com.br.funwithhilt"
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

configurations.all {
    resolutionStrategy {
        force(libs.androidx.espresso.idling.resource)
        force(libs.androidx.espresso.core)
        force(libs.androidx.junit)
    }
}

dependencies {

    // Import the BoM for the Firebase platform
    implementation(platform(libs.firebase.bom))

    // Add the dependencies for the Remote Config and Analytics libraries
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation(libs.google.firebase.config)
    implementation(libs.google.firebase.analytics)


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.graphics)

    // https://developer.android.com/develop/ui/compose/setup#kotlin_1

    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

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
    debugImplementation(libs.ui.tooling)

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

    // INICIO: paging jetpack compose
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
    annotationProcessor(libs.androidx.room.ktx)

    // hilt
    implementation("com.google.dagger:hilt-android:2.57.2")
    ksp("com.google.dagger:hilt-android-compiler:2.57.2")
    // https://developer.android.com/training/dependency-injection/hilt-testing#kts
    // For Robolectric tests.
    testImplementation("com.google.dagger:hilt-android-testing:2.57.2")
    // ...with Kotlin.
    kspTest("com.google.dagger:hilt-android-compiler:2.57.2")

    // For instrumented tests.
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.57.2")
    // ...with Kotlin.
    kspAndroidTest("com.google.dagger:hilt-android-compiler:2.57.2")

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


    androidTestImplementation(platform(libs.androidx.compose.bom))

    val inkVersion = "1.0.0-alpha05"

    implementation("androidx.ink:ink-authoring:$inkVersion")
    implementation("androidx.ink:ink-brush:$inkVersion")
    implementation("androidx.ink:ink-geometry:$inkVersion")
    implementation("androidx.ink:ink-nativeloader:$inkVersion")
    implementation("androidx.ink:ink-rendering:$inkVersion")
    implementation("androidx.ink:ink-strokes:$inkVersion")


    // FIM: paging jetpack compose


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
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // DEFAULT
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

kotlin {
    jvmToolchain(17)
}