package com.br.justcomposelabs.tutorial.google.scrollindicator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.utils.composable.paddingEdgeToEdge
import timber.log.Timber

/*
    https://developer.android.com/develop/ui/compose/touch-input/pointer-input/scroll
 */


/*
    Pesquisar por Modifier.drawWithCache scroll indicator example
 */

val rememberPaint: @Composable (p: Color) -> Paint = { p ->
    remember {
        Paint().apply { color = p }
    }
}


fun Modifier.customScrollState(
    scrollState: LazyListState,
    thumbColor: Color = Color.DarkGray,
    trackColor: Color = Color.LightGray,
    trackWidth: Dp = 8.dp,
    rememberThumbColor: (Color) -> Color,
    rememberTrackColor: (Color) -> Color
) = this.drawWithCache {
    // Cache the paint objects for performance


    // Get the size of the composable area
    val contentSize = size


    onDrawBehind {
        // Draw the scrollbar track
        drawRect(
            color = rememberTrackColor(trackColor),
            topLeft = Offset(contentSize.width - trackWidth.toPx(), 0f),
            size = Size(trackWidth.toPx(), contentSize.height)
        )

        // Calculate scroll thumb properties
        val totalItems = scrollState.layoutInfo.totalItemsCount
        val visibleItems = scrollState.layoutInfo.visibleItemsInfo.size
        val viewportHeight = contentSize.height
        val contentHeight = totalItems * 100 // Approximate item height for calculation

        // Only draw thumb if content is scrollable
        if (totalItems > visibleItems) {
            val thumbHeight = (viewportHeight * visibleItems.toFloat()) / totalItems.toFloat()
            val scrollOffset = scrollState.firstVisibleItemScrollOffset
            val visibleTop = scrollState.firstVisibleItemIndex

            // Calculate thumb's top position
            val thumbTop = (visibleTop * (viewportHeight / totalItems.toFloat())) +
                    (scrollOffset * (viewportHeight / contentHeight.toFloat()))

            drawRect(
                color = rememberThumbColor(thumbColor),
                topLeft = Offset(contentSize.width - trackWidth.toPx(), thumbTop),
                size = Size(trackWidth.toPx(), thumbHeight)
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SimpleScrollBox() {
    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .size(100.dp)
            .verticalScroll(rememberScrollState())

    ){
        repeat(10) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    "Item $it", modifier = Modifier
                        .padding(8.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ScrollBoxes() {
    /*
        Pesquisar por: scroll indicator jetpack compose

        Scroll modifiers
        https://developer.android.com/develop/ui/compose/touch-input/pointer-input/scroll#scroll-modifiers
     */
    val scrollState = rememberScrollState() // For a standard Column

    LaunchedEffect(Unit) {
        scrollState.animateScrollTo(100)
    }

    val drawWithCache = Modifier.drawWithCache {

        val maxScroll = scrollState.maxValue
        val currentScroll = scrollState.value
        val scrollBarHeight = size.height * (size.height / (maxScroll + size.height))
        val scrollBarWidth = size.width * (size.width / (maxScroll + size.width))
        val scrollBarY = currentScroll.toFloat() / maxScroll * (size.height - scrollBarHeight)


        Timber.tag("SCROLL_STATE")
            .i("HWY: $scrollBarHeight, $scrollBarWidth, $scrollBarY")

        Timber.tag("SCROLL_STATE_DP")
            .i("HWY DP: ${scrollBarHeight.toDp()}, ${scrollBarWidth.toDp()}, $scrollBarY")


        Timber.tag("SCROLL_STATE_PX")
            .i(
                "HWY DP: ${scrollBarHeight.toDp().toPx()}, ${
                    scrollBarWidth.toDp().toPx()
                }, $scrollBarY"
            )

        Timber.tag("SCROLL_STATE_SP")
            .i(
                "HWY DP: ${scrollBarHeight.toSp()}, ${
                    scrollBarWidth.toSp()
                }, ${scrollBarY.toSp()}"
            )


        val scrollBarWidthPx = scrollBarWidth.toDp().toPx()


        Timber.tag("S_STATE_BOX_SIZE")
            .i(
                "${Size(scrollBarWidthPx, scrollBarHeight)}"
            )

        onDrawBehind {
            // draw a Scrollbar
            drawRect(
                color = Color.LightGray,
                topLeft = Offset(size.width - scrollBarWidthPx, 0f),
                size = Size(scrollBarWidthPx, scrollBarHeight)
            )

            // Desenha a scrollbar thumb
            drawRect(
                color = Color.DarkGray,
                topLeft = Offset(size.width - scrollBarWidthPx, scrollBarY),
                size = Size(scrollBarWidthPx, scrollBarHeight)
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .systemBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .background(Color.LightGray)
                .verticalScroll(scrollState)
                .size(100.dp)
                .then(drawWithCache)
        ) {
            repeat(25) {
                /*
                Card(
                    modifier = Modifier.background(Color.LightGray)
                ) {
                    Text(
                        "Item $it", modifier = Modifier
                            .background(Color.LightGray)
                            .padding(8.dp)
                    )
                }

                 */

                Text(
                    "Item $it", modifier = Modifier
                        .background(Color.LightGray)
                        .padding(8.dp)
                )
            }
        }
    }

}