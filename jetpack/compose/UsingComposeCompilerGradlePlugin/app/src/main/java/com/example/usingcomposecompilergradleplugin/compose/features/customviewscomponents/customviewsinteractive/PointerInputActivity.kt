package com.example.usingcomposecompilergradleplugin.compose.features.customviewscomponents.customviewsinteractive

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
import com.example.usingcomposecompilergradleplugin.compose.features.customviewscomponents.customviewsinteractive.ui.theme.UsingComposeCompilerGradlePluginTheme

/**
 * https://developer.android.com/develop/ui/compose/touch-input/pointer-input
 * Make a custom view interactive
 * TODO ler e usar no package view
 * https://developer.android.com/develop/ui/views/layout/custom-views/making-interactive
 *
 */
class PointerInputActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UsingComposeCompilerGradlePluginTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting3(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting3(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    UsingComposeCompilerGradlePluginTheme {
        Greeting3("Android")
    }
}