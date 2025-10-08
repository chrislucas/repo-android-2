package com.br.justcomposelabs.tutorial.medium.fillingmaxsize

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.br.scaffoldttopbarsample.ui.theme.JustComposeLabsTheme

@Composable
fun FillMaxSizeLazyRow(
    modifier: Modifier = Modifier,
    messages: List<Pair<String?, String?>>
) {
    LazyRow(
        modifier = modifier
            .fillMaxSize()
            .padding(WindowInsets.safeDrawing.asPaddingValues()),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(messages.size) {
            MessageCard(message = messages[it])
        }
    }
}


@Composable
fun MessageCard(message: Pair<String?, String?>, height: Dp = 123.dp) {
    val ctx = LocalContext.current
    Card(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .width(130.dp)
            .clickable {
                Toast.makeText(
                    ctx,
                    "Clicked", Toast.LENGTH_SHORT
                ).show()
            },
        shape = RoundedCornerShape(6.dp),
        elevation = CardDefaults.cardElevation(16.dp),
    ) {
        val (first, second) = message
        Row (modifier = Modifier.width(IntrinsicSize.Min)) {
            Box(
                modifier = Modifier
                    .width(8.dp)
                    .fillMaxHeight()
                    .background(Color.Red)
            )
            Column (
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                first?.let {
                    it
                    Text(it)
                }

                second?.let {
                    Text(it)
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun FillMaxSizeLazyRowPreview() {
    JustComposeLabsTheme {
        FillMaxSizeLazyRow(
            messages = listOf(
                "Hello" to "World",
                "Hello" to null,
                null to "World"
            )
        )
    }
}