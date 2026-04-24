plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.br.coroutinelab"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.br.coroutinelab"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    val workVersion = "2.11.1"

    // (Java only)
    implementation("androidx.work:work-runtime:$workVersion")

    // Kotlin + coroutines
    implementation("androidx.work:work-runtime-ktx:$workVersion")

    // optional - RxJava2 support
    implementation("androidx.work:work-rxjava2:$workVersion")

    // optional - GCMNetworkManager support
    implementation("androidx.work:work-gcm:$workVersion")

    // optional - Test helpers
    androidTestImplementation("androidx.work:work-testing:$workVersion")

    // optional - Multiprocess support
    implementation("androidx.work:work-multiprocess:$workVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")

    // Optional: ViewModelScope and LifecycleScope KTX extensions
    // These provide lifecycle-aware scopes like viewModelScope and lifecycleScope
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.10.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.10.0")

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:2.10.0")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:2.10.0")

    // Optional: Android Architecture Components testing
    testImplementation("androidx.arch.core:core-testing:2.10.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}

kotlin {
    jvmToolchain(17)
}
