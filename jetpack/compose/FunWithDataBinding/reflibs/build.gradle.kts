plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.br.reflibs"
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

    api(project(":funwithcurrencyconversionapi"))
    api(project(":navfeatures"))
    api(project(":funwithprotodatastore"))
    api(project(":mapsandroidktx"))
    api(project(":navfeaturescompose"))
    api(project(":funwithconcatadapter"))
    api(project(":justconstraintlayoutstudies"))
    api(project(":funwithcamerax"))
    api(project(":funwithworkmanager"))
    api(project(":funwithshapes"))
    api(project(":recyclerviewcomponent"))
    api(project(":appdataandfile"))
    api(project(":funwithandroidktx"))
    api(project(":opencamerafromwebview"))
    api(project(":fileuploadclient"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    api(project(":funwithjetpackcompose"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}