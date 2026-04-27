package com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp3.primitives.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp8.TriangleView
import com.br.justcomposelabs.utils.composable.fillMaxSizePadding

@Preview(showSystemUi = true, name = "DrawTriangleView")
@Composable
fun DrawTriangleView() {
    AndroidView(
        factory = { context ->
            TriangleView(context)
        },
        update = { view ->
            view.invalidate()
        },
        modifier = Modifier.fillMaxSizePadding(),
    )
}
