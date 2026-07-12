package com.br.buildlogic

import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType

/*
    https://share.google/aimode/n7YVgM8390lrMYnR0
 */
class DetektConventionPlugin : Plugin<Project> {

    override fun apply(target: Project): Unit = with(target) {

        pluginManager.apply("io.gitlab.arturbosch.detekt")

        extensions.configure<DetektExtension> {
            toolVersion = "1.23.8"
            autoCorrect = true
            parallel = true
            buildUponDefaultConfig = true
            // Config compartilhada permanece global
            config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
            // Baseline por módulo, no diretório do próprio módulo
            baseline = file("$projectDir/detekt-baseline.xml")
            ignoreFailures = false
        }

        dependencies.add(
            "detektPlugins",
            "io.nlopez.compose.rules:detekt:0.5.7"
        )
        dependencies.add(
            "detektPlugins",
            "io.gitlab.arturbosch.detekt:detekt-formatting:1.23.8"
        )

        tasks.withType<Detekt>().configureEach {
            val taskName = name
            reports {
                html.required.set(true)
                html.outputLocation.set(
                    layout.buildDirectory.file(
                        "reports/detekt/$taskName.html"
                    )
                )
                sarif.required.set(true)
                sarif.outputLocation.set(
                    layout.buildDirectory.file(
                        "reports/detekt/$taskName.sarif"
                    )
                )
                md.required.set(true)
                md.outputLocation.set(
                    layout.buildDirectory.file(
                        "reports/detekt/$taskName.md"
                    )
                )
            }
        }

        tasks.register<DetektCreateBaselineTask>("detektProjectBaseline") {
            description = "Overrides current baseline for this module."
            buildUponDefaultConfig.set(true)
            ignoreFailures.set(true)
            parallel.set(true)
            setSource(files(projectDir))                       // só o módulo
            config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
            baseline.set(file("$projectDir/detekt-baseline.xml"))  // por módulo
            include("**/*.kt", "**/*.kts")
            exclude("**/resources/**", "**/build/**")
        }
    }
}
