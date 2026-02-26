package com.br.cupcakeappnavigation

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
import com.br.cupcakeappnavigation.ui.theme.CupCakeAppNavigationTheme

/*
    https://developer.android.com/codelabs/basic-android-kotlin-compose-navigation?hl=pt-br#2

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
            CupCakeAppNavigationTheme {
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
    CupCakeAppNavigationTheme {
        Greeting("Android")
    }
}