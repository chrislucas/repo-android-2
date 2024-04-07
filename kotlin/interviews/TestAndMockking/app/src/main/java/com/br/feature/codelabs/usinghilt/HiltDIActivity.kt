package com.br.feature.codelabs.usinghilt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.br.feature.codelabs.usinghilt.ui.theme.TestAndMockkingTheme

/**
 * Sobre DI - Dependency Injection
 * https://developer.android.com/training/dependency-injection
 * Doc sobre Hilt
 * https://developer.android.com/training/dependency-injection/hilt-android
 *
 * https://developer.android.com/codelabs/android-hilt#0
 */
class HiltDIActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestAndMockkingTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting3("Android")
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
    TestAndMockkingTheme {
        Greeting3("Android")
    }
}