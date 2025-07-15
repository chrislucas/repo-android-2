package com.br.funwithdatabinding.view.features.networking.coinlore.view

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
import com.br.funwithdatabinding.view.features.networking.coinlore.view.ui.theme.FunWithDataBindingTheme


/*
    https://www.coinlore.com/pt/cryptocurrency-data-api
    Usar essa api para explorar
        - recurso do retrofit
        - arquitetura MVVM + Flow + Compose
        - Explorar a funcionalidade de Network do Android Studio
            - Verificar a possibilidade de conseguir chegar a um CURl a partir da requisicao

        - Estudar a possibilidade de criar um plugin de network que funciona como o Flipper
            - https://fbflipper.com/docs/tutorial/intro/
            - Um plugin para Android studio e verificar a necessidade de um plugin gradle
 */
class CoinLoreActivity : ComponentActivity() {
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