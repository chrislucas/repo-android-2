package com.example.usingcomposecompilergradleplugin

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
import com.example.usingcomposecompilergradleplugin.ui.theme.UsingComposeCompilerGradlePluginTheme

/**
 * TODO configurar Kotlin 2.0 nesse projeto
 * https://developer.android.com/jetpack/androidx/releases/compose-kotlin#kts
 * Compose Compiler Gradle plugin
 * https://developer.android.com/develop/ui/compose/compiler
 *
 * Kotlin 2.0 — Android project migration guide
 * https://medium.com/@kacper.wojciechowski/kotlin-2-0-android-project-migration-guide-b1234fbbff65
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UsingComposeCompilerGradlePluginTheme {
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
    UsingComposeCompilerGradlePluginTheme {
        Greeting("Android")
    }
}