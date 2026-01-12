package com.br.justcomposelabs.tutorial.google.lifecycle.integratecompose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.LocalLifecycleOwner

/*
    https://developer.android.com/topic/libraries/architecture/compose
 */

@Composable
fun Lifecycle() {
    val lifecycleOwner = LocalLifecycleOwner.current
    val stateFlow = lifecycleOwner.lifecycle.currentStateFlow
    val currentLifecycleState by stateFlow.collectAsState()

}
