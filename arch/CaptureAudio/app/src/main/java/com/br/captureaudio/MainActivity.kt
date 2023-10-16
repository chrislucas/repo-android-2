package com.br.captureaudio

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
import com.br.captureaudio.ui.theme.CaptureAudioTheme

/**
 * Sharing audio input
 * https://developer.android.com/guide/topics/media/platform/sharing-audio-input
 *
 * Can I get the audioStream of other apps
 * https://stackoverflow.com/questions/59510560/can-i-get-the-audiostream-of-other-apps
 *
 * Capture video and audio playback
 * https://developer.android.com/guide/topics/media/platform/av-capture
 *
 * Capturing Audio in Android Q
 * https://android-developers.googleblog.com/2019/07/capturing-audio-in-android-q.html
 *
 * Audio Playback Capture in Android X
 * https://www.kodeco.com/11393023-audio-playback-capture-in-android-x
 *
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaptureAudioTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
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
    CaptureAudioTheme {
        Greeting("Android")
    }
}