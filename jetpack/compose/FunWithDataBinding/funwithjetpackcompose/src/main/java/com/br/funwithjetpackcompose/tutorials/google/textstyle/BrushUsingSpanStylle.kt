package com.br.funwithjetpackcompose.tutorials.google.textstyle

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview

/*
    https://developer.android.com/develop/ui/compose/text/style-text#apply-brush-to-span
 */


@Preview(showBackground = true)
@Composable
fun BrushSpanStyle() {
    Text(
        text = buildAnnotatedString {
            append("Do not allow people to dim your shine\n")
            withStyle(
                SpanStyle(
                    brush = Brush.linearGradient(
                        colors = listOf(Yellow, Red, Blue)
                    )
                )
            ) {
                append("because they are blinded.")
            }
            append("\nTell them to put some sunglasses on.")
        }
    )
}