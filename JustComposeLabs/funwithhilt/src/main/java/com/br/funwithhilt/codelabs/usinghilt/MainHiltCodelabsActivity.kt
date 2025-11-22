package com.br.funwithhilt.codelabs.usinghilt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.br.funwithhilt.R
import com.br.funwithhilt.codelabs.usinghilt.ui.theme.JustComposeLabsTheme

/**
 *  https://developer.android.com/codelabs/android-hilt#1

    Hilt application class
        -
 */
class MainHiltCodelabsActivity : ComponentActivity() {
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

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(
                id = R.string.hilt_codelabs_main_screen_message
            )
        )

        Button(onClick = {}) { }
        Button(onClick = {}) { }
        Button(onClick = {}) { }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JustComposeLabsTheme {
        Greeting()
    }
}