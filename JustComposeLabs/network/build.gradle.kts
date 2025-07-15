plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.br.network"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)


    api(libs.retrofit)
    api(libs.converter.gson)
    api(libs.logging.interceptor)
    api(libs.okhttp.urlconnection)

    // https://github.com/square/retrofit/tree/trunk/retrofit-adapters/rxjava3
    implementation(libs.adapter.rxjava2)
    implementation(libs.adapter.rxjava3)

    // https://github.com/ReactiveX/RxAndroid
    api(libs.rxandroid)
    // https://github.com/ReactiveX/RxJava
    api(libs.rxjava)

    debugApi(libs.leakcanary.android)


    api(libs.converter.gson)
    api(libs.logging.interceptor)
    api(libs.okhttp.urlconnection)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}