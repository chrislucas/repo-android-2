package com.br.justcomposelabs.tutorial.google.compose.sideffects.remembercoroutinescope

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

/**
 * @see com.br.justcomposelabs.tutorial.google.compose.sideffects.launcheffect.SnackbarComponent
    https://developer.android.com/develop/ui/compose/components/snackbar
    https://developer.android.com/develop/ui/compose/side-effects#remembercoroutinescope
 */

@Composable
fun StatefulSnackBarComponent() {
    val snackBarStateHost = remember { SnackbarHostState() }
}