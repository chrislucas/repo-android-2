package com.br.perlinnoise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.br.perlinnoise.ui.theme.PerlinNoiseTheme

/**
 * https://pt.khanacademy.org/computing/computer-programming/programming-natural-simulations/programming-noise/a/perlin-noise
 * https://en.wikipedia.org/wiki/Perlin_noise
 * https://medium.com/mobile-app-development-publication/jetpack-compose-implement-perlin-noise-animation-interpolator-c94d3f1b64cd
 * https://betterprogramming.pub/create-beautiful-programmatic-random-drawing-easily-with-perlin-noise-40e1eb1439e
 *
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PerlinNoiseTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PerlinNoiseTheme {
        Greeting("Android")
    }
}