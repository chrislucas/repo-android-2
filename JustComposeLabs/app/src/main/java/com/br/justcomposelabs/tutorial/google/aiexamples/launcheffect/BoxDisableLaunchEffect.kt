package com.br.justcomposelabs.tutorial.google.aiexamples.launcheffect

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import timber.log.Timber

/*
    LaunchedEffect and animation compose
    Launched used in animation compose

    LaunchedEffect é uma composable function usada para executar suspendable side effects, como
    lancar uma coorutine para animacoes, dentro eo didlo de vida da funcao composable.

    Isso garante que efeitos estao vinculados com a operacao de compisicao e respondem amudancas
    de elementos que sao postos como elementos-chave

    - Como LaunchedEffect facilita animacoes

        - Coroutine Scope: LaunchedEffect prove uma corouiine scope, permite a execucao de
        suspend functions, que sao essencialmente muitas das funcoes da API de animacao em
        Jetpack Compose

        - Lifecycle Management: Quando LaunchedEffect entra na composicao, ele lanca uma coroutine
        especifica. Se o argumento "key" mudar, a coroutine sera cancelada e uma nova
        sera lancada, garantindo que  a animacao seja reiniciada ou adaptada ao novo estado

           - Quando a funcao composable deixa a composicao, a coroutine associada a ela tambem é cancelada,
           previnindo vazamento de recursos

        - Triggering Animation: Ao passar variaveis de estado como chave para LaunchedEffect, animations podem
        ser disparadas automaticamente quando os valores de estado mudam.
            - Exemplo: Se queremos uma animacao executando quando o estado de visibilidade muda, passamos
            tal estado como chave para LaunchedEffect

 */


@Preview(showBackground = true)
@Composable
fun AnimatedBox() {
    val isVisible = remember { mutableStateOf(false) }

    //var s by remember { mutableStateOf(false) }

    val alpha: Float by animateFloatAsState(
        targetValue = if (isVisible.value) 1f else 0f,
        animationSpec = tween(durationMillis = 1000),
        label = "alphaAnimation"
    )

    LaunchedEffect(isVisible) {
        Timber.d("Visibility: $isVisible")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .alpha(alpha),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Hello!",
                fontSize = TextUnit(34f, TextUnitType.Sp)
            )
        }

        Button(onClick = { isVisible.value = !isVisible.value }) {
            Text("Toggle Visibility")
        }
    }
}
