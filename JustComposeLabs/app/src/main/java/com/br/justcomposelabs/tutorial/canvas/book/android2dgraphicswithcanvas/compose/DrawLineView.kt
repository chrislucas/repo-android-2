package com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.br.justcomposelabs.R
import com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp3.LineView


@Preview(showSystemUi = true, name = "DrawLineView")
@Composable
fun DrawLineView() {

    AndroidView(
        factory = { context ->
            LineView(context)
        }, update = { view ->
            view.invalidate()
        }, modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .navigationBarsPadding()
    )
}