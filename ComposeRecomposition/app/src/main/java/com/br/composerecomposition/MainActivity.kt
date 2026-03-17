package com.br.composerecomposition

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
import com.br.composerecomposition.ui.theme.ComposeRecompositionTheme


/*

    Quando recomposition realmente acontece ?

    - De maneira simples, quando o parâmetro passado para uma função muda
        - Exemplo uma data class, um atributo obervavel uma viewmodel
        - Esse conceito é muito importante de ser entendido

    https://developer.android.com/develop/ui/compose/state

    Composable Functions
    https://androidbyexample.com/modules/compose-concepts/STEP_020_FULL.html
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeRecompositionTheme {
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
    ComposeRecompositionTheme {
        Greeting("Android")
    }
}