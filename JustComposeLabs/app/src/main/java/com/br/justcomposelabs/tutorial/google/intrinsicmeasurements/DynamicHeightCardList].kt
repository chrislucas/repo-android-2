package com.br.justcomposelabs.tutorial.google.intrinsicmeasurements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

data class MyCardData(val title: String, val description: String)

@Composable
fun DynamicHeightCardList(dataList: List<MyCardData>) {
    LazyRow (
        modifier = Modifier
            .padding(WindowInsets.safeDrawing.asPaddingValues())
            .fillMaxHeight()
    ) {
        items(dataList) { cardData ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .defaultMinSize(minHeight = 150.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Text(text = cardData.title)
                    Text(text = cardData.description)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DynamicHeightCardListPreview() {
    val dataList = listOf(
        MyCardData(
            "Card 1",
            "Long text that exceeds the card's height.\n" +
                    "this is the fourth card.\nAdding more text to see how it looks."
        ),
        MyCardData("Card 2", "This is the second card."),
        MyCardData("Card 3", "This is the thrid card."),
        MyCardData("Card 4", "This is the fourth card."),
        MyCardData("Card 5", "This is the fifth card.")
    )

    JustComposeLabsTheme {
        DynamicHeightCardList(dataList = dataList)
    }
}