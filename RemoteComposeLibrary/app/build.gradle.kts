plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.br.remotecomposelibrary"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.br.remotecomposelibrary"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
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

    // https://developer.android.com/jetpack/androidx/releases/compose-remote?hl=pt-br
    implementation("androidx.compose.remote:remote-core:1.0.0-alpha07")

    // Use to create Remote Compose documents
    implementation("androidx.compose.remote:remote-creation:1.0.0-alpha07")
    implementation("androidx.compose.remote:remote-creation-core:1.0.0-alpha07")
    implementation("androidx.compose.remote:remote-creation-android:1.0.0-alpha07")
    implementation("androidx.compose.remote:remote-creation-jvm:1.0.0-alpha07")
    implementation("androidx.compose.remote:remote-creation-compose:1.0.0-alpha07")

    // Use to render a Remote Compose document
    implementation("androidx.compose.remote:remote-player-core:1.0.0-alpha07")
    implementation("androidx.compose.remote:remote-player-view:1.0.0-alpha07")

    implementation("androidx.compose.remote:remote-tooling-preview:1.0.0-alpha07")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
