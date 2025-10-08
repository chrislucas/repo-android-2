package com.br.justcomposelabs.tutorial.google.canvas.graphics.commonsdrawoperations


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview

/*
    https://developer.android.com/develop/ui/compose/graphics/draw/overview#common-drawing
 */

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun DrawTextCompose() {
    val textMeasurer = rememberTextMeasurer(34)

    Canvas(modifier = Modifier.fillMaxSize()) {
        drawText(textMeasurer, "Hello")
    }
}