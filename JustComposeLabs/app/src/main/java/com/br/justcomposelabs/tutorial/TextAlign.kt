package com.br.justcomposelabs.tutorial

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun VerticallyCenteredText() {
    Text(
        text = "Vertically Centered Text",
        modifier = Modifier
            .fillMaxWidth() // Fill the width of the parent
            .height(100.dp) // Give it a fixed height
            .wrapContentHeight(align = Alignment.CenterVertically), // Vertically center the content
        // textAlign = TextAlign.Center // Horizontally center the content
    )
}


@Preview(showBackground = true)
@Composable
fun CenterHorizontallyText() {
    Text(
        text = "Vertically Centered Text",
        modifier = Modifier
            .fillMaxWidth() // Fill the width of the parent
            .height(100.dp) // Give it a fixed height
            .wrapContentWidth(align = Alignment.CenterHorizontally), // Vertically center the content

    )
}


@Preview(showBackground = true)
@Composable
fun WrapContentSizeCenterText() {
    Text(
        text = "Centered Text",
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .wrapContentSize(align = Alignment.Center)
    )
}


@Preview(showBackground = true)
@Composable
fun WrapContentSizeCenterStartText() {
    Text(
        text = "Centered Text",
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .wrapContentSize(align = Alignment.CenterStart)
    )
}

@Preview(showBackground = true)
@Composable
fun WrapContentSizeCenterEndText() {
    Text(
        text = "Centered Text",
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .wrapContentSize(align = Alignment.CenterEnd)
    )
}


@Preview(showBackground = true)
@Composable
fun WrapContentSizeTopStartText() {
    Text(
        text = "Top Start",
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .wrapContentSize(align = Alignment.TopStart)
    )
}


@Preview(showBackground = true)
@Composable
fun WrapContentSizeTopEndText() {
    Text(
        text = "Top End",
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .wrapContentSize(align = Alignment.TopEnd)
    )
}

@Preview(showBackground = true)
@Composable
fun WrapContentSizeTopCenterText() {
    Text(
        text = "Top End",
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .wrapContentSize(align = Alignment.TopCenter)
    )
}