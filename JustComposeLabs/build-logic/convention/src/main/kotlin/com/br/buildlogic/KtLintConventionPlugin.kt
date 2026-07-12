package com.br.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jlleitschuh.gradle.ktlint.KtlintExtension

/*
    https://share.google/aimode/n7YVgM8390lrMYnR0
 */
class KtLintConventionPlugin: Plugin<Project> {

    override fun apply(target: Project): Unit = with(target) {
        pluginManager.apply("org.jlleitschuh.gradle.ktlint")
        extensions.configure<KtlintExtension> {
            version.set("12.1.1")
            android.set(true)
            verbose.set(true)
            outputToConsole.set(true)
            ignoreFailures.set(false)
            filter {
                exclude("**/generated/**")
            }
        }
    }
}
