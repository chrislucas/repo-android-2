package com.br.justcomposelabs.tutorial.medium.coroutines.snapshotflow

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.util.query
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged

/*
    https://www.linkedin.com/posts/pawan-jeswani-android_jetpackcompose-androiddev-kotlin-activity-7383486754026074112-1JEL?utm_source=share&utm_medium=member_android&rcm=ACoAAAucV48BgdbCBoMmXrArsYNH-OL_jFGhzfk
 */


interface SearchRepository {
    fun performSearch(query: String)
}

class QueryViewModel(private val searchRepository: SearchRepository) : ViewModel() {
    val observableQueryState = MutableStateFlow("")

    val queryState: StateFlow<String> = observableQueryState

    fun performSearch(query: String) {
        searchRepository.performSearch(query)
    }
}



@OptIn(FlowPreview::class)
@Composable
fun RealTimeQuery(viewModel: QueryViewModel = viewModel()) {

    val searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        snapshotFlow { searchQuery }
            .debounce(500L)
            .distinctUntilChanged()
            .collectLatest(viewModel::performSearch)
    }
}