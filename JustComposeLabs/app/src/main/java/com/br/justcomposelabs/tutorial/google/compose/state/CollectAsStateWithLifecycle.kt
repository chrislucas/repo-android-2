package com.br.justcomposelabs.tutorial.google.compose.state

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.time.Duration.Companion.milliseconds

/**
 * Explicação sobre Coleta de Estado no Jetpack Compose:
 *
 * 1. collectAsStateWithLifecycle():
 *    - O QUE É: Converte um Flow em State do Compose de forma ciente do ciclo de vida (Lifecycle-aware).
 *    - QUANDO USAR: É a **recomendação padrão** para desenvolvimento Android.
 *    - POR QUE: Ela interrompe a coleta do Flow automaticamente quando o app entra em segundo plano
 *      (especificamente quando o Lifecycle cai abaixo do estado definido, por padrão STARTED) e retoma
 *      quando o app volta para o primeiro plano. Isso economiza recursos (CPU/Bateria) ao evitar
 *      processamento desnecessário quando a UI não está visível.
 *    - DEPENDÊNCIA: `androidx.lifecycle:lifecycle-runtime-compose`.
 *
 * 2. collectAsState():
 *    - O QUE É: Função básica do Compose para converter Flows em State.
 *    - QUANDO USAR: Em projetos Kotlin Multiplatform (KMP) ou em partes da UI onde o ciclo de vida
 *      do Android não é um fator crítico (ex: testes simples ou lógica puramente de UI).
 *    - LIMITAÇÃO: No Android, ela continua coletando do Flow enquanto o Composable estiver na
 *      árvore de composição, mesmo que o usuário não esteja vendo o app, o que pode gastar recursos.
 *
 * 3. observeAsState() (para LiveData):
 *    - O QUE É: Converte LiveData em State.
 *    - QUANDO USAR: Em projetos legados ou que ainda utilizam LiveData na camada de ViewModel.
 *
 * 4. subscribeAsState() (para RxJava):
 *    - O QUE É: Converte fluxos RxJava em State.
 *    - QUANDO USAR: Se o projeto utiliza RxJava para reatividade na camada de dados/negócios.
 */

/*
    Link oficial:
    https://developer.android.com/reference/kotlin/androidx/lifecycle/compose/package-summary#(kotlinx.coroutines.flow.Flow).collectAsStateWithLifecycle(kotlin.Any,androidx.lifecycle.LifecycleOwner,androidx.lifecycle.Lifecycle.State,kotlin.coroutines.CoroutineContext)
 */

class ClockViewModelFlow : ViewModel() {
    /**
     * @see com.br.justcomposelabs.tutorial.medium.timerflowtype.TimerWithFlowTypesActivity
     */
    val clock =
        flow {
            while (true) {
                emit(currentHour())
                delay(1000L.milliseconds)
            }
        }

    private fun currentHour() = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
}

data class ClockUiState(
    val currentTime: String = "--:--:--",
)

class ClockViewModelFlowImproved : ViewModel() {
    // Assuming 'clock' is a Flow<String> that emits the time
    private val clock: Flow<String> =
        flow {
            while (true) {
                emit(currentHour())
                delay(1000.milliseconds)
            }
        }

    private val _uiState = MutableStateFlow(ClockUiState())
    val uiState: StateFlow<ClockUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            clock.collect { timeString ->
                _uiState.update { currentState ->
                    currentState.copy(currentTime = timeString)
                }
            }
        }
    }

    private fun currentHour() = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
}

@Composable
fun ClockScreen(viewModel: ClockViewModelFlow = viewModel()) {
    val currentHour: String by viewModel.clock.collectAsStateWithLifecycle(initialValue = "--:--:--")
    ClockCard(currentHour = currentHour)
}

@Preview(showBackground = true, name = "ClockScreenPreview")
@Composable
private fun ClockScreenPreview() {
    ClockScreen()
}

@Composable
fun ClockScreenImproved(viewModel: ClockViewModelFlowImproved = viewModel()) {
    val currentHour by viewModel.uiState.collectAsStateWithLifecycle(
        ClockUiState(),
    )
    /**
     * @see com.br.justcomposelabs.tutorial.google.compose.basics.lifecycle
     Integrate Lifecycle with Compose
     https://developer.android.com/topic/libraries/architecture/compose
     */
    LifecycleStartEffect(currentHour) {
        onStopOrDispose {
            Timber.d("onStopOrDispose")
        }
    }

    LifecycleResumeEffect(currentHour) {
        onPauseOrDispose {
            Timber.d("onPauseOrDispose")
        }
    }

    ClockCard(currentHour = currentHour.currentTime)
}

@Preview(showBackground = true, name = "ClockScreenImprovedPreview")
@Composable
private fun ClockScreenImprovedPreview() {
    ClockScreenImproved()
}

@Preview(showBackground = true, name = "ClockCard")
@Composable
fun ClockCard(currentHour: String = "--:--:--") {
    Surface(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = MaterialTheme.shapes.medium,
        tonalElevation = 1.dp,
    ) {
        Box(
            modifier = Modifier.padding(16.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = currentHour,
                style = MaterialTheme.typography.headlineLarge,
            )
        }
    }
}
