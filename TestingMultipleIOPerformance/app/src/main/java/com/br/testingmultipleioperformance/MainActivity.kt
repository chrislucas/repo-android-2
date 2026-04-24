package com.br.testingmultipleioperformance

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
import com.br.testingmultipleioperformance.ui.theme.TestingMultipleIOPerformanceTheme

/**
 *  TODO
 *      1 - testar desempenho de leitura e escrita nos componentes
 *          - sharedpreference
 *          - datasotre
 *          - SQLite com Room
 *      2 - Quero saber se executar X coroutines escrevendo e lendo
 *      nesses componentes, quanto de consumo de memoria ocorre e se em algum momento
 *      temos o travamento do app
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestingMultipleIOPerformanceTheme {
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
    TestingMultipleIOPerformanceTheme {
        Greeting("Android")
    }
}
