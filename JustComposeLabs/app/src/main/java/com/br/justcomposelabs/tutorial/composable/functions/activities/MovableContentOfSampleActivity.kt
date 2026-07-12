package com.br.justcomposelabs.tutorial.composable.functions.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.tutorial.CenteredColumnSlotComponent
import com.br.justcomposelabs.tutorial.composable.functions.MovableCounterComponent
import com.br.justcomposelabs.tutorial.composable.functions.activities.ui.theme.JustComposeLabsTheme

class MovableContentOfSampleActivity : ComponentActivity() {
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
    /*
        val configuration = LocalConfiguration.current
        val isLandscape = remember {
            mutableStateOf(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
        }
     */

    /*
        https://share.google/aimode/QjwrAGMiRWdqJpdLg
     */
    var isLandscape by remember { mutableStateOf(false) }

    CenteredColumnSlotComponent(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
            .navigationBarsPadding(),
    ) {
        MovableCounterComponent(
            modifier = Modifier,
            isLandscape = isLandscape
        )

        Button(
            onClick = { isLandscape = !isLandscape },
            modifier = Modifier
                .padding(8.dp)

        ) {
            Text(text = "Change Layout Format")
        }
    }
}

@Preview(
    showBackground = true,
    name = "Landscape",
    device = "spec:width=640dp,height=360dp,dpi=480,orientation=landscape"
)
@Composable
fun GreetingLandscapePreview() {
    JustComposeLabsTheme {
        Greeting()
    }
}

@Preview(
    showBackground = true,
    name = "Portrait",
    device = "spec:width=360dp,height=640dp,dpi=480,orientation=portrait"
)
@Composable
fun GreetingPortraitPreview() {
    JustComposeLabsTheme {
        Greeting()
    }
}
