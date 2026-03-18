package com.br.uistatepatternviewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
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
            delay(DELAY_LOADING)
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
            delay(DELAY_FETCH_NEWS)
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
            LoadingOverlayComponent()
        }
        News(news = news)
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoadingOverlayComponent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.2f))
            .testTag("loading_overlay")
            .zIndex(1f),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.testTag("progress_indicator")
        )
    }
}

/*
    https://developer.android.com/develop/ui/compose/tooling/previews#preview-data
 */


@Composable
fun News(
    modifier: Modifier = Modifier,
    news: List<News>
) {
    Column(modifier = modifier
        .systemBarsPadding()
        .navigationBarsPadding()
        .padding(start = 8.dp, end = 8.dp)
    ) {
        if (news.isNotEmpty()) {
            Text(
                "News: ${news.size}",
                style = MaterialTheme.typography.headlineMedium
            )
            LazyColumn {
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


// Preview for the `News` composable. We must reference the data class using the full package
// name to avoid clashing with the `News` composable function.
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewsPreview() {
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

    News(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        news = sampleNews
    )
}

