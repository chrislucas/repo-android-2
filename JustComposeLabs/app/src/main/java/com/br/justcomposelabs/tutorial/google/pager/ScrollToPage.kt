package com.br.justcomposelabs.tutorial.google.pager

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/*
    Scroll to an item in the pager
    https://developer.android.com/develop/ui/compose/layouts/pager#scroll-to-item
 */

@Composable
fun HorizontalPageScrollToPageSample(words: List<String>) {
    val pagerState = rememberPagerState(pageCount = { words.size })
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .navigationBarsPadding()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { page ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = words[page],
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
            }
        }

        val coroutineScope = rememberCoroutineScope()
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                coroutineScope.launch {
                    pagerState.scrollToPage(words.size - 1)
                }
            },
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(text = "Scroll to last page")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HorizontalPageScrollToPageSamplePreview() {
    HorizontalPageScrollToPageSample(
        words = buildList {
            add("Maçã")
            add("Banana")
            add("Cereja")
            add("Damasco")
            add("Ervilha")
        }
    )
}
