package com.br.funwithjetpackcompose.tutorials.google.codelabs.basics

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
import com.br.funwithjetpackcompose.tutorials.google.codelabs.basics.ui.theme.FunWithDataBindingTheme


/*
    https://developer.android.com/codelabs/jetpack-compose-layouts?hl=en#0


    https://developer.android.com/codelabs/jetpack-compose-basics#0


    https://developer.android.com/courses/pathways/jetpack-compose-for-android-developers-1
    Thinking in Compose
    Composable functions
        - Tomar cuidado para que Composable Function nao causem side-effets
            - Nao alterar propriedades dentro de Composable Function como por exemplo mudar
            um boolean de um objeto de true para false
         - Recomposition
            - Quando construimos uma UI atraves de uma fucncao baseada no estado de um objeto, quando esse objeto
            muda seu estado a UI tbm muda atraves da re-execucao dessa funcao com o novo estado do objeto

            - Recomposition ocorre quando quando uma funcao composable e re-executada com novos
            parametros e tambem ocorre quando um estado interno na funcao muda.
 */
class CodeLabsBasicsComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FunWithDataBindingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting19(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting19(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview19() {
    FunWithDataBindingTheme {
        Greeting19("Android")
    }
}