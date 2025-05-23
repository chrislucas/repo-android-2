// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    //id("com.android.application") version "8.1.0" apply false
    //id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kapt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
}