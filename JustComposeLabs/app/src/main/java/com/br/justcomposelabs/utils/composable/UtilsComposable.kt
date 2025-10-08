package com.br.justcomposelabs.utils.composable

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner


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


/*
    Processes and app lifecycle
    https://developer.android.com/guide/components/activities/process-lifecycle
 */
