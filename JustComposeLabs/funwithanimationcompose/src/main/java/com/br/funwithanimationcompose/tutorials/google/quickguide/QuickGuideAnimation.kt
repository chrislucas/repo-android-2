package com.br.funwithanimationcompose.tutorials.google.quickguide

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.funwithanimationcompose.codelabs.animatingelements.start.ui.theme.JustComposeLabsTheme

/*
    https://developer.android.com/develop/ui/compose/animation/quick-guide
 */


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AnimatedVisibilityModifierAlpha() {
    JustComposeLabsTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(
                    WindowInsets.safeDrawing.asPaddingValues()
                )
        ) {
            var visible by remember { mutableStateOf(true) }

            val animatedAlpha by animateFloatAsState(
                targetValue = if (visible) 1.0f else 0f,
                label = "alpha"
            )

            Box(
                modifier = Modifier
                    .size(200.dp)
                    .graphicsLayer {
                        alpha = animatedAlpha
                    }
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(red = 10, green = 141, blue = 255, alpha = 255))
                    .align(Alignment.TopCenter),
                contentAlignment = Alignment.Center
            ) {
                Text("Box")
            }

            Button(modifier = Modifier.align(Alignment.BottomCenter), onClick = {
                visible = !visible
            }) {
                Text( text = if (visible) "hide" else "show")
            }
        }
    }
}