package com.br.justcomposelabs.tutorial.google.paginglist

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    showSystemUi = false,
    showBackground = true,
    device = "spec:width=411dp,height=891dp",
    name = "VerticalPagerSample",
)
@Composable
fun VerticalPagerSample(modifier: Modifier = Modifier) {
    val pagerState =
        rememberPagerState(pageCount = {
            10
        })

    VerticalPager(state = pagerState) { page ->
        Text(text = "Page: $page", modifier = Modifier.fillMaxWidth())
    }
}
