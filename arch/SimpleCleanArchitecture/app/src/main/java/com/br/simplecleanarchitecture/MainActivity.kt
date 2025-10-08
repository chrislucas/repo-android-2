package com.br.simplecleanarchitecture

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
import com.br.simplecleanarchitecture.ui.theme.SimpleCleanArchitectureTheme

/*
    Guide to app architecture
    https://developer.android.com/topic/architecture

    Criar um exemplo basico de clean arch para estudos futuros
    Usar esse exemplo com essa api
    https://medium.com/android-dev-br/paging-v3-jetpack-compose-2899a6877bef#fromHistory
    https://rickandmortyapi.com/#fromHistory

    https://medium.com/@cardosof.gui/clean-architecture-e-mvvm-no-desenvolvimento-android-6f542d0f2e99
    https://medium.com/@anandgaur2207/mvvm-clean-architecture-in-android-be5ef3f05330
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleCleanArchitectureTheme {
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
    SimpleCleanArchitectureTheme {
        Greeting("Android")
    }
}