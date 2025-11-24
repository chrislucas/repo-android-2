package com.br.testmaplibre

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
import com.br.testmaplibre.ui.theme.TestmaplibreTheme

/*
    https://madewithmaplibre.com/sdks
    https://maplibre.org/maplibre-compose/getting-started/

    https://github.com/maplibre/maplibre-compose

    Read “A Practical Guide to MapLibre Compose“ by ShiYuHong on Medium:
    https://medium.com/@joy458963214/a-practical-guide-to-maplibre-compose-94a8cb6f79c4
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestmaplibreTheme {
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
    TestmaplibreTheme {
        Greeting("Android")
    }
}