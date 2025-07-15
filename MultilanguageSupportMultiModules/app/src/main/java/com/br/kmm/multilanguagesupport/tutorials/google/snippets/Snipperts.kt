package com.br.kmm.multilanguagesupport.tutorials.google.snippets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.tooling.preview.Preview
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath

/*
    https://developer.android.com/develop/ui/compose/libraries
    https://github.com/android/snippets/blob/9d6278951bdcc7b269f9d6d3131fc947319a4c87/compose/snippets/src/main/java/com/example/compose/snippets/graphics/ShapesSnippets.kt
 */

@Preview
@Composable
fun BasicShapeCanvas() {
    Box(
        modifier = Modifier
            .drawWithCache {
                val roundedPolygon = RoundedPolygon(
                    numVertices = 6,
                    radius = size.minDimension / 2,
                    centerX = size.width / 2,
                    centerY = size.height / 2
                )
                val roundedPolygonPath = roundedPolygon.toPath().asComposePath()
                onDrawBehind {
                    drawPath(roundedPolygonPath, color = Color.Blue)
                }
            }
            .fillMaxSize()
    )
}