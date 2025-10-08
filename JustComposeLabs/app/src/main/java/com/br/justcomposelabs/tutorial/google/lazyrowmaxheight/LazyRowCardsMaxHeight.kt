package com.br.justcomposelabs.tutorial.google.lazyrowmaxheight

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

/*
    TODO
    LazyRow all cards same max height

    Find the maximum height:
    Use Modifier.onGloballyPositioned on each card to get its height after layout.
    Store the maximum height encountered in a mutableStateOf variable.

    Apply the maximum height:
    Once the maximum height is determined (or updated), apply it to each card using Modifier.height(maxHeight.dp).

    Explanation:
    maxHeightPx stores the height of the tallest card in pixels.

    onGloballyPositioned is a modifier that provides the LayoutCoordinates of the composable, including its size.
    The maxOf function ensures maxHeightPx always holds the largest height found so far.

    LocalDensity.current and with(density) { maxHeightPx.toDp() }
    are used to convert the pixel height to Dp (density-independent pixels)
    for use with Modifier.height().
    This approach ensures that all cards in your LazyRow will adjust their
    height to match the tallest card, creating a visually consistent layout.
 */




@Composable
fun LazyRowWithUniformCardHeight(modifier: Modifier= Modifier, cardDataList: List<MockContent>) { // cardDataList represents your data for each card
    var maxHeightPx by remember { mutableIntStateOf(1) }
    val density = LocalDensity.current
    LazyRow(modifier) {
        items(cardDataList) { cardData ->
            Card(
                modifier = Modifier
                    .onGloballyPositioned { coordinates ->
                        maxHeightPx = maxOf(maxHeightPx, coordinates.size.height)
                    }
                    .padding(2.dp)

                    //.height(with(density) { maxHeightPx.toDp() }) // Apply the maximum height
            ) {
                // Your card content goes here, using 'cardData'
                // Example: Text(text = cardData)

                val (f, s, t) = cardData
                Column {
                    f?.let { Text(text = it, modifier = Modifier.padding(16.dp)) }
                    s?.let { Text(text = it, modifier = Modifier.padding(16.dp)) }
                    t?.let { Text(text = it, modifier = Modifier.padding(16.dp)) }
                }
            }
        }
    }
}

/*
    https://medium.com/androiddevelopers/preview-and-test-your-apps-edge-to-edge-ui-da645c905d78
 */

@Preview(showSystemUi = true, showBackground = true, device = "spec:parent=pixel_8")
@Preview(showSystemUi = true, showBackground = true, device = "spec:parent=pixel_8,navigation=buttons")
@Preview(showSystemUi = true, showBackground = true, device = "spec:width=1080px,height=2400px,cutout=punch_hole")
@Preview(showSystemUi = true, showBackground = true, device = "spec:width=1080px,height=2400px,cutout=tall")
@Preview(showSystemUi = true, showBackground = true, device = "spec:width=1080px,height=2400px,cutout=corner")
@Preview(showSystemUi = true, showBackground = true, device = "spec:width=1080px,height=2400px,cutout=double")
@Composable
private fun LazyRowWithUniformCardHeightPreview() {
    JustComposeLabsTheme {
        LazyRowWithUniformCardHeight(cardDataList = items)
    }
}