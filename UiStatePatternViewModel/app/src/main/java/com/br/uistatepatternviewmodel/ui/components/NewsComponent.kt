package com.br.uistatepatternviewmodel.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.uistatepatternviewmodel.News
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

data class StateList(
    val shouldLoadMore: Boolean,
    val canIGoTopTop: Boolean,
    val onClick: () -> Unit
)

@Composable
fun NewsComponent(
    modifier: Modifier = Modifier,
    news: List<News>,
    onScroll: (StateList) -> Unit = {},
) {
    val listState = rememberLazyListState()

    val shouldLoadMore by remember {
        derivedStateOf {
            val lastVisibleIndex = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            val totalItems = listState.layoutInfo.totalItemsCount - 2
            lastVisibleIndex > totalItems
        }
    }

    val canIGoTopTop by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }

    val coroutineScope = rememberCoroutineScope()

    val scrollTo = {
        coroutineScope.launch { listState.animateScrollToItem(0) }
        Unit
    }

    val stateList by remember {
        derivedStateOf {
            StateList(
                shouldLoadMore,
                canIGoTopTop,
                onClick = if (canIGoTopTop) scrollTo else {
                    {}
                }
            )
        }
    }

    LaunchedEffect(stateList) {
        snapshotFlow { stateList }
            .distinctUntilChanged()
            .collect { state ->
                onScroll(state)
            }
    }

    Column(
        modifier = modifier
            .systemBarsPadding()
            .navigationBarsPadding()
            .padding(start = 8.dp, end = 8.dp)
    ) {
        if (news.isNotEmpty()) {
            Text(
                "News: ${news.size}",
                style = MaterialTheme.typography.headlineMedium
            )
            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxWidth()
            ) {
                items(news) { newsItem ->
                    key(newsItem.title) {
                        Card(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 8.dp, bottom = 8.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = newsItem.title,
                                    style = TextStyle(
                                        fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                                        fontStyle = MaterialTheme.typography.headlineSmall.fontStyle,
                                        fontWeight = MaterialTheme.typography.headlineSmall.fontWeight
                                    ),
                                    maxLines = 1
                                )
                                Text(
                                    text = newsItem.description,
                                    style = TextStyle(
                                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                        fontStyle = MaterialTheme.typography.bodyMedium.fontStyle,
                                        fontWeight = MaterialTheme.typography.bodyMedium.fontWeight
                                    ),
                                    maxLines = 10
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

/*
    https://developer.android.com/develop/ui/compose/tooling/previews#preview-data
 */


// Preview for the `News` composable. We must reference the data class using the full package
// name to avoid clashing with the `News` composable function.
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewsComponentPreview() {
    val sampleNews = listOf(
        News(
            title = "Breaking: Compose Preview",
            description = "This is a sample description to demonstrate a preview of the News composable."
        ),
        News(
            title = "Kotlin is great",
            description = "Compose makes UI development concise and enjoyable."
        ),
        News(
            title = "Preview #3",
            description = "Another example item to show list rendering and spacing."
        )
    )

    NewsComponent(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        news = sampleNews
    )
}


@Composable
private fun TrackScrollList() {
    val listState = rememberLazyListState()

    val showButton by remember { derivedStateOf { listState.firstVisibleItemIndex > 0 } }

    AnimatedVisibility(visible = showButton) {
        ScrollListTopComponent(
            component = { onClick ->
                Box(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = onClick,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RectangleShape
                    ) {
                        Text("Scroll to Top")
                    }
                }
            },
            onClick = {
                listState.animateScrollToItem(0)
            }
        )
    }
}
