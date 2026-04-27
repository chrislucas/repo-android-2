package com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp3.primitives.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.br.justcomposelabs.tutorial.canvas.geom.triangle.DraggableTriangleView

@Preview(showSystemUi = true, name = "DrawDraggableTriangleView")
@Composable
fun DrawDraggableTriangleView() {
    AndroidView(
        factory = { context ->
            DraggableTriangleView(context)
        },
        update = { view ->
            // Update the view if needed
            view.invalidate() // Redraw the view if necessary
        },
        modifier =
        Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .navigationBarsPadding(),
    )
}
