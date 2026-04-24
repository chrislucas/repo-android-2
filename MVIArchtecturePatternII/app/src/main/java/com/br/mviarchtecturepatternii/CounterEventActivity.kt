package com.br.mviarchtecturepatternii

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.RepeatMode
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.br.mviarchtecturepatternii.CounterIntent.Decrement
import com.br.mviarchtecturepatternii.CounterIntent.Increment
import com.br.mviarchtecturepatternii.CounterIntent.LoadData
import com.br.mviarchtecturepatternii.ui.theme.MVIArchtecturePatternIITheme
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

/*
    Advanced: Adding Effects and Async
    https://jamshidbekboynazarov.medium.com/mvi-architecture-pattern-in-android-with-jetpack-compose-0fd61f9c67fd
 */


sealed interface CounterEffect {
    data class ShowToast(val message: String) : CounterEffect
}


class CounterEffectViewModel : ViewModel() {
    private val channelEffects = Channel<CounterEffect>(Channel.BUFFERED)
    val effects = channelEffects.receiveAsFlow()


    private val mutableState = MutableStateFlow(CounterState())
    val state: StateFlow<CounterState> = mutableState.asStateFlow()


    fun fetchData() =
        viewModelScope.launch {
            while (true) {
                delay(5000L)
                Log.i("FETCH_DATA_TAG", "fetch data")
                dispatch(LoadData)
            }
        }


    @Throws(Exception::class)
    suspend fun fakeExecution() {
        delay(5000L)
        Log.i("FAKE_EXECUTION_TAG", "fake execution")
        val mod = Random.nextInt(Int.MAX_VALUE - 1) % 3 == 0
        if (mod) {
            throw Exception("FAKE_EXCEPTION")
        }
    }

    /*
        CoroutineExceptionHandler
        https://kotlinlang.org/docs/exception-handling.html#coroutineexceptionhandler

        https://proandroiddev.com/advanced-exception-handling-in-kotlin-coroutines-a-guide-for-android-developers-e1aede099252
     */
    val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e("COROUTINE_EXCEPTION_TAG", "coroutine exception", throwable)
    }


    fun dispatch(intent: CounterIntent) {
        viewModelScope.launch(coroutineExceptionHandler) {
            when (intent) {
                Increment -> {
                    /*
                        Thread-safe update
                     */
                    mutableState.update { state ->
                        state.copy(count = state.count + 1)
                    }
                }

                Decrement -> {
                    mutableState.update { state ->
                        state.copy(count = state.count - 1)
                    }
                }

                LoadData -> {
                    mutableState.update { state -> state.copy(isLoading = true) }
                    runCatching {
                        fakeExecution()
                        mutableState.update { state -> state.copy(isLoading = false, count = 42) }
                    }.onFailure {
                        mutableState.update { state -> state.copy(isLoading = false) }
                        Log.e("LOAD_DATA_TAG", "load data failed", it)
                        channelEffects.send(CounterEffect.ShowToast("Error"))
                    }
                }
            }
        }
    }
}

class CounterEventActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MVIArchtecturePatternIITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CounterScreenView(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CounterScreenView(
    modifier: Modifier = Modifier,
    viewModel: CounterEffectViewModel = viewModel()
) {
    viewModel.fetchData()

    val ctx = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.effects.collect {
            when (it) {
                is CounterEffect.ShowToast -> {
                    // show toast

                    Toast.makeText(
                        ctx,
                        it.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    /*
        rememberCoroutineScope: obtain a composition-aware scope to launch a coroutine outside a composable
        https://developer.android.com/develop/ui/compose/side-effects#remembercoroutinescope

        - LaunchedEffect é uma composable function
     */
    // val coroutineScope = rememberCoroutineScope()

    /*
        remembercoroutinescope vs launchedeffect
     */

    /*
        launch coroutine from composable function vs from viewModel
     */


    /*
        when launch coroutine from composable function
     */


    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(modifier) {
        Text("Count: ${state.count}")
        Row {
            Button(onClick = { viewModel.dispatch(Increment) }) {
                Text("Increment")
            }
            Button(onClick = { viewModel.dispatch(Decrement) }) {
                Text("Decrement")
            }
        }
    }
}


private

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    MVIArchtecturePatternIITheme {
        CounterScreenView()
    }
}
