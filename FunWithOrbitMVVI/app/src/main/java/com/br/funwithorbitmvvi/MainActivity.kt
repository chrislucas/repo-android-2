package com.br.funwithorbitmvvi

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
import com.br.funwithorbitmvvi.ui.theme.FunWithOrbitMVVITheme


/*
    indicacao de uso desse artigo: https://jamshidbekboynazarov.medium.com/mvi-architecture-pattern-in-android-with-jetpack-compose-0fd61f9c67fd


    https://orbit-mvi.org/
    https://github.com/orbit-mvi/orbit-mvi
    https://medium.com/@mikhaltchenkov/orbit-mvi-a-complete-guide-to-the-state-management-framework-39c28e05cdd3
    https://orbit-mvi.org/Core/architecture/
    https://medium.com/@engineer.marwatalaat/orbit-mvi-and-containerhost-acf78b64d2ac
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FunWithOrbitMVVITheme {
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
    FunWithOrbitMVVITheme {
        Greeting("Android")
    }
}
