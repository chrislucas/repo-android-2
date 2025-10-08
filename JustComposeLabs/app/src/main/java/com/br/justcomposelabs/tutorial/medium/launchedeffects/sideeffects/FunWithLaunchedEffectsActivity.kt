package com.br.justcomposelabs.tutorial.medium.launchedeffects.sideeffects

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.br.justcomposelabs.tutorial.medium.launchedeffects.sideeffects.ui.theme.JustComposeLabsTheme
import kotlinx.coroutines.NonCancellable.isActive
import kotlinx.coroutines.delay

/*
    https://www.droidcon.com/2021/09/24/jetpack-compose-side-effects-i-launchedeffect/
    https://medium.com/@sujathamudadla1213/what-is-launchedeffect-coroutine-api-android-jetpack-compose-76d568b79e63
 */
class FunWithLaunchedEffectsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JustComposeLabsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PulseEffect(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PulseEffect(modifier: Modifier = Modifier) {
    /*
        LaunchedEffect: run suspend functions in the scope of a composable
        https://developer.android.com/develop/ui/compose/side-effects
     */
    var pulseRateMs by remember { mutableLongStateOf(3000L) }
    val alpha = remember { Animatable(1f) }
    LaunchedEffect(pulseRateMs) { // Restart the effect when the pulse rate changes
        while (isActive) {
            delay(pulseRateMs) // Pulse the alpha every pulseRateMs to alert the user
            alpha.animateTo(0f)
            alpha.animateTo(1f)
        }
    }
}

@Composable
private fun TimerScreen() {
    /*
        https://www.droidcon.com/2021/09/24/jetpack-compose-side-effects-i-launchedeffect/
     */

    LaunchedEffect(Unit) { }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JustComposeLabsTheme {
        PulseEffect()
    }
}