package com.br.paginglibrarysamples.loadanddisplaypageddata

import androidx.paging.PagingSource
import androidx.paging.PagingState

/*
    https://developer.android.com/topic/libraries/architecture/paging/v3-paged-data
 */


data class User(val id: Int, val name: String)


interface UserClient {
    suspend fun getUsers(): List<User>
}

class PagingUserSource(
    val query: String,
    val userClient: UserClient
) : PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val nextPageNumber = params.key ?: 1
        return LoadResult.Page(
            data =  userClient.getUsers(),
            prevKey = null,
            nextKey = nextPageNumber
        )
    }
}