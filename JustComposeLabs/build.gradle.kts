import java.io.ByteArrayOutputStream

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.compose.compiler) apply false

    id("com.google.gms.google-services") version "4.4.3" apply false
    id("com.google.devtools.ksp") version "2.2.10-2.0.2" apply false

    // https://infinum.com/blog/automated-gradle-dependency-updates/
    id("com.github.ben-manes.versions") version "0.53.0"
    id("nl.littlerobots.version-catalog-update") version "1.0.1"
}

buildscript {

    repositories {
        mavenLocal()
        google()
        mavenCentral()
        maven {
            url = uri("https://maven.pkg.github.com/google/secrets-gradle-plugin")
            credentials {
                username = project.findProperty("ghGprUser") as String? ?: System.getenv("ghGprUser")
                password = project.findProperty("ghGprToken") as String? ?: System.getenv("ghGprToken")
            }
        }
    }

    dependencies {
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.secrets.gradle.plugin)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

/*
    TODO estudar
    Optimize your build speed
    https://developer.android.com/build/optimize-your-build
    https://developer.android.com/build/optimize-your-build#kts
    https://docs.gradle.org/current/userguide/performance.html#parallel_execution

    Estudos sobre gradle
    https://cookbook.gradle.org/android/#featured-recipes
    https://developer.android.com/build/gradle-build-overview

 */

/*
    create a task to execute gradle command gradle.kts
    https://docs.gradle.org/current/userguide/more_about_tasks.html
 */

tasks.register<Exec>("updcat") {
    /*
        https://infinum.com/blog/automated-gradle-dependency-updates/
     */
    executable("./gradlew versionCatalogUpdate")

    val outputStream = ByteArrayOutputStream()
    standardOutput = outputStream

    doLast {
        println("Command output: ${outputStream.toString().trim()}")
    }
}

tasks.register<Exec>("Echo") {
    description = "Executes a custom command-line command."
    group = "Custom Tasks"

    commandLine("echo", "Hello World")

    val outputStream = ByteArrayOutputStream()
    standardOutput = outputStream

    doLast {
        println("Command output: ${outputStream.toString().trim()}")
    }
}

tasks.register<Exec>("Greeting") {
    description = "Executes a custom command-line command."
    group = "Custom Tasks"

    // Set the executable (the command you want to run)
    executable("echo")

    // Define the arguments for the command
    args("Hello from Gradle!")

    // Optionally, capture the output of the command
    val outputStream = ByteArrayOutputStream()
    standardOutput = outputStream

    // Define an action to perform after the command executes (e.g., print output)
    doLast {
        println("Command output: ${outputStream.toString().trim()}")
    }
}

