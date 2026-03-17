package com.br.uistatepatternviewmodel

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.br.uistatepatternviewmodel.ui.theme.UiStatePatternViewModelTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NewsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UiStatePatternViewModelTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UiStatePatternNewsScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

class NewsViewModelUiStatePattern : ViewModel() {

    sealed interface NewsUIState {
        data object Loading : NewsUIState
        data class ShowNews(val news: List<News>) : NewsUIState
    }

    private val _uiState = MutableStateFlow<NewsUIState>(NewsUIState.Loading)
    val uiState: StateFlow<NewsUIState> = _uiState.asStateFlow()

    private val currentNews: MutableList<News> = mutableListOf()

    init {
        viewModelScope.launch {
            fetchNews()
        }
    }

    private suspend fun fetchNews() {
        while (true) {
            _uiState.update { NewsUIState.Loading }
            delay(DELAY_LOADING) // Simulate network delay
            val news = List(4) {
                News(
                    title = getRandomString(5),
                    description = "Description ${getRandomPhrase(200, " ")}"
                )
            }
            _uiState.update {
                currentNews.addAll(news)
                NewsUIState.ShowNews(currentNews.toList())
            }

            delay(DELAY_FETCH_NEWS)
        }
    }
}

@Composable
fun UiStatePatternNewsScreen(
    modifier: Modifier = Modifier,
    viewModel: NewsViewModelUiStatePattern = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Box(modifier = modifier) {
        when (val state = uiState) {
            is NewsViewModelUiStatePattern.NewsUIState.Loading -> {
                LoadingOverlayComponent()
            }

            is NewsViewModelUiStatePattern.NewsUIState.ShowNews -> {
                Toast.makeText(
                    LocalContext.current,
                    "News Updated: ${state.news.size} items",
                    Toast.LENGTH_SHORT
                ).show()

                News(
                    modifier,
                    news = state.news
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "UiStatePatternNewsScreen - ShowNews")
@Composable
private fun UiStatePatternNewsScreenPreview() {
    // Sample data to preview the content state
    val sampleNews = List(4) { index ->
        News(
            title = "Sample Title #${index + 1}",
            description = "Description ${getRandomPhrase(400, " ")}"
        )
    }

    News(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        news = sampleNews
    )
}


@Preview(showBackground = true, showSystemUi = true, name = "UiStatePatternNewsScreen - Loading")
@Composable
fun UiStatePatternNewsScreenLoadingPreview() {
    // Simulate the loading UI as shown by UiStatePatternNewsScreen
    Column(modifier = Modifier.fillMaxSize()) {
        LoadingOverlayComponent()
    }
}
