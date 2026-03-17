package com.br.funwithcamera

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
import com.br.funwithcamera.ui.theme.FunWithCameraTheme


/*
    https://developer.android.com/jetpack/androidx/releases/camera?hl=pt-br

    Camera X
    -  https://developer.android.com/media/camera/camerax
    -  Codelabs
        - https://developer.android.com/codelabs/camerax-getting-started#0
    - Introducing CameraX 1.5: Powerful Video Recording and Pro-level Image Capture
        - https://android-developers.googleblog.com/2025/11/introducing-camerax-15-powerful-video.html
    - Support camera on multiple form factors
        - https://developer.android.com/develop/ui/compose/layouts/adaptive/camera-form-factors-support
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FunWithCameraTheme {
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
    FunWithCameraTheme {
        Greeting("Android")
    }
}