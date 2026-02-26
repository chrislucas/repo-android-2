package com.br.testingcupcakeapp

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
import com.br.testingcupcakeapp.ui.theme.TestingCupcakeAppTheme


/*
https://developer.android.com/codelabs/basic-android-kotlin-compose-test-cupcake?hl=pt-br#0

    TODO realizar teste de performance na UI


    https://developer.android.com/develop/ui/compose/performance/stability#summary
    Compiler reports: You can use the compiler reports to
    determine what stability is being inferred about your classes.

    Diagnose stability issues
    https://developer.android.com/develop/ui/compose/performance/stability/diagnose


 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestingCupcakeAppTheme {
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
    TestingCupcakeAppTheme {
        Greeting("Android")
    }
}