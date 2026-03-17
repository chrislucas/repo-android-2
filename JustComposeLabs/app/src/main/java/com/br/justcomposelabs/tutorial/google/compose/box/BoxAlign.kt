package com.br.justcomposelabs.tutorial.google.compose.box

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showBackground = true)
@Composable
fun BoxWithIndividualAlignment() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center // Default alignment (ignored by aligned children)
    ) {
        // This text uses the TopStart alignment
        Text("Top Start", Modifier.align(Alignment.TopStart))
        Text("Top Center", Modifier.align(Alignment.TopCenter))
        Text("Top End", Modifier.align(Alignment.TopEnd))

        // This text uses the BottomCenter alignment
        Text("Bottom Center", Modifier.align(Alignment.BottomCenter))
        Text("Bottom Start", Modifier.align(Alignment.BottomStart))

        // This text uses the default Center alignment since no align modifier is specified
        Text("Center")
        Text("Center Start", Modifier.align(Alignment.CenterStart))
    }
}