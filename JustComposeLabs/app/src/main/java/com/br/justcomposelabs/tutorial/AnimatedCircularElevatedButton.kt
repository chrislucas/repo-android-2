package com.br.justcomposelabs.tutorial

import android.text.Layout
import android.widget.Toast
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

@Composable
fun AnimatedCircularElevatedSurface(onClick: () -> Unit = {}) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val elevation by animateDpAsState(
        targetValue = if (isPressed) 2.dp else 20.dp,
        label = "ElevationAnimation"
    )

    Surface(
        onClick = onClick,
        modifier = Modifier
            .systemBarsPadding()
            .statusBarsPadding()
            .size(128.dp),
        shape = CircleShape,
        tonalElevation = elevation,
        shadowElevation = elevation,
        interactionSource = interactionSource,
        color = MaterialTheme.colorScheme.primary,
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                Icons.Default.Favorite,
                contentDescription = "Favorite",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AnimatedCircularElevatedSurfacePreview() {
    JustComposeLabsTheme {
        val ctx = LocalContext.current
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            AnimatedCircularElevatedSurface {
                Toast.makeText(
                    ctx,
                    "Surface Clicked",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}