plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.br.funwithmlkit"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

}

configurations.all {
    resolutionStrategy {
        force(libs.androidx.junit)
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    /*
        https://developers.google.com/ml-kit/genai/summarization/android

        LiteRT in Google Play services
        https://ai.google.dev/edge/litert/android/play_services

        kotlindl
        https://resources.jetbrains.com/storage/products/kotlinconf-2023/Deep%20Dive%20Into%20Deep%20Learning%20With%20KotlinDL.pdf
        https://github.com/Kotlin/kotlindl
        https://kotlinlang.org/docs/kotlin-notebook-set-up-env.html#set-up-the-environment
     */
    implementation("com.google.mlkit:genai-summarization:1.0.0-beta1")

    implementation("com.google.mlkit:face-mesh-detection:16.0.0-beta1")


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

kotlin {
    jvmToolchain(17)
}