package com.br.justcomposelabs.tutorial.google.compose.state.withlifecycle

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.br.justcomposelabs.utils.composable.paddingEdgeToEdge
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

/**
 * @see Lifecycle.State
 * @see Lifecycle.State.STARTED
 * @see Lifecycle.State.DESTROYED
 * @see Lifecycle.State.INITIALIZED
 *
https://developer.android.com/reference/kotlin/androidx/lifecycle/compose/package-summary#(kotlinx.coroutines.flow.Flow).collectAsStateWithLifecycle(kotlin.Any,androidx.lifecycle.LifecycleOwner,androidx.lifecycle.Lifecycle.State,kotlin.coroutines.CoroutineContext)
@Composable
fun <T : Any?> Flow<T>.collectAsStateWithLifecycle(
initialValue: T,
lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
context: CoroutineContext = EmptyCoroutineContext
): State<T>

- Coleta valores a partir de um Flow e representa o ultimo valor via State usando
lifecycle-aware

- Sempre que houver uma mudanca no valor do estado postado no Flow, o estado (State<T>)
Retornado sera atualizado causando uma recomposicao de cada componente que usa o valor
de State.value, sempre que o ciclo de vida do lifecycleOwner estiver pelo menos o valor
passado ao parametro minActiveState (Lifecycle.State.STARTED)

- O Flow é coletado a todo momento que o lifecycle do lifecycleOwner alcancar o valor
passado para o parametro minActiveState

 */

class CounterState {
    val counter =
        flow {
            var count = 0
            while (true) {
                emit(count++)
                delay(1000L)
            }
        }
}

@Preview(showBackground = true)
@Composable
fun CounterComponent() {
    val state = remember { CounterState() }
    val count by state.counter.collectAsStateWithLifecycle(initialValue = 0)

    val ctx = LocalContext.current
    /*
        Run code on lifecycle events
        https://developer.android.com/topic/libraries/architecture/compose#run-code
        There are also LifecycleEffects that let you run a block when a particular Lifecycle.Event occurs.
     */
    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        Toast.makeText(ctx, "ON_START", Toast.LENGTH_SHORT).show()
    }

    /*
        LifecycleStartEffect
        The LifecycleStartEffect is similar to the LifecycleEffect,
        but it runs only on Lifecycle.Event.ON_START events.

        It also accepts keys that work like other Compose keys.
        When the key changes, it triggers the block to run again.


     */

    LifecycleStartEffect(Unit) {
        // ON_START code is executed here
        Toast.makeText(ctx, "LIFECYCLE_START_EFFECT", Toast.LENGTH_SHORT).show()

        onStopOrDispose {
            // do any needed clean up here
            Toast
                .makeText(
                    ctx,
                    "LIFECYCLE_START_ON_STOP",
                    Toast.LENGTH_SHORT,
                ).show()
        }
    }

    LifecycleResumeEffect(Unit) {
        // ON_RESUME code is executed here
        Toast
            .makeText(
                ctx,
                "LIFECYCLE_RESUME_EFFECT",
                Toast.LENGTH_SHORT,
            ).show()

        onPauseOrDispose {
            // do any needed clean up here
            Toast
                .makeText(
                    ctx,
                    "LIFECYCLE_RESUME_ON_PAUSE",
                    Toast.LENGTH_SHORT,
                ).show()
        }
    }

    Box(
        modifier =
        Modifier
            .fillMaxSize()
            .paddingEdgeToEdge()
            .navigationBarsPadding(),
        contentAlignment = Alignment.Center,
    ) {
        Text("Counter: $count", style = TextStyle(fontSize = 23.sp))
    }
}
