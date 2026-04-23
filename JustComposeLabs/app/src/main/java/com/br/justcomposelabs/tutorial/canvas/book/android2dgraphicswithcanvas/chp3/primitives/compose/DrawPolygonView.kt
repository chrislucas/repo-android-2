package com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp3.primitives.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp3.primitives.PolygonView
import com.br.justcomposelabs.utils.composable.fillMaxSizePadding

@Preview(showBackground = true)
@Composable
fun DrawPolygon() {
    AndroidView(
        factory = { ctx -> PolygonView(ctx) },
        update = { it.invalidate() },
        modifier = Modifier.fillMaxSizePadding()
    )
}
