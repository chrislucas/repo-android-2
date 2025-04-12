package com.br.funwithjetpackcompose.tutorials.google.docs.animation.stepper

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
import com.br.funwithjetpackcompose.tutorials.google.docs.animation.stepper.ui.theme.FunWithDataBindingTheme

/*
    TODO inspiracoes
    https://gist.github.com/manueldidonna/9ff6044c5aa3c8dc2357caaf96183d50
    https://www.linkedin.com/posts/ardakazanci_jetpackcompose-androidprogramming-androiddevelopment-ugcPost-7311762125721362432-4nbl?utm_source=share&utm_medium=member_android&rcm=ACoAAAucV48BgdbCBoMmXrArsYNH-OL_jFGhzfk
 */
class StepperAnimatedActivity : ComponentActivity() {
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