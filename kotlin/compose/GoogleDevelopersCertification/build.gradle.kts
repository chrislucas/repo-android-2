// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.androidLibrary) apply false

    id ("org.jetbrains.kotlin.jvm") version "1.9.0"
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id ("com.google.devtools.ksp") version "1.9.0-1.0.12"

}