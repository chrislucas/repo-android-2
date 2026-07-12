package com.br.justcomposelabs.tutorial.google.pager

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

/*
    https://developer.android.com/develop/ui/compose/layouts/pager
 */

@Composable
fun HorizontalPagerComponent(words: List<String>) {
    val pagerState = rememberPagerState(pageCount = { words.size })
    HorizontalPager(state = pagerState) { page ->
        Card {
            Text(
                text = words[page],
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HorizontalPagerComponentPreview() {
    JustComposeLabsTheme {
        HorizontalPagerComponent(
            words = listOf("Compose", "Pager", "Horizontal", "Layout")
        )
    }
}

@Composable
fun VerticalPagerComponent(words: List<String>) {
    val pagerState = rememberPagerState(pageCount = { words.size })
    VerticalPager(state = pagerState) { page ->
        Card {
            Text(
                text = words[page],
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VerticalPagerComponentPreview() {
    JustComposeLabsTheme {
        VerticalPagerComponent(
            words = listOf("Compose", "Pager", "Vertical", "Layout")
        )
    }
}
