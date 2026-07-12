pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }

    plugins {
        kotlin("jvm") version "2.4.0"
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "JustComposeLabs"
include(":app")
include(":scaffoldttopbarsample")
include(":paginglibrarysamples")
include(":network")
include(":datastore")
include(":referencelinbraries")
include(":canvasviews")
include(":funwithrolleta")
include(":funwithglance")
include(":funwithmlkit")
include(":funcodelabsanimation")
include(":funwithanimationcompose")
include(":clonejetcaster")
include(":clonejetcaster:core:data")
include(":migrationstrategycompose")
include(":funwithperapplanguages")
include(":searchinaccessibility")
include(":funwithlottiecompose")
include(":funwithgmaps")
include(":okhttpwebsocket")
include(":scarletwebsocket")
include(":funwithbroadcastreceiver")
include(":funwithfragments")
include(":funwithsecurity")
include(":funwithhilt")
include(":funwithcameraxcompose")
include(":appobservability")
include(":funwithviewmodel")
include(":funwithlifecycle")
include(":clonejetchat")
