package com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp3.conerpatheffect.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp3.conerpatheffect.CornerPathEffectPolygonView

/**
 * https://share.google/aimode/fs5jugMj8t47It3Q1
 * https://share.google/aimode/msHMP3NYCz8koc6iw
 */

@Composable
fun CornerPathEffectPolygonComponent() {
    AndroidView(factory = { ctx -> CornerPathEffectPolygonView(ctx) })
}
