package com.br.justcomposelabs.tutorial.medium.sideeffects.tipsaboutsideeffects

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay

/*
    https://medium.com/@sivavishnu0705/mastering-the-impure-a-guide-to-jetpack-compose-side-effects-in-kotlin-7abf536ca59a
 */

@Composable
fun SecureDataScreen(onTimeout: () -> Unit) {

    LaunchedEffect(Unit) {
        delay(60_000L)
        onTimeout()
    }
}