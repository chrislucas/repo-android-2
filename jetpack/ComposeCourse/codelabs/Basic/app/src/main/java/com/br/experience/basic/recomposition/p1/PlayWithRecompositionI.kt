package com.br.experience.basic.recomposition.p1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.br.experience.basic.ui.theme.BasicTheme
import com.br.experience.basic.ui.theme.Purple200
import com.br.experience.basic.ui.theme.Purple500
import kotlin.concurrent.timer

/**
 * Estudando o mecanismo de recomposition
 * https://medium.com/mobile-app-development-publication/android-jetpack-compose-recomposition-made-easy-527ecc9bcbaf
 */
class PlayWithRecompositionI : ComponentActivity() {

    private var counter = 0
    private val routine = timer(period = 10000L) {
        val size = info.value.size
        while (counter < Int.MAX_VALUE) {
            val idx = counter % size
            val (_, color) = info.value[idx]
            info.value[idx] = Pair("message $counter", color)
            counter += 1
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicTheme {
                // A surface container using the 'background' color from the theme
                ShowGreetingMessages(info)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        routine.cancel()
    }
}

private val info = mutableStateOf(
    arrayOf(
        "msg 1" to Purple200,
        "msg 2" to Purple500
    )
)

@Composable
fun Greeting3(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun HelloWorld() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Greeting3("Android")
    }
}

@Composable
fun ShowGreetingMessage() {
    Text(
        text = "hello",
        modifier = Modifier
            .fillMaxSize()
            .background(Purple200),
        textAlign = TextAlign.Center,
        fontSize = 32.sp
    )
}

@Composable
fun ShowGreetingMessages(data: MutableState<Array<Pair<String, Color>>>) {
    Column {
        data.value.forEach { (msg, color) ->
            TextMessage(message = msg, color = color)
        }
    }
}

@Composable
fun TextMessage(message: String, color: Color) {
    Text(
        text = message,
        modifier = Modifier
            .fillMaxWidth()
            .background(color),
        textAlign = TextAlign.Center,
        fontSize = 32.sp
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    BasicTheme {
        ShowGreetingMessages(info)
    }
}