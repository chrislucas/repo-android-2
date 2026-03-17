package com.example.game2048

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.game2048.ui.theme.Game2048Theme

/**
 * TODO
 * https://medium.com/@nicolasmarin1312/2048-with-compose-part-1-e35cda261292
 * https://medium.com/@nicolasmarin1312/2048-with-compose-part-5-6112242697c8
 *
 * TODO ler
 * https://developer.android.com/develop/ui/compose/tooling/tracing
 *
 * Sobre a biblioteca Tracing
 *
 * - Ha duas niveis de suporte a rastreamento/tracing no android: System tracing e Method Tracing
 *
 * - System tracing só faz track de pontos especificos marcados para serem rastreados e assim
 * tem um baixo custo computaciona (low overhead) e nao afeta muito o desempenho do app de forma
 *      - System tracing é interessante para analisar o quanto uma seção  particular do código esta
 *          levando para ser executada
 *
 *       - Por padrao, system trace nao incluie funcoes composables individuais, elas
 *       sao avalidadas no Method Trace
 *
 * - Method Tracing rastrea toda chamada de funcao do app. É um recurso muito custoso que afeta o
 * desempenho do app, mas nos da uma visão completa do que esta acontecendo, qual funcao esta sendo
 * chamada e com que frequencia.
 *
 *
 * - Setup para composition tracing
 *
 * Android Studio Flamingo
 * Compose UI: 1.3.0
 * Compose Compiler: 1.3.0
 * The device or emulator you run your trace on must also be at minimum API level 30
 *
 *
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Game2048Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Game2048Theme {
        Greeting("Android")
    }
}