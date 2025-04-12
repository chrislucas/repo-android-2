package com.br.funwithjetpackcompose.tutorials.google.components.cards

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview

/*
    https://medium.com/@shmehdi01/spannable-text-in-jetpack-compose-a6ec33a20ec2
 */



@Composable
fun SpannableText(content: String, modifier: Modifier = Modifier) {
    val annotatedString = buildAnnotatedString {
        append("Texto numero #1")
        withStyle (style = SpanStyle(Color.Blue)) {
            append("")
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSpannableText() {
    SpannableText("Simple Text")
}