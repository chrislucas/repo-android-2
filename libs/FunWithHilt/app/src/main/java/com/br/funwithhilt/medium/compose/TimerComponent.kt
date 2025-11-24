package com.br.funwithhilt.medium.compose

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Date
import java.util.Timer
import java.util.TimerTask

/*
    TODO: ler o artigo
    fazer o TimerScreen com Timer funcionar, o contador nao esta mudando
    https://medium.com/@chetanshingare2991/ditch-fragments-how-jetpack-compose-redefines-modern-android-development-5e4e9af979c6
 */

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TimerScreen() {
    val time = remember { mutableIntStateOf(0) }
    DisposableEffect(Unit) {
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                Log.d("TimerScreen", "run: ${time.intValue}")
            }

        }, Date(), 1000L)

        onDispose { timer.cancel() }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "Elapsed time: ${time.intValue} seconds",
            style = TextStyle(
                fontStyle = FontStyle.Italic,
                fontSize = 23.sp
            )
        )

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TimerScreenCoroutine() {
    val time = remember { mutableIntStateOf(0) }
    DisposableEffect(Unit) {
        val job = CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                time.intValue++
                delay(1000L)
            }
        }
        onDispose { job.cancel() }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "Elapsed time: ${time.intValue} seconds",
            style = TextStyle(
                fontStyle = FontStyle.Italic,
                fontSize = 23.sp
            )
        )
    }
}