package com.br.optimizingcontextualcontentforassitant

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
import com.br.optimizingcontextualcontentforassitant.ui.theme.OptimizingContextualContentForAssitantTheme

/*
    https://developer.android.com/training/articles/assistant#implementing_your_own_assistant

    Google Assistant for Android
    https://developer.android.com/develop/devices/assistant/overview

    Projeto
    https://android.googlesource.com/platform/frameworks/base/+/marshmallow-release/tests/VoiceInteraction
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OptimizingContextualContentForAssitantTheme {
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
    OptimizingContextualContentForAssitantTheme {
        Greeting("Android")
    }
}