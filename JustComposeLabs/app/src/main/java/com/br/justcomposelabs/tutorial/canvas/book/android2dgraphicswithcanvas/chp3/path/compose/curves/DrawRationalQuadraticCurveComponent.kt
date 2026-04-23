package com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp3.path.compose.curves

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp3.path.RationalQuadraticCurveView
import com.br.justcomposelabs.utils.composable.FillMaxSizePad

@Preview(showBackground = true)
@FillMaxSizePad
@Composable
fun DrawRationalQuadraticCurveComponent() {
    AndroidView(
        factory = {
            RationalQuadraticCurveView(it)
        },
        update = { it.invalidate() },
    )
}