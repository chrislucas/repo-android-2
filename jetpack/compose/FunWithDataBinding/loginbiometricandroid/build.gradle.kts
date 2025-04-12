plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    id("com.google.devtools.ksp")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.br.loginbiometric"
    compileSdk = 35

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
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // compose
    implementation(platform(libs.androidx.compose.bom.v20240901))
    implementation(libs.androidx.ui.viewbinding)

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

    // Kotlin
    implementation("androidx.biometric:biometric:1.4.0-alpha03")

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
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    annotationProcessor(libs.androidx.room.compiler)
    debugImplementation(libs.androidx.ui.tooling)


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

    implementation(libs.coil3.coil.compose)
    implementation(libs.coil3.coil.network.okhttp)


    androidTestImplementation(platform(libs.androidx.compose.bom.v20240901))

    // Test rules and transitive dependencies:
    androidTestImplementation(libs.ui.test.junit4)
    // Needed for createComposeRule(), but not for createAndroidComposeRule<YourActivity>():
    debugImplementation(libs.ui.test.manifest)
    testImplementation(libs.robolectric)
    testImplementation(libs.androidx.paging.common)
    // optional - Test helpers
    testImplementation(libs.androidx.room.testing)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}