package com.example.usingcomposecompilergradleplugin.compose.features.tutorials.medium.statehoisting

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
import com.example.usingcomposecompilergradleplugin.compose.features.tutorials.medium.statehoisting.ui.theme.UsingComposeCompilerGradlePluginTheme


/**
 * Unlock the Power of State Hoisting in Jetpack Compose
 * https://medium.com/@tenigada/unlock-the-power-of-state-hoisting-in-jetpack-compose-574f742c4721
 */
class FunWithStateHoistingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UsingComposeCompilerGradlePluginTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting4(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting4(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    UsingComposeCompilerGradlePluginTheme {
        Greeting4("Android")
    }
}