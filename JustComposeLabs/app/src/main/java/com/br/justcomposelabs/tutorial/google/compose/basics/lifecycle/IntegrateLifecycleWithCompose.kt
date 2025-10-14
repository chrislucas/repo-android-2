package com.br.justcomposelabs.tutorial.google.compose.basics.lifecycle

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.lifecycle.compose.LocalLifecycleOwner

/*
    https://developer.android.com/topic/libraries/architecture/compose

    Warning: You cannot use this to listen for Lifecycle.Event.ON_DESTROY since composition ends before this signal is sent.

 */

@Composable
fun Start() {
    LifecycleEventEffect(event = Lifecycle.Event.ON_START) {

    }

    LifecycleEventEffect(event = Lifecycle.Event.ON_STOP) {

    }

}

/*
    https://developer.android.com/develop/ui/compose/side-effects#disposableeffect
 */

@Composable
fun BindingDisposableEffect(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    callback: CallbackLocalLifecycleOwner
) {
    DisposableEffect(lifecycleOwner) {
        val obs = LifecycleEventObserver { _, event ->
            callback.onEvent(event)
        }

        lifecycleOwner.lifecycle.addObserver(obs)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(obs)
        }
    }
}

interface CallbackLocalLifecycleOwner {
    fun onEvent(e: Lifecycle.Event)
}