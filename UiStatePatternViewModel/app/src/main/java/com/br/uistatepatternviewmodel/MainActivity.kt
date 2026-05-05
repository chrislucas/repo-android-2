package com.br.uistatepatternviewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.br.uistatepatternviewmodel.ui.components.LoadingOverlayLayout
import com.br.uistatepatternviewmodel.ui.components.NewsComponent
import com.br.uistatepatternviewmodel.ui.theme.UiStatePatternViewModelTheme
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/*
    how to avoid multiples mutablestateflow in ViewModel ?
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UiStatePatternViewModelTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NewsScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

class NewsViewModelSeparateProperties : ViewModel() {
    private val mutableStateNews: MutableStateFlow<List<News>> = MutableStateFlow(emptyList())
    val stateNews: StateFlow<List<News>> = mutableStateNews.asStateFlow()

    private val mutableStateIsLoading: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val stateIsLoading: StateFlow<Boolean> = mutableStateIsLoading.asStateFlow()

    init {
        viewModelScope.launch {
            fetchNews()
        }
    }

    private suspend fun fetchNews() {
        while (true) {
            mutableStateIsLoading.update { true }
            delay(DELAY_LOADING.milliseconds)
            val news = buildList {
                repeat(4) {
                    add(
                        News(
                            title = getRandomString(5),
                            description = "Description ${getRandomPhrase(200, " ")}"
                        )
                    )
                }
            }
            mutableStateIsLoading.update { false }
            mutableStateNews.update { it + news }
            delay(DELAY_FETCH_NEWS.milliseconds)
        }
    }
}

@Composable
fun NewsScreen(
    modifier: Modifier = Modifier,
    viewModel: NewsViewModelSeparateProperties = viewModel()
) {

    /*
        collectAsStateWithLifecycle: collect a StateFlow as State in a lifecycle-aware manner
        collectasstate vs collectasstatewithlifecycle

        - Documentacao:
            - Flow: collectAsState() - https://developer.android.com/develop/ui/compose/state
                - CollectAsState é similar ao CollectAsStateWithLife, coleta valores de um Flow
                e transforma

     */
    val isLoading by viewModel.stateIsLoading.collectAsStateWithLifecycle()
    val news by viewModel.stateNews.collectAsStateWithLifecycle()
    Box(modifier = modifier) {
        if (isLoading) {
            LoadingOverlayLayout()
        }
        NewsComponent(news = news)
    }
}


