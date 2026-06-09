package com.br.justcomposelabs.tutorial.google.gemini.example.invertedlazycolumn

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme
import com.br.justcomposelabs.utils.takeRandomString

/*
    https://share.google/aimode/BvCzt1gTH1eVYb1U5
 */

@Composable
fun BottomToTopLazyColumn(values: List<String>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .border(BorderStroke(3.dp, Color.Black), shape = RectangleShape)
            .navigationBarsPadding(),
        contentAlignment = Alignment.BottomCenter,
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    1.dp,
                    Brush.radialGradient(
                        listOf(
                            Color.Red,
                            Color.Transparent
                        )
                    ),
                    shape = CircleShape
                )
                .heightIn(200.dp, 300.dp),
            reverseLayout = true
        ) {
            itemsIndexed(values) { idx, value ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(
                        text = "$idx: $value",
                        style = TextStyle(fontSize = 24.sp),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomToTopLazyColumnPreview() {
    JustComposeLabsTheme {
        BottomToTopLazyColumn(
            values = takeRandomString(100)
        )
    }
}
