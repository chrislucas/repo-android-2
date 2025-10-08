package com.br.justcomposelabs.tutorial.google.lazyrowmaxheight

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


/*
    TODO estudar como resolver o problema de calcular o tamanho do maior card e fazer com que
    todos tenham esse tamanho.
        - O ideial seria fazer isso antes de renderizar todos

 */
data class MockContent(
    val first: String? = null,
    val second: String? = null,
    val third: String? = null
)


internal val items = listOf(
    MockContent(first = null, second = "Second", third = "Third"),
    MockContent(first = "First", second = null, third = null),
    MockContent(first = "First", second = "Second", third = null),
    MockContent(first = null, second = "Second", third = null),
    MockContent(first = "first", second = null, third = null),
    MockContent(first = "first", second = null, third = null),
    MockContent(first = "first", second = "second", third = null),
    MockContent(first = "first", second = null, third = "third"),
    MockContent(first = "first", second = null, third = "third"),
    MockContent(first = "first", second = null, third = "third"),
    MockContent(first = null, second = null, third = "third"),
    MockContent(first = "first", second = null, third = "third"),
    MockContent(first = "first", second = null, third = null),
    MockContent(first = "first", second = "second", third = "third"),
)


@Preview(showBackground = true)
@Composable
fun LazyRowWithMaxHeight() {
    val maxHeight = remember { mutableIntStateOf(0) }
    // Measure each card to find max height
    val heights = remember { mutableStateMapOf<Int, Int>() }

    LazyRow {
        itemsIndexed(items) { index, item ->
            Card(
                modifier = Modifier
                    // Measure the height of each card
                    .onGloballyPositioned { coordinates ->
                        val height = coordinates.size.height
                        heights[index] = height
                        val currentMax = heights.values.maxOrNull() ?: 0
                        if (currentMax > maxHeight.intValue) {
                            maxHeight.intValue = currentMax
                        }
                    }
                    .padding(2.dp)
                    .onSizeChanged { size ->

                    }) {
                // Content of the card
                val (f, s, t) = item
                Column {
                    f?.let { Text(text = it, modifier = Modifier.padding(16.dp)) }
                    s?.let { Text(text = it, modifier = Modifier.padding(16.dp)) }
                    t?.let { Text(text = it, modifier = Modifier.padding(16.dp)) }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LazyRowWithMaxHeightCalculateBefore() {
    val maxHeight = remember { mutableIntStateOf(0) }
    val measuringDone = remember { mutableStateOf(false) }
    val heights = remember { mutableMapOf<Int, Int>() }

    // First, measure all items and store max height
    if (!measuringDone.value) {
        LazyRow {
            itemsIndexed(items) { index, item ->
                Card(
                    modifier = Modifier
                        .onGloballyPositioned { coordinates ->
                            val height = coordinates.size.height
                            heights[index] = height
                            if (heights.size == items.size) {
                                val max = heights.values.maxOrNull() ?: 0
                                maxHeight.intValue = max
                                measuringDone.value = true // Measurements complete
                            }
                        }
                        .padding(2.dp)
                        .onSizeChanged { size ->

                        }
                ) {
                    TextCard(item)
                }
            }
        }
    } else {
        // Render LazyRow with fixed height equal to maxHeight
        LazyRow(
            modifier = Modifier.height(
                height = maxHeight.intValue.dp
                // with(LocalDensity.current) { maxHeight.intValue.toDp() }
            )
        ) {
            items(items) { item ->
                Card(modifier = Modifier.padding(2.dp)) {
                    TextCard(item)
                }
            }
        }
    }
}


@Composable
fun TextCard(mockContent: MockContent) {
    val (f, s, t) = mockContent
    Column {
        f?.let { Text(text = it, modifier = Modifier.padding(16.dp)) }
        s?.let { Text(text = it, modifier = Modifier.padding(16.dp)) }
        t?.let { Text(text = it, modifier = Modifier.padding(16.dp)) }
    }
}
