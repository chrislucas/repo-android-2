plugins {
    alias(libs.plugins.android.library)
    //alias(libs.plugins.jetbrains.kotlin.android)
}

/*
    Modularization learning journey
    https://github.com/android/nowinandroid/blob/main/docs/ModularizationLearningJourney.md

    Jetpack samples
    https://developer.android.com/jetpack/samples

    https://developer.android.com/develop/ui/compose/designsystems
    https://medium.com/androiddevelopers/building-jetcaster-on-all-form-factors-8e3418eeac13
 */

android {
    namespace = "com.br.clonejetcaster.core.data"
    compileSdk = 37

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

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

kotlin {
    jvmToolchain(17)
}