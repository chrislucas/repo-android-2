package com.br.funwithjetpackcompose.tutorials.google.span.annotatedstring

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/*
    https://developer.android.com/reference/kotlin/androidx/compose/ui/text/LinkAnnotation
    https://developer.android.com/reference/kotlin/androidx/compose/ui/text/LinkAnnotation.Clickable

    Clickable Text esta obsolete
    @Composable
    @Deprecated("Use Text or BasicText and pass an AnnotatedString that contains a LinkAnnotation")
    fun ClickableText
 */


private val composeDocLink = "https://developer.android.com/jetpack/compose"
private val url =
    LinkAnnotation.Url(composeDocLink, TextLinkStyles(style = SpanStyle(color = Color.Blue)))


@Composable
fun TextLink() {
    BasicText(buildAnnotatedString {
        append("Construia melhores apps mais rápido com ")
        withLink(url) {
            append("Jetpack Compose")
        }
    })
}

/*
    https://developer.android.com/reference/kotlin/androidx/compose/ui/text/LinkAnnotation.Clickable
 */

@Composable
fun TextLinkClickable() {
    val uriHandler = LocalUriHandler.current
    BasicText(buildAnnotatedString {
        append("Acesse o ")
        val link = LinkAnnotation.Url(
            composeDocLink,
            TextLinkStyles(style = SpanStyle(color = Color.Blue))
        ) {
            val url = (it as LinkAnnotation.Url).url
            uriHandler.openUri(url)
        }
        withLink(link) { append("link") }
    })
}

/*
    https://developer.android.com/reference/kotlin/androidx/compose/ui/text/LinkAnnotation.Url

    - Uma
 */

@Composable
fun StyledTextLinkClickable() {
    val ctx = LocalContext.current
    BasicText(buildAnnotatedString {
        append("Acesse o ")
        val link = LinkAnnotation.Url(
            composeDocLink,
            TextLinkStyles(
                style = SpanStyle(color = Color.Blue, textDecoration = TextDecoration.Underline),
                hoveredStyle = SpanStyle(textDecoration = TextDecoration.Underline),
                pressedStyle = SpanStyle(
                    color = Color.Red,
                    textDecoration = TextDecoration.Underline
                )
            )
        ) { linkAnnotation ->
            val url = (linkAnnotation as LinkAnnotation.Url).url
            Toast.makeText(ctx, url, Toast.LENGTH_LONG).show()
        }

        withLink(link) {
            append("link")
        }
    })
}


@Preview(showBackground = true)
@Composable
fun PreviewText() {
    MaterialTheme {
        Column {
            TextLink()
            Spacer(Modifier.size(16.dp))
            TextLinkClickable()
            Spacer(Modifier.size(16.dp))
            StyledTextLinkClickable()
        }
    }
}