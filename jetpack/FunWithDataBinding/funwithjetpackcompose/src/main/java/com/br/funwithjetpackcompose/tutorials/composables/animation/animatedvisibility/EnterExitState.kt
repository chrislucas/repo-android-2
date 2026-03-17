package com.br.funwithjetpackcompose.tutorials.composables.animation.animatedvisibility

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

/*
    https://developer.android.com/reference/kotlin/androidx/compose/animation/EnterExitState
    EnterExistState contem 3 extados que esta envolvido na ENTRADA e SAIDA de transicao
    de AnimatedVisibility
        - PreEnter e Visible define o estado inicial e "alvo" de uma transicao
        de entrada,
        - ao passo que Visible e PostExit sao os estados iniciais e "alvo"
        de uma transicao de Saida
 */


@Composable
fun TransitionAnimation() {
    val visible by remember { mutableStateOf(true) }
}