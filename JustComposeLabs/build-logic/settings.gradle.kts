pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

/*
    Para acessar libs dentro do build-logic, garanta que
    build-logic/settings.gradle.kts importe o catálogo:
 */
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    // Permite que o build-logic use o mesmo Version Catalog (libs.versions.toml) do app principal
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}


rootProject.name = "build-logic"
include(":convention")
