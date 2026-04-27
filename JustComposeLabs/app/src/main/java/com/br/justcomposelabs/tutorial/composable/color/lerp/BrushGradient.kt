package com.br.justcomposelabs.tutorial.composable.color.lerp

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/*
    https://developer.android.com/develop/ui/compose/graphics/draw/brush
 */

@Preview(showBackground = true)
@Composable
fun CircleGradient() {
    val brush =
        Brush.horizontalGradient(
            listOf(Color.Red, Color.Blue),
        )
    Column {
        Canvas(
            modifier = Modifier.size(200.dp),
            onDraw = {
                drawCircle(brush)
            },
        )

        /**
         * TODO using slider para modifcar a cor do gradiente
         */

        Slider(
            value = 0f,
            onValueChange = {
            },
            valueRange = 0f..1f,
        )
    }
}
