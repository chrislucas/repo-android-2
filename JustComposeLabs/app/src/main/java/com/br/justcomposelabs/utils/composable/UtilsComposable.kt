package com.br.justcomposelabs.utils.composable

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollSource.Companion.SideEffect
import androidx.compose.ui.node.Ref
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import timber.log.Timber

@Preview(showBackground = true)
@Preview(showBackground = false)
annotation class ShowBackgroundOrNot

@Composable
fun Modifier.paddingEdgeToEdge() =
    padding(WindowInsets.safeDrawing.asPaddingValues())

/*
    https://stackoverflow.com/questions/71239101/how-to-listen-for-lifecycle-in-jetpack-compose
    https://medium.com/@mahdizareeii/handling-app-lifecycle-events-in-jetpack-compose-d3b7f526514b

    Handling lifecycles with lifecycle-aware components
    https://developer.android.com/topic/libraries/architecture/lifecycle

    - Lifecycle-aware components perfom actions in response to a change in the lifecycle status
 */

@Composable
fun ComposableLifecycle(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onEvent: (LifecycleOwner, Lifecycle.Event) -> Unit
) {
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver(onEvent)
        lifecycleOwner.lifecycle.run {
            addObserver(observer)
            onDispose {
                removeObserver(observer)
            }
        }
    }
}

@Composable
fun LogCompositions(tag: String, msg: String) {
    /*
        o que ocorre ao usar rememberSaveable

        java.lang.IllegalArgumentException:
        androidx.compose.ui.node.Ref@e4b0743 cannot be saved using the current SaveableStateRegistry.

        The default implementation only supports types which can be stored inside the Bundle.
        Please consider implementing a custom Saver for this class and pass it to rememberSaveable().
     */

    val ref = remember { Ref<Int>().apply { value = 0 } }
    SideEffect {
        ref.value?.plus(1)
    }

    Timber.tag(tag).d("$msg: ${ref.value}")

    /*
        Isso gera recomposições infinitas

        Por que não usar MutableStateOf?

        Se você usasse mutableStateOf(0),
        o ato de aumentar o contador (count.value++)
        causaria uma nova recomposição infinita.


        var counter by rememberSaveable { mutableIntStateOf(1) }
        SideEffect {
            counter++
        }

        Timber.tag(tag).d("$msg: $counter")

     */
}

/*
    Processes and app lifecycle
    https://developer.android.com/guide/components/activities/process-lifecycle
 */


