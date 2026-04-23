package com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp3.path.compose.curves

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp3.path.QuadraticBezierCurveView


@Preview(showBackground = true)
@Composable
fun DrawQuadraticBezierCurve() {
    AndroidView(
        factory = { ctx -> QuadraticBezierCurveView(ctx) },
        update = { it.invalidate() },
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .systemBarsPadding()
    )
}