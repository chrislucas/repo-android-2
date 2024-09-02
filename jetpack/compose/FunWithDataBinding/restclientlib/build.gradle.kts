plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.br.restclientlib"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    api(libs.kotlinx.coroutines.core)
    api(libs.kotlinx.coroutines.android)


    api(libs.retrofit)
    // https://github.com/square/retrofit/tree/trunk/retrofit-adapters/rxjava3
    implementation(libs.adapter.rxjava2)
    implementation(libs.adapter.rxjava3)

    api(libs.converter.gson)
    api(libs.logging.interceptor)


    // optional - RxJava2 support
    api(libs.androidx.datastore.preferences.rxjava2)

    // optional - RxJava3 support
    api(libs.androidx.datastore.preferences.rxjava3)

    api(libs.androidx.datastore)

    // optional - RxJava2 support
    api(libs.androidx.datastore.rxjava2)

    // optional - RxJava3 support
    api(libs.androidx.datastore.rxjava3)

    // https://github.com/ReactiveX/RxAndroid
    api(libs.rxandroid)
    // https://github.com/ReactiveX/RxJava
    api(libs.rxjava)

    debugApi(libs.leakcanary.android)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}