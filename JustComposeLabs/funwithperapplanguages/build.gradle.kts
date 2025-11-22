plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.br.funwithperapplanguages"
    compileSdk = 36

    // This block is specifically for configuring Android resources
    androidResources {
        // Set this to true to automatically generate the locale_config.xml
        // file based on the resources in your project.
        // This is the key setting for per-app language preferences.
        // generateLocaleConfig = true
    }

    defaultConfig {
        /*
            Esse projeto nao vai funcionar aqui porque o min sdk é maior do que o modulo app
         */
        minSdk = 33
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

    /*
        https://developer.android.com/guide/topics/resources/app-languages#kts
        https://android-developers.googleblog.com/2022/11/per-app-language-preferences-part-1.html
     */
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation(libs.androidx.appcompat)
    // For loading and tinting drawables on older versions of the platform
    implementation(libs.androidx.appcompat.resources)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}


kotlin {
    jvmToolchain(17)
}