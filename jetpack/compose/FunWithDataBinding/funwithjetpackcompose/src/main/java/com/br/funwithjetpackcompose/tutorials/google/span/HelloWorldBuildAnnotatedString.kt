package com.br.funwithjetpackcompose.tutorials.google.span

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType

/*
    https://developer.android.com/reference/kotlin/androidx/compose/ui/text/SpanStyle?authuser=1
 */

@Preview(showSystemUi = true)
@Composable
fun HelloWorldBuildAnnotatedString() {
    Text(text = buildAnnotatedString {
        append("Hello")
        // push green text style so that any appended text will be green
        pushStyle(SpanStyle(color = Color.Companion.Green))
        // append new text, this text will be rendered as green

        append(" World")
        // pop the green style
        pop()

        // append a string without style
        append("!")
        // then style the last added word as red, exclamation mark will be red
        addStyle(SpanStyle(color = Color.Companion.Red), "Hello World".length, this.length)

        // Por que esse metodo foi chamado no exemplo ?
        // toAnnotatedString()

    }, fontSize = TextUnit(32.0f, TextUnitType.Sp))
}