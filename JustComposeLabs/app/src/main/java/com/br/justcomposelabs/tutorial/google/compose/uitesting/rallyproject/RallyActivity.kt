package com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject

import ads_mobile_sdk.cu
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.components.RallyTopAppBar
import com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.ui.theme.JustComposeLabsTheme

/*
    https://developer.android.com/develop/ui/compose/testing
    https://m2.material.io/design/material-studies/rally.html
 */
class RallyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JustComposeLabsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RallyApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun RallyApp(modifier: Modifier = Modifier) {
    /*
        rememberSaveable
        https://developer.android.com/develop/ui/compose/state-saving?hl=pt-br#ui-logic
     */
    var currentScreen by rememberSaveable { mutableStateOf(RallyScreen.Overview) }
    val onChangeScreen: (RallyScreen) -> Unit = { screen -> currentScreen = screen }
    Scaffold(
        topBar = {
            RallyTopAppBar(
                allScreens = RallyScreen.entries,
                onChangeScreen = onChangeScreen,
                currentScreen = currentScreen
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            currentScreen.Content(onScreenChange = onChangeScreen)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JustComposeLabsTheme {
        RallyApp()
    }
}