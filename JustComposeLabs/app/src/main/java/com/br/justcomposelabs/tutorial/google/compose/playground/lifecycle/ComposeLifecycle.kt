package com.br.justcomposelabs.tutorial.google.compose.playground.lifecycle

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import timber.log.Timber

/*
    https://foso.github.io/Jetpack-Compose-Playground/general/compose_lifecycle/

    Compose possui algumas funcoes de "efeito" que podem see usados em composables para trackear
    o ciclo de vida de uma funcao

    LaunchedEffect {}
        - Sera chamada a primeira vez que a funcao compose for aplicada
     DisposableEffect
        - recebe uma function with receiver: DisposableEffectScope.() -> DisposableEffectResult
            - isso habilita acesso a funcao onDispose() que sera executada quando a funcao
            copose nao fizer parte da composition mais
 */


@Preview(uiMode = 1, showSystemUi = true, showBackground = true)
@Composable
fun ComposeLifecycle() {
    /*
        o exemplo abaixo tem um botao que ao ser clicado aumenta o valor de umva variavel
        ido tipo inteiro. Quando o valor chega a 3 o Text(modifier = Modifier.testTag("side_effect_text"))
        nao sera adicionada mais exibido.

        A primeira vez que a funcao ComposeLifecycle for executada, o SideEffect dentro da clausula
        IF sera executado. Quando o contador chegar a 3+ onDispose dentro da

     */
    val count = remember { mutableIntStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .systemBarsPadding()
    ) {
        Button(onClick = { count.intValue++ }) {
            Text(
                "Count: ${count.intValue}",
                style = TextStyle(

                ),
                textAlign = TextAlign.Center
            )
        }

        if (count.intValue < 3) {
            LaunchedEffect(Unit) {
                Timber.tag("COMPOSE_LAUNCH_EFFECT").d("on active with value ${count.intValue}")
            }
            DisposableEffect(Unit) {
                onDispose {
                    Timber.tag("COMPOSE_DISPOSE_EFFECT")
                        .d("on dispose with value ${count.intValue}")
                }
            }

            Text(
                text = "You have clicked the button: ${count.intValue}",
                modifier = Modifier.testTag("side_effect_text"),
                textAlign = TextAlign.Center
            )
        }
    }
}
