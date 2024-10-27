pluginManagement {
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
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "FunWithDataBinding"
include(":app")
include(":restclientlib")
include(":wrapperviewmodel")
include(":funwithjetpackcompose")
include(":navfeatures")
include(":navfeaturescompose")
include(":reflibs")
include(":funwithcurrencyconversionapi")
include(":funwithprotodatastore")
include(":mapsandroidktx")
include(":funwithandroidktx")
include(":funwithconcatadapter")
include(":recyclerviewcomponent")
include(":funwithshapes")
