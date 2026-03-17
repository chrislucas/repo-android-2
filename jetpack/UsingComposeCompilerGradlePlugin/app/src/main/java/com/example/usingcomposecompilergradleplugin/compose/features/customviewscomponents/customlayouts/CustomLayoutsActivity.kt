package com.example.usingcomposecompilergradleplugin.compose.features.customviewscomponents.customlayouts

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
import com.example.usingcomposecompilergradleplugin.compose.features.customviewscomponents.customlayouts.ui.theme.UsingComposeCompilerGradlePluginTheme


/**
 * https://developer.android.com/develop/ui/compose/layouts/custom
 *
 * Create custom view components
 * TODO ler para criar no package de projeto de views
 * https://developer.android.com/develop/ui/views/layout/custom-views/custom-components
 *
 */
class CustomLayoutsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UsingComposeCompilerGradlePluginTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting2(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    UsingComposeCompilerGradlePluginTheme {
        Greeting2("Android")
    }
}