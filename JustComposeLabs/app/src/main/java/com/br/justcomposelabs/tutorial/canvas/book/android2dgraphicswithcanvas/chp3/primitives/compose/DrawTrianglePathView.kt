package com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp3.primitives.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp3.primitives.TriangleNonClosedPathJoinView
import com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp3.primitives.TrianglePathJoinView

@Preview(showBackground = true)
@Composable
fun DrawTrianglePathView() {
    AndroidView(factory = { ctx ->
        TrianglePathJoinView(ctx)
    }, update = { view -> view.invalidate() }, modifier = Modifier
        .fillMaxSize()
        .systemBarsPadding()
        .navigationBarsPadding()
    )
}


@Preview(showBackground = true)
@Composable
fun DrawTriangleNonClosedPathView() {
    AndroidView(factory = { ctx ->
        TriangleNonClosedPathJoinView(ctx)
    }, update = { view -> view.invalidate() }, modifier = Modifier
        .fillMaxSize()
        .systemBarsPadding()
        .navigationBarsPadding()
    )
}