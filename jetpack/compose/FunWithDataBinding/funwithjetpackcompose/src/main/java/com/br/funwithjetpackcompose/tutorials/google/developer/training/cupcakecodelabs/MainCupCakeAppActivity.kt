package com.br.funwithjetpackcompose.tutorials.google.developer.training.cupcakecodelabs

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
import com.br.funwithjetpackcompose.tutorials.google.developer.training.cupcakecodelabs.ui.theme.FunWithDataBindingTheme

/*
    Google Developer Training
    https://github.com/google-developer-training/

    CupCake Project
    basic-android-kotlin-compose-training-cupcake
    https://github.com/google-developer-training/basic-android-kotlin-compose-training-cupcake

    Codelabs
    https://developer.android.com/codelabs/basic-android-kotlin-compose-navigation#2
 */
class MainCupCakeAppActivity : ComponentActivity() {
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