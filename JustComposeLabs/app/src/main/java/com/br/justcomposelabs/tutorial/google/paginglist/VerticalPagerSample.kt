package com.br.justcomposelabs.tutorial.google.paginglist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(
    showSystemUi = false,
    showBackground = true,
    device = "spec:width=411dp,height=891dp",
    name = "VerticalPagerSample"
)
@Composable
fun VerticalPagerSample(modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState(pageCount = {
        10
    })

    VerticalPager(state = pagerState) { page ->
        Text(text = "Page: $page", modifier = Modifier.fillMaxWidth())
    }


}
