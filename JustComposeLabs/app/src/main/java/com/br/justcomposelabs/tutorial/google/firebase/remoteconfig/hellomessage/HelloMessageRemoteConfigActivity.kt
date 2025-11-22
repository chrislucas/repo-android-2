package com.br.justcomposelabs.tutorial.google.firebase.remoteconfig.hellomessage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.br.justcomposelabs.R
import com.br.justcomposelabs.tutorial.google.firebase.remoteconfig.hellomessage.ui.theme.JustComposeLabsTheme

/*
    https://github.com/firebase/quickstart-android/blob/master/config/app/src/main/java/com/google/samples/quickstart/config/kotlin/MainActivity.kt

    https://github.com/firebase/quickstart-android/blob/master/config/README.md
    https://firebase.google.com/docs/remote-config/get-started?platform=android#firebase-console_2
 */
class HelloMessageRemoteConfigActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JustComposeLabsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.bakery_back),
            contentDescription = null
        )

        Text("Welcome")

        Button(onClick = {

        }) {
            Text("fetch remote welcome<")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JustComposeLabsTheme {
        Greeting()
    }
}