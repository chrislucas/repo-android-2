package com.br.justcomposelabs.tutorial.google.lazilyloadwithpaging

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey

/*
    Lazily load data with lists and Paging
    https://developer.android.com/develop/ui/compose/quick-guides/content/lazily-load-list

    Key points about the code
    LazyColumn: This composable is used to display a large list of items (messages) efficiently.
    It only renders the items that are visible on the screen, thus saving resources and memory.

    The lazyPagingItems object efficiently manages the loading and presentation of paged data
     within the LazyColumn. It passes LazyPagingItems to items in the LazyColumn composable.

    MessageRow(message: Text) is responsible for rendering individual message items,
    likely displaying the sender and text of the message within a Card.

    MessagePlaceholder() provides a visual placeholder (a loading spinner)
    while the actual message data is being fetched, enhancing the user experience.
 */

data class Message(val author: String, val body: String)


@Composable
fun Messages(
    modifier: Modifier = Modifier,
    pager: Pager<Int, Message>
) {
    val lazyPagingItems = pager.flow.collectAsLazyPagingItems()
    LazyColumn(modifier) {
        items(
            lazyPagingItems.itemCount,
            key = lazyPagingItems.itemKey { it.hashCode() }
        ) { index ->
            val message = lazyPagingItems[index]
            if (message != null) {
                MessageRow(message = message)
            } else {
                MessagePlaceholder()
            }
        }
    }
}

@Preview
@Composable
fun MessagesPreview() {
    Messages(
        modifier = Modifier.fillMaxSize(),
        pager = Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                MockMessagePagingSource()
            },
        )
    )
}


/*
    https://developer.android.com/reference/kotlin/androidx/paging/PagingSource

    - Uma instancia de
 */

private const val STARTING_PAGE_INDEX = 1

class MockMessagePagingSource : PagingSource<Int, Message>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Message> {
        return LoadResult.Page(
            data = buildList {
                repeat(30) {
                    add(Message("Author $it", "Body $it"))
                }
            },
            prevKey = params.key,
            nextKey = params.key?.plus(1) ?: STARTING_PAGE_INDEX.plus(1)
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Message>): Int? {
        return state.anchorPosition
    }
}

@Composable
private fun MessagePlaceholder(modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun MessageRow(
    modifier: Modifier = Modifier,
    message: Message
) {
    Card(modifier = modifier.padding(8.dp)) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(message.author)
            Text(message.body)
        }
    }
}