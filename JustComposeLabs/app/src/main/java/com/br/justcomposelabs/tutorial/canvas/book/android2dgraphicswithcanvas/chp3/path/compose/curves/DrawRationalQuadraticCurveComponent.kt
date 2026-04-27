package com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp3.path.compose.curves

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp3.path.RationalQuadraticBezierCurveView
import com.br.justcomposelabs.utils.composable.fillMaxSizePadding

@Preview(showBackground = true)
@Composable
fun DrawRationalQuadraticCurveComponent() {
    AndroidView(
        factory = {
            RationalQuadraticBezierCurveView(it)
        },
        update = { it.invalidate() },
        modifier = Modifier.fillMaxSizePadding(),
    )
}
