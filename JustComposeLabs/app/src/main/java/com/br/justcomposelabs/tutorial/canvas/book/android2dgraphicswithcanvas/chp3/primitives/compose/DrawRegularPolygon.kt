package com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp3.primitives.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp3.primitives.RegularPolygonView
import com.br.justcomposelabs.utils.composable.fillMaxSizePadding

@Preview(showBackground = true)
@Composable
fun DrawRegularPolygon() {
    AndroidView(
        factory = { ctx ->
            RegularPolygonView(ctx)
        },
        update = {
            it.sides = 12
            it.invalidate()
        },
        modifier = Modifier.fillMaxSizePadding(),
    )
}
