package com.br.funwithrolleta

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
import com.br.funwithrolleta.ui.theme.JustComposeLabsTheme

/*
    Criar uma roleta animada
        - Criar uma interface onde fica uma roleta girando e o usuário pode adicionar
        nomes
        - adicionar um campo de texto para os nomes. Pode ser na toolbar
        - Adicionar a roleta no meio da tela
        - Adicionar um botão para com o texto girar para selecionar a roleta
        - Adicionar um efeito de confete quando um nome for selecionado
            - Verificar a possibilidade de usar lottie como efeito

        Inspiracao
            - https://app-sorteos.com/pt/apps/girar-roleta-aleatoria
            https://app-sorteos.com/pt/apps/girar-roleta-aleatoria

 */
class RollActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JustComposeLabsTheme {
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
    JustComposeLabsTheme {
        Greeting("Android")
    }
}