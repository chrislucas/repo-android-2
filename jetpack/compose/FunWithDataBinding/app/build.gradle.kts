plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.gms.google-services")
    id("kotlin-parcelize")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.br.funwithdatabinding"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.br.funwithdatabinding"
        minSdk = 26
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
        }
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
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        dataBinding = true
        buildConfig = true
        viewBinding = true
        compose = true
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {

    implementation(project(":reflibs"))
    implementation(project(":restclientlib"))
    implementation(project(":wrapperviewmodel"))

    api(libs.kotlinx.coroutines.core)
    api(libs.kotlinx.coroutines.android)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation(libs.androidx.graphics.core)
    implementation(libs.androidx.graphics.path)
    implementation(libs.androidx.graphics.shapes)

    implementation(libs.okhttp.urlconnection)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.picasso)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom.v20240901))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.junit.jupiter)
    androidTestImplementation(platform(libs.androidx.compose.bom.v20240901))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    annotationProcessor(libs.androidx.databinding.compiler)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    kapt(libs.compiler)

    val paging_version = "3.3.2"

    implementation("androidx.paging:paging-runtime:$paging_version")

    // alternatively - without Android dependencies for tests
    testImplementation("androidx.paging:paging-common:$paging_version")

    // optional - RxJava2 support
    implementation("androidx.paging:paging-rxjava2:$paging_version")

    // optional - RxJava3 support
    implementation("androidx.paging:paging-rxjava3:$paging_version")

    // optional - Guava ListenableFuture support
    implementation("androidx.paging:paging-guava:$paging_version")

    // optional - Jetpack Compose integration
    implementation("androidx.paging:paging-compose:3.3.2")

    // livedata

    // ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    // ViewModel utilities for Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    // LiveData
    implementation(libs.androidx.lifecycle.livedata.ktx)
    // Lifecycles only (without ViewModel or LiveData)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    // Lifecycle utilities for Compose
    implementation(libs.androidx.lifecycle.runtime.compose)

    implementation(libs.androidx.paging.compose)

    // Saved state module for ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)

    // Annotation processor
    kapt(libs.androidx.lifecycle.compiler)
    // alternately - if using Java8, use the following instead of lifecycle-compiler
    implementation(libs.androidx.lifecycle.common.java8)

    // optional - helpers for implementing LifecycleOwner in a Service
    implementation(libs.androidx.lifecycle.service)

    // optional - ProcessLifecycleOwner provides a lifecycle for the whole application process
    implementation(libs.androidx.lifecycle.process)

    // optional - ReactiveStreams support for LiveData
    implementation(libs.androidx.lifecycle.reactivestreams.ktx)


    // Import the BoM for the Firebase platform
    implementation(platform(libs.firebase.bom.v3320))

    // Add the dependencies for the Remote Config and Analytics libraries
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation(libs.google.firebase.config)
    implementation(libs.google.firebase.analytics)

    // Data store

    implementation("androidx.datastore:datastore-preferences:1.1.1")

    // optional - RxJava2 support
    implementation("androidx.datastore:datastore-preferences-rxjava2:1.1.1")

    // optional - RxJava3 support
    implementation("androidx.datastore:datastore-preferences-rxjava3:1.1.1")

    implementation("androidx.datastore:datastore:1.1.1")

    // optional - RxJava2 support
    implementation("androidx.datastore:datastore-rxjava2:1.1.1")

    // optional - RxJava3 support
    implementation("androidx.datastore:datastore-rxjava3:1.1.1")


    implementation(libs.timber)

    val fragmentVersion = "1.8.6"
    // Kotlin
    implementation("androidx.fragment:fragment-ktx:$fragmentVersion")

    val dynamicanimation_version = "1.0.0"
    implementation("androidx.dynamicanimation:dynamicanimation:$dynamicanimation_version")

    // optional - Test helpers for LiveData
    testImplementation(libs.androidx.core.testing)

    // optional - Test helpers for Lifecycle runtime
    testImplementation(libs.androidx.lifecycle.runtime.testing)
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    /*
        https://developer.android.com/training/testing/local-tests/robolectric?hl=pt-br
     */
    testImplementation(libs.robolectric)

    // https://developer.android.com/jetpack/androidx/releases/test
    val androidXTestVersion = "1.6.1"
    // Core library
    androidTestImplementation("androidx.test:core:$androidXTestVersion")

    val testRunnerVersion = "1.6.2"
    val testRulesVersion = "1.6.1"
    // AndroidJUnitRunner and JUnit Rules
    androidTestImplementation("androidx.test:runner:$testRunnerVersion")
    androidTestImplementation("androidx.test:rules:$testRulesVersion")

    val testJunitVersion = "1.2.1"
    val truthVersion = "1.6.0"
    // Assertions
    androidTestImplementation("androidx.test.ext:junit:$testJunitVersion")
    androidTestImplementation("androidx.test.ext:truth:$truthVersion")

    val espressoVersion = "3.6.1"
    // Espresso dependencies
    androidTestImplementation( "androidx.test.espresso:espresso-core:$espressoVersion")
    androidTestImplementation( "androidx.test.espresso:espresso-contrib:$espressoVersion")
    androidTestImplementation( "androidx.test.espresso:espresso-intents:$espressoVersion")
    androidTestImplementation( "androidx.test.espresso:espresso-accessibility:$espressoVersion")
    androidTestImplementation( "androidx.test.espresso:espresso-web:$espressoVersion")
    androidTestImplementation( "androidx.test.espresso.idling:idling-concurrent:$espressoVersion")

    // The following Espresso dependency can be either "implementation",
    // or "androidTestImplementation", depending on whether you want the
    // dependency to appear on your APK"s compile classpath or the test APK
    // classpath.
    androidTestImplementation( "androidx.test.espresso:espresso-idling-resource:$espressoVersion")
}