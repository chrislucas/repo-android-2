package com.br.justcomposelabs.tutorial.google.compose.graphics.drawscope

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/*
    https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/drawscope/DrawScope
 */

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CanvasDrawScope() {
    Box(
        modifier =
        Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .navigationBarsPadding(),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.size(128.dp)) {
            // DrawScope.() -> Unit function
            /*
                Insere o conteúdo em 10 pixels nas laterais esquerda/direita e 12 pixels na horizontal.
             */
            inset(10.0f, 12.0f) {
                val quadrantSize = size / 2.0f
                drawRect(Color.Red, size = quadrantSize)
                rotate(45.0f) {
                    drawRect(Color.Green, size = quadrantSize)
                }
            }
        }
    }
}
