package com.br.justcomposelabs.tutorial.google.paginglist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

/*
    https://developer.android.com/develop/ui/compose/quick-guides/content/display-paging-list
    https://developer.android.com/develop/ui/compose/quick-guides/content/custom-page-indicator
 */

@Preview(
    showSystemUi = false,
    showBackground = true,
    device = "spec:width=411dp,height=891dp",
    name = "HorizontalPagerSample"
)
@Composable
fun HorizontalPagerSample(modifier: Modifier = Modifier) {
    Column(modifier) {
        val pagerState = rememberPagerState(pageCount = {
            10
        })

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(.95f)
                .fillMaxSize()
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center,

                ) {
                Text(
                    text = "Page: $page",
                    fontSize = TextUnit(30f, TextUnitType.Sp)
                )
            }
        }

        Row(
            Modifier
                .wrapContentHeight()
                .weight(.05f)
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(16.dp)
                )
            }
        }
    }
}

