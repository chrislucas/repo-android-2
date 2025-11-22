plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)

    kotlin("plugin.serialization") version "2.0.21"
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

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.content.negotiation)
    // https://ktor.io/docs/client-webrtc.html#add-dependencies
    // https://hackernoon.com/real-time-communication-with-webrtc-on-android-f96cdcfc4771
    // min sdk 28
    // implementation(libs.ktor.client.webrtc)
    implementation("io.ktor:ktor-serialization-gson:3.3.1")

    implementation("io.ktor:ktor-serialization-kotlinx-protobuf:3.3.1")

    implementation("io.ktor:ktor-serialization-kotlinx-json:3.3.1")


    api(libs.converter.gson)
    api(libs.logging.interceptor)
    api(libs.okhttp.urlconnection)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}