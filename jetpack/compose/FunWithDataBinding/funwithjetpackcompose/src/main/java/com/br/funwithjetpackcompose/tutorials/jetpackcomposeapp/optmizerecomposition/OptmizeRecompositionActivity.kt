package com.br.funwithjetpackcompose.tutorials.jetpackcomposeapp.optmizerecomposition

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
import com.br.funwithjetpackcompose.tutorials.jetpackcomposeapp.optmizerecomposition.ui.theme.FunWithDataBindingTheme

/*
    https://www.jetpackcompose.app/
    https://www.jetpackcompose.app/articles/donut-hole-skipping-in-jetpack-compose
    https://medium.com/@emad.shehadah92/what-is-donut-hole-skipping-in-jetpack-compose-bd33ad33cb6f
 */
class OptmizeRecompositionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FunWithDataBindingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting8(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting8(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview8() {
    FunWithDataBindingTheme {
        Greeting8("Android")
    }
}