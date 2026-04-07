package com.br.justcomposelabs.tutorial.google.compose.sideffects.launcheffect

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/*
    Side-effects in Compose:
    https://developer.android.com/develop/ui/compose/side-effects

    - Side Effect em compose é a mudacan de estado do app que ocorre ou inicia fora do escopo de uma composable
    function.

        - Devido o ciclo de vida das funcoes composables e da propriedade na preditiva do mecanismo
        de recomposicao, executando recomposicoes de composables em diferentes ordens, ou
        recomposicoes que podem ser discartadas, composables idealmente deveriam se livres
        de side-effect


        - Porém, Side-Effects sao necessarios para realizar tarefas como:
            - Ler ou escrever em um banco de dados
            - Fazer chamadas de rede
            - Registrar um BroadcastReceiver
            - Executar uma animação
            - Mostrar uma snackbar
            - Navegar para outra tela
        -Essas acoes devem ser chamadas de um ambiente controlado que esteja ciente do ciclo
        de vida de uma compsosable.


    Effect APIs: Executar side effects de uma maneira preditivel
        - LaunchedEffect
        - rememberCoroutineScope
        - DisposableEffect
        - SideEffect
        - rememberUpdatedState
        - produceState
        - derivedStateOf
        - snapshotFlow



    LaunchedEffect: run suspend functions in the scope of a composable
        - Para executar tarefas ao logo do ciclo de vida da funcao composable
        e ter a capacidade de chamar funcoes composable, use a funcao
        LaunchedEffect.
        - Quando LaunchedEffect entra na composition, ele lanca uma coroutine atraves da funcao
        lamnbda (block) passada como argumento.

        - A coroutine vai ser cancelada se LaunchedEffect deixar a composicao.

        - Se LaunchedEffect for recomposto no processo de recomposisiton com uma key diferenten, a coroutine
        existente sera cancelada e uma nova suspend function sera lancada com uma nova
        coroutine.
            - Restarting effects
                - LaunchedEffect, producedState e DispossableEffect tomam
                variaveiscomo argumento que sao chaves usadas para cancelar a funcao de side-effect
                e iniciar a uma nova caso uma dessas chaves seja alterada (recomposicao)

                - Devido a sutileza desse comportamos, problemas podem ocorrer se o parametro
                usado nessa funcao nao for o correto.

                     - Reiniciar o Side-Effect


 */


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PulseTextComponent() {
    var pulseRateMs by remember { mutableLongStateOf(1000L) }
    val alpha = remember { Animatable(1f) }
    LaunchedEffect(pulseRateMs) { // Restart the effect when the pulse rate changes
        while (true) {
            delay(pulseRateMs) // Pulse the alpha every pulseRateMs to alert the user
            alpha.animateTo(0f)
            alpha.animateTo(1f)
        }
    }

    Box(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .border(
                BorderStroke(2.dp, Color.Red)
            )
            .graphicsLayer {
                this.alpha = alpha.value
            },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            "Pulse",
            textAlign = TextAlign.Center,
            style = TextStyle(
                color = Color.Red,
                fontSize = 60.sp,
                fontFamily = FontFamily.Cursive
            )
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PulseTextSideEffectComponent() {
    var pulseRateMs by remember { mutableLongStateOf(1000L) }
    val alpha = remember { Animatable(1f) }
    LaunchedEffect(pulseRateMs) { // Restart the effect when the pulse rate changes
        while (true) {
            delay(pulseRateMs) // Pulse the alpha every pulseRateMs to alert the user
            alpha.animateTo(0f)
            alpha.animateTo(1f)
        }
    }

    SideEffect {
        // Update the pulse rate every 5 seconds to demonstrate restarting the effect}
        pulseRateMs = if (pulseRateMs == 1000L) {
            500L
        } else {
            1000L
        }
    }

    Box(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .border(
                BorderStroke(2.dp, Color.Red)
            )
            .graphicsLayer {
                this.alpha = alpha.value
            },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            "Pulse",
            textAlign = TextAlign.Center,
            style = TextStyle(
                color = Color.Red,
                fontSize = 60.sp,
                fontFamily = FontFamily.Cursive
            )
        )
    }
}

/*
    rememberCoroutineScope: obtain a composition-aware scope to launch a coroutine outside a composable
    - LaunchedEffect é uma
 */

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SnackbarComponent(snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }) {
    // CoroutineScope vinculada ao lifecycle da composable
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { contentPadding ->
        Column(
            Modifier.padding(contentPadding)
                .fillMaxWidth()
        ) {
            Button(
                onClick = {
                    // Lança uma coroutine para mostrar a snackbar, pois showSnackbar é uma função de suspensão
                    scope.launch {
                        snackbarHostState.showSnackbar("Something happened!")
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(2.dp),
                shape = RectangleShape
            ) {
                Text("Press me")
            }
        }
    }
}