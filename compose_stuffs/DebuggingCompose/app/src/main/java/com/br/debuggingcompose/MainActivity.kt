package com.br.debuggingcompose

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
import com.br.debuggingcompose.ui.theme.DebuggingComposeTheme


/*
    https://www.linkedin.com/posts/rebeccafranks_jetpack-compose-executes-your-code-in-multiple-activity-7404916361954615296-xHdV?utm_source=share&utm_medium=member_desktop&rcm=ACoAAAucV48BgdbCBoMmXrArsYNH-OL_jFGhzfk

    https://developer.android.com/develop/ui/compose/tooling/stacktraces
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DebuggingComposeTheme {
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
    DebuggingComposeTheme {
        Greeting("Android")
    }
}