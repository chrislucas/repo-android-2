# Como configurar plugins de análise estática de código

## KtLint - https://pinterest.github.io/ktlint/latest/

### Funcionalidaees
- KtLint é um linter para Kotlin que ajuda a manter um código limpo e 
consistente, seguindo as convenções de estilo do Kotlin.
  - Sem configuraçÕes: KtLint usa as regras de
    - [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
    - [Android Kotlin Style Guide](https://developer.android.com/kotlin/style-guide)
  - .editconfig
    - Algumas regras permitem configurações adicionais e quando não fornecidos um valor padrão é usado
- Disables Rules
  -  KTLint permitir habilitar e desabilittar regras
  - Exemplo: para desabilitar a regra de indentação, 
  adicione o seguinte ao seu arquivo ".editorconfig"
    ```
    [*.kt]
    indent_size = 4
    indent_style = space
    disabled_rules = indent
    ```
  - [How do I enable or disable a rule?¶](https://pinterest.github.io/ktlint/latest/faq/#how-do-i-enable-or-disable-a-rule)
- Built-in Formatter
  - A maioira dos lints nao precisa ser consertadas manualmente. KTlint possui um formatador
que corrige violações quando possível


### Configuraç!oes

- Adicione esse trecho de código no arquivo build.gradle.kts do módulo app para configurar o plugin KtLint:
```
plugins {
    id "org.jlleitschuh.gradle.ktlint" version "11.0.0"
}
```
- Adicione esse trecho de codigo no arquivo build.gradle.kts do módulo app para configurar o plugin Ktlint:

```
plugins {
    id("org.jlleitschuh.gradle.ktlint")
}
```

### Configurando o as regras do KtLint e Detekt para Compose


### Lint e formatação do código

```
Autocorrect style violations
ktlint --format
# or
ktlint -F
```
- [Command line](https://pinterest.github.io/ktlint/latest/install/cli/#command-line-usage)
- [Lint & Format](https://pinterest.github.io/ktlint/latest/quick-start/#step-2-lint-and-format-your-code)

### fontes:
- https://mrmans0n.github.io/compose-rules/ktlint/
- https://arctouch.com/blog/static-analysis-ktlint-detekt
- https://medium.com/android-dev-br/introdu%C3%A7%C3%A3o-ktlint-876794faafb7




## Detekt

```
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("io.gitlab.arturbosch.detekt") version("1.23.6")
}
```

```
plugins {
    id("io.gitlab.arturbosch.detekt")
}
```

```
    // https://github.com/mrmans0n/compose-rules
    // https://mrmans0n.github.io/compose-rules/ktlint/
    
    detektPlugins("io.nlopez.compose.rules:detekt:0.5.3") // Use the latest version
    detektPlugins("dev.detekt:detekt-rules-ktlint-wrapper:2.0.0-alpha.1")
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.6")
```


```
// https://detekt.dev/docs/intro
// https://github.com/detekt/detekt

detekt {
    toolVersion = "1.23.8"
    autoCorrect = true
    parallel = true
    config.setFrom(file("${rootProject.layout.projectDirectory}/config/detekt/detekt.yml"))
    buildUponDefaultConfig = true
}

// Kotlin DSL
tasks.withType<Detekt>().configureEach {
    reports {
        html.required.set(true)
        sarif.required.set(true)
        md.required.set(true)
    }
}
```

fontes:
- https://kt.academy/article/ak-static-analysis
- https://detekt.dev/docs/1.23.8/intro/
- https://detekt.dev/docs/1.23.8/introduction/configurations/
- https://detekt.dev/docs/1.23.8/introduction/baseline/
- https://detekt.dev/docs/1.23.8/introduction/reporting/
- https://detekt.dev/docs/1.23.8/rules/formatting/
- https://detekt.dev/docs/1.23.8/rules/style/
- https://detekt.dev/docs/1.23.8/rules/complexity/#cyclomaticcomplexmethod
- https://detekt.dev/docs/1.23.8/introduction/compose/#recommended-configuration-4
- https://www.baeldung.com/kotlin/detekt-static-code-analysis
- https://medium.com/@ipek.birinci8/what-is-detekt-dc1df0e2cb61
- https://medium.com/@SaezChristopher/how-to-automatically-update-detekt-on-your-android-project-without-gradle-d275e1c214df

## lint-checker

fontes:
- https://developer.android.com/studio/write/lint

## Compose Stability Analyser

fontes:
- https://proandroiddev.com/compose-stability-analyzer-real-time-stability-insights-for-jetpack-compose-1399924a0a64

## Test | Jacaco

fontes:
- https://gist.github.com/shubhendras11/d366717985ca5eae776bfbb153c5d1a0
- https://canopas.com/android-code-coverage-using-jacoco-6639a1fc4293
- https://proandroiddev.com/mastering-jacoco-with-agp-8-5-pursuit-of-code-coverage-d3f57c0587a3


## Pre commit hooks

fonts
- https://pre-commit.com/
- https://github.com/pre-commit/pre-commit
- https://medium.com/@habbema/pre-commit-315db54ef2d8