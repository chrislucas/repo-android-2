package com.br.justcomposelabs.tutorial.medium.animation.flip.cardflip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.size.Dimension
import com.br.justcomposelabs.tutorial.medium.animation.flip.cardflip.models.Card
import com.br.justcomposelabs.tutorial.medium.animation.flip.cardflip.ui.theme.JustComposeLabsTheme

/*
    TODO
    https://medium.com/bilue/card-flip-animation-with-jetpack-compose-f60aaaad4ac9

    https://github.com/KatieBarnett/Experiments/blob/main/jc-cardflip/src/main/java/dev/katiebarnett/experiments/jccardflip/models/Card.kt
    https://github.com/KatieBarnett/Experiments/blob/main/jc-cardflip/build.gradle.kts
 */
class CardFlipActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JustComposeLabsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Stack(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun Stack(modifier: Modifier = Modifier) {
    JustComposeLabsTheme {

    }
}