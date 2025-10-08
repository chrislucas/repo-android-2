package com.br.funwithanimationcompose.tutorials.google.quickguide

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showSystemUi = true)
@Composable
fun AnimatedVisibilityCompose() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(red = 227, green = 75, blue = 75, alpha = 255)),
        contentAlignment = Alignment.TopCenter
    ) {
        var visible by remember { mutableStateOf(true) }

        AnimatedVisibility(visible) {
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(red = 10, green = 141, blue = 255, alpha = 255)),

                ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.Center)
                        .background(Color(red = 84, green = 148, blue = 45, alpha = 255))
                )
            }
        }

        ElevatedButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(10.dp)
                .fillMaxWidth()
            , onClick = {
                visible = !visible
            }) {
            Text(text = if (visible) "hide" else "show")
        }
    }
}