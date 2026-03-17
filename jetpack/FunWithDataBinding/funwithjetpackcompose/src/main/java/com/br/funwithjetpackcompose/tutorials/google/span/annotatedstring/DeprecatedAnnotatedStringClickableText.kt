package com.br.funwithjetpackcompose.tutorials.google.span.annotatedstring

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.UrlAnnotation
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/*
    https://proandroiddev.com/composing-annotatedstring-poetry-music-code-blogs-expandables-and-beyond-b5f7ec35a49b
 */



@OptIn(ExperimentalTextApi::class)
@Composable
fun DeprecatedClickableText() {
    val handler = LocalUriHandler.current
    val linkString = buildAnnotatedString {
        val str = "Abrir o site da google"
        val link = "google"
        val startIndex = str.indexOf(link)
        val endIndex = startIndex + link.length
        append(str)
        addStyle(
            style = SpanStyle(
                color = Color.Red,
                textDecoration = TextDecoration.Underline
            ),
            start = startIndex,
            end = endIndex
        )

        addUrlAnnotation(UrlAnnotation("https://google.com"), startIndex, endIndex)
    }

    ClickableText(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        text = linkString
    ) {
        linkString.getUrlAnnotations(it, it)
            .firstOrNull()
            ?.let { annotation ->
                handler.openUri(annotation.item.url)
            }
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewDeprecatedClickableText() {
    DeprecatedClickableText()
}