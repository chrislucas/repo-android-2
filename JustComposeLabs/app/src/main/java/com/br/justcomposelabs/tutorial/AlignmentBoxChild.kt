package com.br.justcomposelabs.tutorial

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showBackground = true)
@Composable
fun AlignmentBoxChild() {
    val alignments = listOf(
        "Top Start" to Alignment.TopStart,
        "Top Center" to Alignment.TopCenter,
        "Top End" to Alignment.TopEnd,
        "Top Start" to Alignment.CenterStart,
        "Top" to Alignment.Center,
        "Top End" to Alignment.CenterEnd,
        "Bottom Stqrt" to Alignment.BottomStart,
        "Bottom Center" to Alignment.BottomCenter,
        "Bottom End" to Alignment.BottomEnd,
    )

    Box(modifier = Modifier.fillMaxSize()) {
        alignments.forEach { (content, alignment) ->
            Text(
                content,
                modifier = Modifier.align(alignment)
            )
        }
    }
}