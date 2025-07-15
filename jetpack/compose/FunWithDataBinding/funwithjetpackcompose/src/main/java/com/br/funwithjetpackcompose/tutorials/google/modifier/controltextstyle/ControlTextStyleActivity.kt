package com.br.funwithjetpackcompose.tutorials.google.modifier.controltextstyle

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
import com.br.funwithjetpackcompose.tutorials.google.modifier.controltextstyle.ui.theme.FunWithDataBindingTheme


/*
    TODO
    Criar uma interfaee que controla o Estilo de texto
        - adiciona e remove shadow
        - aumenta fonte
        - troca estilo
        - troca pexo (weigjt)
        - adiciona brush
    https://developer.android.com/develop/ui/compose/text/style-text
 */
class ControlTextStyleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FunWithDataBindingTheme {
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
    FunWithDataBindingTheme {
        Greeting("Android")
    }
}