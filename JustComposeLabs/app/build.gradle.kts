import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask

plugins {
    alias(libs.plugins.android.application)
    /*
        # Compatibility with AGP 9.0+
        If you have upgraded to Android Gradle Plugin 9.0 or higher,
        the org.jetbrains.kotlin.android plugin is no longer required
        as Kotlin support is built-in.

        Migrate to built-in Kotlin
        https://developer.android.com/build/migrate-to-built-in-kotlin
     */
    //alias(libs.plugins.jetbrains.kotlin.android)
    /*
        Compose Compiler Gradle plugin
        https://developer.android.com/develop/ui/compose/compiler
     */
    alias(libs.plugins.compose.compiler)
    id("com.google.devtools.ksp")
    id("com.google.gms.google-services")
    kotlin("plugin.serialization") version "2.0.21"
    // Add the Crashlytics Gradle plugin
    id("com.google.firebase.crashlytics")
    id("kotlin-parcelize")
    id("io.gitlab.arturbosch.detekt")
    id("org.jlleitschuh.gradle.ktlint")
}

android {
    namespace = "com.br.justcomposelabs"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.br.justcomposelabs"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // This block is different from the one you use to link Gradle
        // to your CMake or ndk-build script.
        externalNativeBuild {
            // For ndk-build, instead use the ndkBuild block.
            cmake {
                // Passes optional arguments to CMake.
                arguments += listOf("-DANDROID_SUPPORT_FLEXIBLE_PAGE_SIZES=ON")
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            // For AGP 8.0 and later:
            enableAndroidTestCoverage = true
            enableUnitTestCoverage = true
        }
    }

    buildFeatures {
        compose = true
        viewBinding = true
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    packagingOptions {
        jniLibs {
            useLegacyPackaging = true
        }
    }
}

configurations.all {
    resolutionStrategy {
        force(libs.androidx.espresso.idling.resource)
        force(libs.androidx.espresso.core)
        force(libs.androidx.junit)
    }
}

dependencies {
    implementation(project(":referencelinbraries"))

    // View
    // https://github.com/material-components/material-components-android/blob/master/docs/getting-started.md
    // https://developer.android.com/develop/ui/views/theming/look-and-feel
    implementation("com.google.android.material:material:1.13.0")

    // https://developer.android.com/develop/ui/views/components/settings
    implementation("androidx.preference:preference-ktx:1.2.1")

    implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.4.0")

    // end views
    implementation(libs.androidx.compose.ui.ui.test.junit4)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.compose.material3.material3)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.fragment.compose)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    implementation(platform(libs.androidx.compose.bom))
    implementation("androidx.paging:paging-compose:3.4.2")

    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.ads.mobile.sdk)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    annotationProcessor(libs.androidx.room.ktx)
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.junit.jupiter)
    lintChecks(libs.lint)
    implementation(libs.androidx.dynamicanimation)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    /*
        Select colors with the Palette API
        https://developer.android.com/develop/ui/views/graphics/palette-colors
     */
    implementation("androidx.palette:palette:1.0.0")


    // https://developer.android.com/develop/ui/compose/layouts/adaptive/list-detail
    implementation(libs.androidx.adaptive)


    // camera x
    implementation(libs.androidx.camera.core)
    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.camera.extensions)
    implementation(libs.androidx.camera.video)
    implementation(libs.androidx.camera.view)

    // ktx
    implementation(libs.androidx.collection.ktx)

    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.graphics.core)
    implementation(libs.androidx.graphics.path)
    implementation(libs.androidx.graphics.shapes)

    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    implementation("io.coil-kt.coil3:coil-svg:3.4.0")

    implementation(libs.material)
    implementation(libs.timber)

    // Import the BoM for the Firebase platform
    implementation(platform(libs.firebase.bom))

    // Add the dependencies for the Remote Config and Analytics libraries
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation(libs.google.firebase.config)
    implementation(libs.google.firebase.analytics)
    implementation(libs.google.firebase.crashlytics)

    // compose
    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation(libs.ui.viewbinding)
    // dependency without a version
    implementation(libs.androidx.runtime.tracing)

    // Capture a trace from terminal https://developer.android.com/develop/ui/compose/tooling/tracing#terminal
    implementation(libs.androidx.tracing.perfetto)
    implementation(libs.androidx.tracing.perfetto.binary)

    // Java language implementation
    implementation(libs.androidx.fragment)
    // Kotlin
    implementation(libs.androidx.fragment.ktx)

    // Glance setup

    // For AppWidgets support
    implementation(libs.androidx.glance.appwidget)
    // For interop APIs with Material 3
    implementation(libs.androidx.glance.material3)
    // For interop APIs with Material 2
    implementation(libs.androidx.glance.material)

    // Choose one of the following:
    // Material Design 3
    implementation(libs.material3)
    // or Material Design 2
    implementation(libs.androidx.material)
    // or skip Material Design and build directly on top of foundational components
    implementation(libs.androidx.foundation)
    // or only import the main APIs for the underlying toolkit systems,
    // such as input and measurement/layout
    implementation(libs.ui)

    // Android Studio Preview support
    implementation(libs.ui.tooling.preview)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:6.0.3")
    debugImplementation(libs.ui.tooling)

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.kotlinx.coroutines.test)

    // UI Tests
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.test.manifest)
    testImplementation(libs.robolectric)


    // Optional - Included automatically by material, only add when you need
    // the icons but not the material library (e.g. when using Material3 or a
    // custom design system based on Foundation)
    implementation(libs.androidx.material.icons.core)
    // Optional - Add full set of material icons
    implementation(libs.androidx.material.icons.extended)
    // Optional - Add window size utils
    implementation(libs.androidx.adaptive)

    // Optional - Integration with activities
    implementation(libs.androidx.activity.compose)
    // Optional - Integration with ViewModels
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    // Optional - Integration with LiveData
    implementation(libs.androidx.runtime.livedata)
    // Optional - Integration with RxJava
    implementation(libs.androidx.runtime.rxjava2)
    implementation(libs.androidx.runtime.rxjava3)

    // paging jetpack compose
    // https://developer.android.com/jetpack/androidx/releases/paging?authuser=1
    implementation(libs.androidx.paging.runtime)


    // alternatively - without Android dependencies for tests

    // optional - RxJava2 support
    implementation(libs.androidx.paging.rxjava2)

    // optional - RxJava3 support
    implementation(libs.androidx.paging.rxjava3)

    // optional - Guava ListenableFuture support
    implementation(libs.androidx.paging.guava)

    // optional - Jetpack Compose integration
    implementation(libs.androidx.paging.compose)


    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)


    // To use Kotlin Symbol Processing (KSP)
    ksp(libs.androidx.room.compiler)

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation(libs.androidx.room.ktx)

    // optional - RxJava2 support for Room
    implementation(libs.androidx.room.rxjava2)

    // optional - RxJava3 support for Room
    implementation(libs.androidx.room.rxjava3)

    // optional - Guava support for Room, including Optional and ListenableFuture
    implementation(libs.androidx.room.guava)

    // optional - Paging 3 Integration
    implementation(libs.androidx.room.paging)
    val inkVersion = "1.0.0-alpha05"

    implementation("androidx.ink:ink-authoring:$inkVersion")
    implementation("androidx.ink:ink-brush:$inkVersion")
    implementation("androidx.ink:ink-geometry:$inkVersion")
    implementation("androidx.ink:ink-nativeloader:$inkVersion")
    implementation("androidx.ink:ink-rendering:$inkVersion")
    implementation("androidx.ink:ink-strokes:$inkVersion")

    implementation("androidx.hilt:hilt-work:1.3.0")
    // When using Kotlin.
    ksp("androidx.hilt:hilt-compiler:1.3.0")
    // When using Java.
    annotationProcessor("androidx.hilt:hilt-compiler:1.3.0")


    /*
        Getting started with WorkManager
        https://developer.android.com/develop/background-work/background-tasks/persistent/getting-started
     */
    implementation(libs.androidx.ui.viewbinding)
    implementation(libs.androidx.webkit)
    implementation(libs.androidx.work.gcm)
    implementation(libs.androidx.work.multiprocess)
    implementation(libs.androidx.work.runtime)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.work.rxjava2)
    implementation(libs.androidx.work.testing)

    // Test rules and transitive dependencies:
    androidTestImplementation(libs.ui.test.junit4)
    // Needed for createComposeRule(), but not for createAndroidComposeRule<YourActivity>():
    debugImplementation(libs.ui.test.manifest)
    testImplementation(libs.robolectric)
    testImplementation(libs.androidx.paging.common)
    // optional - Test helpers
    testImplementation(libs.androidx.room.testing)

    /*
        https://github.com/android/testing-samples
     */

    // Optional -- Robolectric environment
    testImplementation("androidx.test:core:1.7.0")
    testImplementation("androidx.test:core-ktx:1.7.0")
    // Optional -- Mockito framework
    testImplementation("org.mockito:mockito-core:5.23.0")
    // Optional -- mockito-kotlin
    testImplementation("org.mockito.kotlin:mockito-kotlin:6.3.0")
    testImplementation(libs.mockk)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // test compose: https://developer.android.com/codelabs/jetpack-compose-testing#0
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)

    debugImplementation(libs.androidx.ui.tooling)
    testImplementation(kotlin("test"))


    // https://github.com/mrmans0n/compose-rules
    // https://mrmans0n.github.io/compose-rules/ktlint/
    detektPlugins("io.nlopez.compose.rules:detekt:0.5.7") // Use the latest version
    detektPlugins("dev.detekt:detekt-rules-ktlint-wrapper:2.0.0-alpha.2")
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.8")
}

kotlin {
    jvmToolchain(17)
}

/*
    https://detekt.dev/docs/intro
    https://github.com/detekt/detekt
 */
detekt {
    /*
        https://detekt.dev/docs/gettingstarted/gradle/#options-for-detekt-configuration-closure
     */
    toolVersion = "1.23.8"
    autoCorrect = true
    parallel = true
    source.setFrom("src/main/java", "src/main/kotlin")
    config.setFrom(file("${rootProject.layout.projectDirectory}/config/detekt/detekt.yml"))
    buildUponDefaultConfig = true
    baseline = file("${rootProject.layout.projectDirectory}/config/detekt/baseline.xml")
}

// Kotlin DSL
tasks.withType<Detekt>().configureEach {
    reports {
        //html.required.set(true)
        //sarif.required.set(true)
        //md.required.set(true)

        // Enable/Disable checkstyle report (default: true)
        //checkstyle.required.set(true)
        //checkstyle.outputLocation.set(file("build/reports/detekt.xml"))
        // Enable/Disable HTML report (default: true)
        html.required.set(true)
        html.outputLocation.set(file("build/reports/detekt.html"))
        // Enable/Disable SARIF report (default: false)
        sarif.required.set(true)
        sarif.outputLocation.set(file("build/reports/detekt.sarif"))
        // Enable/Disable Markdown report (default: false)
        md.required.set(true)
        md.outputLocation.set(file("build/reports/detekt.md"))
        custom {
            // The simple class name of your custom report.
            reportId = "CustomJsonReport"
            outputLocation.set(file("build/reports/detekt.json"))
        }
    }
}

val detektProjectBaseline by tasks.registering(DetektCreateBaselineTask::class) {
    description = "Overrides current baseline."
    buildUponDefaultConfig.set(true)
    ignoreFailures.set(true)
    parallel.set(true)
    setSource(files(rootDir))
    config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
    baseline.set(file("$rootDir/config/detekt/baseline.xml"))
    include("**/*.kt")
    include("**/*.kts")
    exclude("**/resources/**")
    exclude("**/build/**")
}