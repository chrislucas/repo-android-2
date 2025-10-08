package com.br.simplemvi

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
import com.br.simplemvi.ui.theme.SimpleMVITheme

/*
    https://medium.com/@anilani786/exploring-mvi-architecture-in-android-comprehensive-implementation-guide-and-comparison-with-mvvm-c60ebd149717

    TODO
    Criar um exemplo basico de clean arch para estudos futuro. Pode ser num módulo a parte
    Usar esse exemplo com essa api
    https://medium.com/android-dev-br/paging-v3-jetpack-compose-2899a6877bef#fromHistory
    https://rickandmortyapi.com/#fromHistory
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleMVITheme {
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
    SimpleMVITheme {
        Greeting("Android")
    }
}