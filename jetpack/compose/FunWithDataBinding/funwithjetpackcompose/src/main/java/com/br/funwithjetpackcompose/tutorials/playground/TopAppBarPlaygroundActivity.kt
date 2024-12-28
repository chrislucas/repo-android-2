package com.br.funwithjetpackcompose.tutorials.playground

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
import com.br.funwithjetpackcompose.tutorials.playground.ui.theme.FunWithDataBindingTheme

/*
    TODO
    https://foso.github.io/Jetpack-Compose-Playground/material/topappbar/
    https://github.com/Foso/Jetpack-Compose-Playground/blob/master/app/src/main/java/de/jensklingenberg/jetpackcomposeplayground/mysamples/github/material/appbar/topappbar/TopAppBarSample.kt
 */
class TopAppBarPlaygroundActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FunWithDataBindingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting12(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting12(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview12() {
    FunWithDataBindingTheme {
        Greeting12("Android")
    }
}