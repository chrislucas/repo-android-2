// build-logic/convention/build.gradle.kts
/*
    aplica kotlin-dsl (é isso que habilita Kotlin), define JVM target 17,
    declara a dependência do detekt-gradle-plugin e
    registra o plugin via gradlePlugin { }.
 */
plugins {
    `kotlin-dsl`
}


group = "com.br.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}

dependencies {
    implementation(libs.detekt.gradlePlugin)
    implementation(libs.ktlint.gradlePlugin)
}

gradlePlugin {
    plugins {
            register("detektConvention") {
            id = "justcomposelabs.detekt"
            implementationClass = "com.br.buildlogic.DetektConventionPlugin"
        }
        register("ktlintConvention") {
            id = "justcomposelabs.ktlint"
            implementationClass = "com.br.buildlogic.KtLintConventionPlugin"
        }
    }
}
