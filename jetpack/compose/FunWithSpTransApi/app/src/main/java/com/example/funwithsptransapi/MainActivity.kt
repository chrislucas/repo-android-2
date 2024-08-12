package com.example.funwithsptransapi

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
import com.example.funwithsptransapi.ui.theme.FunWithSpTransApiTheme

/**
 * TODO criar um mapa com a localização dos onibus usando a api
 * olho vivo
 *
 * https://olhovivo.sptrans.com.br/
 *
 * ÁREA DO DESENVOLVEDOR
 *  https://www.sptrans.com.br/desenvolvedores/
 *
 * API DO OLHO VIVO - GUIA DE REFERÊNCIA
 *  https://www.sptrans.com.br/desenvolvedores/api-do-olho-vivo-guia-de-referencia/documentacao-api/
 *
 *  https://www.sptrans.com.br/desenvolvedores/api-do-olho-vivo-guia-de-referencia/
 *
 *  https://olhovivo.sptrans.com.br/
 *
 *  https://github.com/jtrecenti/sptrans
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FunWithSpTransApiTheme {
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
    FunWithSpTransApiTheme {
        Greeting("Android")
    }
}