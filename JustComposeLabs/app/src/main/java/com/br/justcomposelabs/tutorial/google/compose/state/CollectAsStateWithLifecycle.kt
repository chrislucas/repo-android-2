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

/*
    https://developer.android.com/reference/kotlin/androidx/lifecycle/compose/package-summary#(kotlinx.coroutines.flow.Flow).collectAsStateWithLifecycle(kotlin.Any,androidx.lifecycle.LifecycleOwner,androidx.lifecycle.Lifecycle.State,kotlin.coroutines.CoroutineContext)
 */


class ClockViewModelFlow : ViewModel() {
    /**
     * @see com.br.justcomposelabs.tutorial.medium.timerflowtype.TimerWithFlowTypesActivity
     */
    val clock = flow {
        while (true) {
            emit(currentHour())
            delay(1000L)
        }
    }

    private fun currentHour() = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())

}

data class ClockUiState(val currentTime: String = "--:--:--")

class ClockViewModelFlowImproved : ViewModel() {
    // Assuming 'clock' is a Flow<String> that emits the time
    private val clock: Flow<String> = flow {
        while (true) {
            emit(currentHour())
            delay(1000L)
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
        ClockUiState()
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = MaterialTheme.shapes.medium,
        tonalElevation = 1.dp
    ) {
        Box(
            modifier = Modifier.padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = currentHour,
                style = MaterialTheme.typography.headlineLarge
            )
        }
    }
}