package com.br.funwithjetpackcompose.tutorials.google.thinkingcompose

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
import com.br.funwithjetpackcompose.tutorials.google.thinkingcompose.ui.theme.FunWithDataBindingTheme

/*
    TODO
    https://developer.android.com/develop/ui/compose/mental-model

    Sobre performance e recomposicao
        - Como fazer debugging de recomposition
        = xhttps://developer.android.com/develop/ui/compose/quick-guides/content/video/debugging-recomposition
        - Jetpack Compose Performance
        - https://developer.android.com/develop/ui/compose/performance
            - Outras fontes
            - https://proandroiddev.com/improving-performance-and-reducing-recomposition-in-jetpack-compose-a-case-study-4565cbef900d
            - https://www.droidcon.com/2024/09/19/jetpack-compose-improve-performance-via-one-view-one-state-pattern/
 */
class ThinkingInComposeRecompositionActivity : ComponentActivity() {
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