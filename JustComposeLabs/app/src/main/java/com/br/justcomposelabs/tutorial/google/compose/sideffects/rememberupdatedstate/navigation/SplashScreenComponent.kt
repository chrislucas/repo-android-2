package com.br.justcomposelabs.tutorial.google.compose.sideffects.rememberupdatedstate.navigation

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.br.justcomposelabs.R
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.delay

@Preview(showBackground = true)
@Composable
fun SplashScreenComponent(onTimeout: () -> Unit = {}) {
    val currentOnTimeout by rememberUpdatedState(onTimeout)
    val scale = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        delay(3000.milliseconds)
        currentOnTimeout()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            imageVector = Icons.Filled.Home,
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value)
        )
        Text(text = stringResource(R.string.app_name))
    }
}