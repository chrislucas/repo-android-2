package com.br.justcomposelabs.tutorial.google.compose.graphics.shapes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath

/*
    https://developer.android.com/develop/ui/compose/graphics/draw/shapes
 */


val hexagonShape = Modifier.drawWithCache {
    val shape = RoundedPolygon(
        numVertices = 6,
        radius = size.minDimension / 2f,
        centerX = size.width / 2f,
        centerY = size.height / 2f,
    )
    val roundedPolygonPath = shape.toPath().asComposePath()
    onDrawBehind {
        drawPath(
            path = roundedPolygonPath,
            color = Color(0xFF673AB7)
        )
    }
}.fillMaxSize()

val triangleShape = Modifier.drawWithCache {
    val shape = RoundedPolygon(
        numVertices = 3,
        radius = size.minDimension / 2f,
        centerX = size.width / 2f,
        centerY = size.height / 2f,
    )
    val roundedPolygonPath = shape.toPath().asComposePath()
    onDrawBehind {
        rotate(degrees = 30f) {
            drawPath(
                path = roundedPolygonPath,
                color = Color(0xFF673AB7)
            )
        }
    }
}.fillMaxSize()


val shape = Modifier.drawWithCache {
    val shape = RoundedPolygon(
        numVertices = 3,
        radius = size.minDimension / 2f,
        centerX = size.width / 2f,
        centerY = size.height / 2f,
    )
    val roundedPolygonPath = shape.toPath().asComposePath()
    onDrawBehind {

        drawPath(
            path = roundedPolygonPath,
            color = Color(0xFF673AB7)
        )

        rotate(degrees = 180f) {
            drawPath(
                path = roundedPolygonPath,
                color = Color(0xFF673AB7)
            )
        }
    }
}.fillMaxSize()


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HexagonBox() {
    /*
        https://developer.android.com/develop/ui/compose/graphics/draw/shapes#create-polygons
     */

    Column(modifier = Modifier.fillMaxSize()
        .systemBarsPadding()
        .statusBarsPadding()) {
        Box(modifier = hexagonShape) {
            // Content of the box
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TriangleBox() {
    /*
        https://developer.android.com/develop/ui/compose/graphics/draw/shapes#create-polygons
     */

    Column(modifier = Modifier.fillMaxSize()
        .systemBarsPadding()
        .statusBarsPadding()) {
        Box(modifier = triangleShape) {
            // Content of the box
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShapeRotatedBox() {
    /*
        https://developer.android.com/develop/ui/compose/graphics/draw/shapes#create-polygons
     */

    Column(modifier = Modifier.fillMaxSize()
        .systemBarsPadding()
        .statusBarsPadding()) {
        Box(modifier = shape) {
            // Content of the box
        }
    }
}