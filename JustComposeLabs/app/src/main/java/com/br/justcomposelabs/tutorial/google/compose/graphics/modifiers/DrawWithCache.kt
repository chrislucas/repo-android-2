package com.br.justcomposelabs.tutorial.google.compose.graphics.modifiers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
    https://developer.android.com/reference/kotlin/androidx/compose/ui/draw/drawWithCache.modifier#(androidx.compose.ui.Modifier).drawWithCache(kotlin.Function1)

    At what angle does Modifier.drawWithCache draw the path?
        - drawWithCache nao desenha num angulo padrao especifico, ele
        prove uma  Scope function DrawScope e dentro desse escopo podemos
        desenhar, paths, shapes e/ou conteúdos em qualquer angulo utilizando
        a funcao rotate(degrees: Float)
 */

@Preview(showBackground = true)
@Composable
fun RectGradient() {
    Box(
        Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .navigationBarsPadding()
            .drawWithCache {
                val gradient =
                    Brush.linearGradient(
                        colors = listOf(Color.Red, Color.Blue),
                        start = Offset.Zero,
                        end = Offset(size.width, size.height),
                    )
                onDrawBehind { drawRect(gradient) }
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Hello World !!!",
            style = TextStyle(
                fontSize = 60.sp,
                color = Color.White,
                fontStyle = FontStyle.Italic
            ),
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SpacerGradient(size: Dp = 200.dp) {
    Spacer(
        modifier = Modifier
            .size(size)
            .drawWithCache {
                // Este objeto é criado apenas uma vez e armazenado em cache
                val brush = Brush.linearGradient(listOf(Color.Red, Color.Blue))
                onDrawBehind {
                    // Este bloco é chamado em cada frame de desenho
                    drawRect(brush)
                }
            }
    )
}
