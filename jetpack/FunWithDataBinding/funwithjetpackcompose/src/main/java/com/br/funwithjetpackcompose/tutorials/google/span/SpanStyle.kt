package com.br.funwithjetpackcompose.tutorials.google.span

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

/*
    https://developer.android.com/reference/kotlin/androidx/compose/ui/text/SpanStyle?authuser=1
 */


@Composable
fun SampleTextSpan(
    content: List<String>,
    fontSize: TextUnit = 16.sp,
    modifier: Modifier = Modifier
) =
    Text(
        text = buildAnnotatedString {
            content.forEachIndexed { i, c ->
                if (i and 1 == 0) {
                    withStyle(style = SpanStyle(color = Color.Red)) { append(c) }
                } else {
                    withStyle(style = SpanStyle(color = Color.Blue)) { append(c) }
                }
            }
        }, fontSize = fontSize, modifier = modifier
    )


@Composable
fun HelloWorld() =
    Text(fontSize = 16.sp, text = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.Red)) { append("Hello") }
        withStyle(SpanStyle(color = Color.Blue)) { append(" World") }
    })


