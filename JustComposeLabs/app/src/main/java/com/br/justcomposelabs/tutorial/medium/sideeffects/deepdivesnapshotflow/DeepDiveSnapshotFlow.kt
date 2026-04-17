package com.br.justcomposelabs.tutorial.medium.sideeffects.deepdivesnapshotflow

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.tooling.preview.Preview
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import timber.log.Timber

/*
    Master the Bridge: A Deep Dive into Jetpack Compose snapshotFlow
    https://medium.com/@sivavishnu0705/master-the-bridge-a-deep-dive-into-jetpack-compose-snapshotflow-92aa83c1949d

    - snapshotFlow é uma função que permite converter Compose State<T> em um cold Kotlin Flow<T>

    - Aspecto chave do sbaoshotFlow
         - Reactive Bridge: Observa/Rasteia qualquer Compose State (MutableSateOf, LazyListtState, etc),
        fazendo a leitura.
         - Auto-Distinct: Similar ao Flow.distinctUntilChanged(),
         snapshotFlow emite um novo valor apenas quando o valor observado muda, evitando emissões redundantes.
         - Cold Flow: Somente consome o recurso enviado quando coletado.

       https://share.google/aimode/LPUJIdnBEmGYJZZRX

       - Casos de Uso
         - Scroll Tracking, Text Input changes or gesture detection
 */


class FlowIntViewModel : ViewModel() {

    /*
        https://share.google/aimode/Fx3i61Uu1Df2IjniO
     */
    val distinctIntUntilChange = flow {
        while (true) {
            val number = (1..3).random()
            emit(number)
            Timber.tag("IntViewModelDistinct").d("Emitting number: $number")
            delay(1000)
        }
    }.distinctUntilChanged()
}


@Composable
fun DistinctIntUntilChangeScreen(
    viewModel: FlowIntViewModel = viewModel()
) {
    val distinctIntUntilChange by viewModel.distinctIntUntilChange.collectAsState(initial = 0)
    DistinctIntUntilChangeContent(distinctIntUntilChange = distinctIntUntilChange)
}

@Composable
fun DistinctIntUntilChangeContent(
    distinctIntUntilChange: Int
) {
    DistinctMessage("DistinctIntUntilChangeContent recomposed with value: $distinctIntUntilChange")
}

@Composable
fun DistinctMessage(
    distinctIntUntilChange: String
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = distinctIntUntilChange,
            style = TextStyle(
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                color = androidx.compose.ui.graphics.Color.Black,
                fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DistinctIntUntilChangeScreenPreview() {
    JustComposeLabsTheme {
        DistinctIntUntilChangeScreen()
    }
}
