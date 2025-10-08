package com.br.justcomposelabs.tutorial.composable.shapes.customshapes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.ink.geometry.Triangle

/*
    TODO
    https://foso.github.io/Jetpack-Compose-Playground/cookbook/how_to_create_custom_shape/
 */

private val triangle = GenericShape { size, _ ->
    // 1)
    moveTo(size.width / 2f, 0f)

    // 2)
    lineTo(size.width, size.height)

    // 3)
    lineTo(0f, size.height)
}


@Preview(showBackground = false)
@Composable
fun TriangleShape() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(triangle)
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        Color(0xFFB174DE), // Indigo
                        Color(0xFF8F00FF),  // Violet
                        Color(0xFFD79378),  // Violet
                        Color(0xFFDAAE9C)  // Violet
                    )
                )
            )
    )
}