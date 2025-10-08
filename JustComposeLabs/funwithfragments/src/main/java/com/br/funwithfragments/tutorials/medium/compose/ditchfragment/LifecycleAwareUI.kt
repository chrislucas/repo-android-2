package com.br.funwithfragments.tutorials.medium.compose.ditchfragment

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

/*
    https://medium.com/@chetanshingare2991/ditch-fragments-how-jetpack-compose-redefines-modern-android-development-5e4e9af979c6
     Jetpack Compose Solution: Lifecycle-Aware UI

 */


@Composable
fun TimerScreen() {
    val time = remember { mutableStateOf(0) }
}