package com.br.uistatepatternviewmodel.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch


@Composable
fun ScrollListTopComponent(component: @Composable (() -> Unit) -> Unit,  onClick: suspend () -> Unit) {
    /*
        rememberCoroutineScope: obtain a composition-aware scope to launch a coroutine outside a composable
        https://developer.android.com/develop/ui/compose/side-effects#remembercoroutinescope
     */
    val scope = rememberCoroutineScope()
    component {
        scope.launch {
            onClick()
        }
    }
}