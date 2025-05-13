package com.br.funwithjetpackcompose.tutorials.medium.testing.testinginjetpackcompose

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
import com.br.funwithjetpackcompose.tutorials.medium.testing.testinginjetpackcompose.ui.theme.FunWithDataBindingTheme
/*
    https://developer.android.com/codelabs/jetpack-compose-testing#0

    Projetos em compose
    https://github.com/android/codelab-android-compose

    Testing cheatsheet
    https://developer.android.com/develop/ui/compose/testing/testing-cheatsheet

    Semantics
    https://developer.android.com/develop/ui/compose/testing/semantics
 */
class TestingInJetpackComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FunWithDataBindingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FunWithDataBindingTheme {
        Greeting()
    }
}