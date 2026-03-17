package com.br.funwithjetpackcompose.tutorials.google.textstyle

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/*
    TODO
    https://developer.android.com/develop/ui/compose/text/style-text
    https://developer.android.com/develop/ui/compose/text/style-text#use-brush
 */

@Preview(showBackground = true)
@Composable
fun BrushTextStyling() {
    val gradientColors = listOf(Cyan, Color.DarkGray, Color.Blue, Color.Green)

    val lightBlue = Color(0xFF0066FF)
    val purple = Color(0xFF800080)

    Column {
        Text(
            text = "Configure your text using a built-in brush within TextStyle. For example, " +
                    "you can configure a linearGradient brush to your text as follows:",
            style = TextStyle(
                brush = Brush.linearGradient(colors = gradientColors)
            )
        )

        Spacer(Modifier.size(15.dp))

        Text(
            text = "Configure your text using a built-in brush within TextStyle. For example, " +
                    "you can configure a linearGradient brush to your text as follows:",
            style = TextStyle(
                brush = Brush.linearGradient(colors = gradientColors + listOf(lightBlue, purple))
            )
        )
    }
}