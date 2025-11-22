package com.br.justcomposelabs.tutorial.google.styletext.textlayout

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/*
    https://developer.android.com/develop/ui/compose/text/configure-layout
 */


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LongText() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("hello ".repeat(50), maxLines = 2)
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EllipsisText() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "hello ".repeat(50), maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MiddleEllipsisText() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "hello ".repeat(50),
            modifier = Modifier.width(200.dp),
            maxLines = 1,
            overflow = TextOverflow.MiddleEllipsis
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StartEllipsisText() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "hello ".repeat(50),
            modifier = Modifier.width(100.dp),
            maxLines = 1,
            overflow = TextOverflow.StartEllipsis
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ClipText() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "This is a very long text that will be clipped if it exceeds the container's width.",
            modifier = Modifier
                .width(200.dp)
                .border(
                    1.dp,
                    Color(0xFF8B77B6),
                    RectangleShape
                ),
            maxLines = 1,
            overflow = TextOverflow.Clip
        )
    }
}