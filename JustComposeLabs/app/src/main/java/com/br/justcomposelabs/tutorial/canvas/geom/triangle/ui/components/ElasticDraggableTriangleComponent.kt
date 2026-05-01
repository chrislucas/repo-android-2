package com.br.justcomposelabs.tutorial.canvas.geom.triangle.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.br.justcomposelabs.tutorial.canvas.geom.triangle.ElasticDraggableTriangleView
import com.br.justcomposelabs.tutorial.canvas.geom.triangle.ElasticDraggableTriangleViewII
import com.br.justcomposelabs.tutorial.canvas.geom.triangle.ElasticDraggableTriangleViewIII
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme
import com.br.justcomposelabs.utils.composable.fillMaxSizePadding

@Preview(showBackground = true)
@Composable
fun ElasticDraggableTrianglePreview() {
    JustComposeLabsTheme {
        AndroidView(
            factory = { ctx -> ElasticDraggableTriangleView(ctx) },
            update = { it.invalidate() },
            modifier = Modifier.fillMaxSizePadding()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ElasticDraggableTrianglePreviewII() {
    JustComposeLabsTheme {
        AndroidView(
            factory = { ctx -> ElasticDraggableTriangleViewII(ctx) },
            update = { it.invalidate() },
            modifier = Modifier.fillMaxSizePadding()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ElasticDraggableTrianglePreviewIII() {
    JustComposeLabsTheme {
        AndroidView(
            factory = { ctx -> ElasticDraggableTriangleViewIII(ctx) },
            update = { it.invalidate() },
            modifier = Modifier.fillMaxSizePadding()
        )
    }
}
