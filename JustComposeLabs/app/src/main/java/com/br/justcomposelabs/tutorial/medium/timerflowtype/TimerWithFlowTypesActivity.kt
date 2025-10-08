package com.br.justcomposelabs.tutorial.medium.timerflowtype

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.br.justcomposelabs.tutorial.medium.timerflowtype.ui.theme.JustComposeLabsTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


/*
    https://medium.com/kotlin-android-chronicle/choosing-between-channels-and-flows-in-your-viewmodel-8d8287f624ac

    Criar um UI com um texto que mostra a hora
        - A hora deve ser atualizada por uma View Model
        - Criar 3 ViewModels
            - Uma com StateFlow
            - Uma com LiveData
            - Uma com SharedStateFlow
 */
class TimerWithFlowTypesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JustComposeLabsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Clocks(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Clocks(
    modifier: Modifier = Modifier,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    clockViewModelStateFlow: ClockViewModelStateFlow = ClockViewModelStateFlow(),
) {

    fun callbackLifecycle(event: Lifecycle.Event) {
        when (event) {

            Lifecycle.Event.ON_CREATE -> {
            }

            Lifecycle.Event.ON_START -> {
            }

            Lifecycle.Event.ON_RESUME -> {

            }

            Lifecycle.Event.ON_PAUSE -> {
            }

            Lifecycle.Event.ON_STOP -> {

            }

            Lifecycle.Event.ON_DESTROY -> {
            }

            Lifecycle.Event.ON_ANY -> {
            }
        }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            callbackLifecycle(event)
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Column(modifier = modifier.fillMaxSize()) {
        ClockStateFlow(modifier, clockViewModelStateFlow)
        Spacer(modifier = Modifier.padding(8.dp))
        ClockLiveData(modifier)
        Spacer(modifier = Modifier.padding(8.dp))
        ClockSharedFlow(modifier)
    }
}

@Composable
private fun ClockStateFlow(
    modifier: Modifier = Modifier,
    viewModel: ClockViewModelStateFlow
) {
    val currentHour by viewModel.observableContent.collectAsState()
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(currentHour, style = MaterialTheme.typography.headlineLarge)
    }
}

class ClockViewModelStateFlow : ViewModel() {
    private val content = MutableStateFlow(currentHour())

    val observableContent: StateFlow<String> = content

    init {
        viewModelScope.launch {
            while (true) {
                content.value = currentHour()
                delay(1000)
            }
        }
    }

    private fun currentHour() = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
}


@Composable
private fun ClockLiveData(
    modifier: Modifier = Modifier,
    viewModel: ClockViewModelLiveData = viewModel()
) {
    val currentHour by viewModel.observableContent.observeAsState(
        SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
    )
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(currentHour, style = MaterialTheme.typography.headlineLarge)
    }
}

class ClockViewModelLiveData : ViewModel() {
    private val content = MutableLiveData(currentHour())

    val observableContent: LiveData<String> = content

    init {
        viewModelScope.launch {
            while (true) {
                content.postValue(currentHour())
                delay(1000)
            }
        }
    }

    private fun currentHour() = SimpleDateFormat(
        "HH:mm:ss",
        Locale.getDefault()
    ).format(Date())
}


@Composable
private fun ClockSharedFlow(
    modifier: Modifier = Modifier,
    viewModel: ClockViewModelSharedFlow = viewModel()
) {
    val currentHour = remember {
        mutableStateOf(
            SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
        )
    }

    /*
        https://developer.android.com/develop/ui/compose/side-effects
        Side-effects in Compose


     */

    LaunchedEffect(Unit) {
        viewModel.observableContent.collectLatest { data ->
            currentHour.value = data
        }
    }

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            currentHour.value,
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

class ClockViewModelSharedFlow : ViewModel() {
    private val content = MutableSharedFlow<String>(replay = 1)

    val observableContent: SharedFlow<String> = content.asSharedFlow()

    init {
        viewModelScope.launch {
            while (true) {
                content.emit(currentHour())
                delay(1000)
            }
        }
    }

    private fun currentHour() = SimpleDateFormat(
        "HH:mm:ss",
        Locale.getDefault()
    ).format(Date())
}

@Preview(showBackground = true)
@Composable
fun ClocksPreview() {
    JustComposeLabsTheme {
        Clocks()
    }
}