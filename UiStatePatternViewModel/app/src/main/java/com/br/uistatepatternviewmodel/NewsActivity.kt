package com.br.uistatepatternviewmodel

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.twotone.Send
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.br.uistatepatternviewmodel.ui.components.LoadingOverlayLayout
import com.br.uistatepatternviewmodel.ui.components.NewsComponent
import com.br.uistatepatternviewmodel.ui.components.StateList
import com.br.uistatepatternviewmodel.ui.theme.UiStatePatternViewModelTheme
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
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

    /*
        Aqui poderia e talvez faça mais sentido uma sealed class
     */
    sealed interface NewsUIState {

        val news: List<News>

        data class Loading(override val news: List<News>) : NewsUIState
        data class ShowNews(override val news: List<News>) : NewsUIState

        data object Idle : NewsUIState {
            override val news: List<News>
                get() = emptyList()
        }
    }

    sealed interface NewsUiEffect {
        data object FetchNews : NewsUiEffect

        data object FinishFetchNews : NewsUiEffect
    }


    private val mutableUiState = MutableStateFlow<NewsUIState>(NewsUIState.Idle)
    val uiState: StateFlow<NewsUIState> = mutableUiState.asStateFlow()

    private val effect = Channel<NewsUiEffect>(capacity = Channel.BUFFERED)

    val uiEffect: Flow<NewsUiEffect> = effect.receiveAsFlow()

    init {
        viewModelScope.launch {
            mockFetchNews()
        }
    }

    fun fetchNews() {
        viewModelScope.launch {
            mockFetchNews()
        }
    }

    suspend fun mockFetchNews() {
        effect.send(NewsUiEffect.FetchNews)
        mutableUiState.update { oldState ->
            NewsUIState.Loading(oldState.news)
        }

        delay(DELAY_LOADING.milliseconds) // Simulate network delay
        val news = List(4) {
            News(
                title = getRandomString(5),
                description = "Description ${getRandomPhrase(200, " ")}"
            )
        }
        mutableUiState.update { oldState ->
            NewsUIState.ShowNews(oldState.news + news)
        }

        delay(DELAY_FETCH_NEWS.milliseconds)
        effect.send(NewsUiEffect.FinishFetchNews)
    }

    fun automaticFetchNews() {
        viewModelScope.launch {
            while (true) {
                mockFetchNews()
            }
        }
    }
}

@Composable
fun UiStatePatternNewsScreen(
    modifier: Modifier = Modifier,
    viewModel: NewsViewModelUiStatePattern = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val ctx = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is NewsViewModelUiStatePattern.NewsUiEffect.FetchNews -> {
                    Toast.makeText(
                        ctx,
                        "Updating News",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is NewsViewModelUiStatePattern.NewsUiEffect.FinishFetchNews -> {
                    Log.d("FETCH_NEWS", "finish")
                }
            }
        }
    }

    var showButton by remember { mutableStateOf(false) }

    var callback by remember { mutableStateOf({}) }

    val onScroll: (StateList) -> Unit = { state ->
        if (state.shouldLoadMore) {
            viewModel.fetchNews()
        }
        showButton = state.canIGoTopTop
        callback = state.onClick
    }

    Scaffold(
        topBar = {},
        floatingActionButton = {
            AnimatedVisibility(visible = showButton) {
                FloatingActionButton(onClick = callback) {
                    Icon(
                        Icons.AutoMirrored.TwoTone.Send,
                        contentDescription = "Voltar ao topo"
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = modifier.padding(paddingValues)) {
            when (uiState) {
                is NewsViewModelUiStatePattern.NewsUIState.Loading -> {
                    LoadingOverlayLayout()
                    NewsComponent(
                        news = uiState.news,
                        onScroll = onScroll,
                    )
                }

                is NewsViewModelUiStatePattern.NewsUIState.ShowNews -> {
                    NewsComponent(
                        news = uiState.news,
                        onScroll = onScroll,
                    )
                }

                is NewsViewModelUiStatePattern.NewsUIState.Idle -> {
                    EmptyStateComponent()
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun UiStatePatternNewsScreenNewsPreview() {
    // Sample data to preview the content state
    val sampleNews = List(4) { index ->
        News(
            title = "Sample Title #${index + 1}",
            description = "Description ${getRandomPhrase(400, " ")}"
        )
    }
    NewsComponent(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        news = sampleNews
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun UiStatePatternNewsScreenIdlePreview() {
    // Sample data to preview the content state
    UiStatePatternNewsScreen(modifier = Modifier.fillMaxSize())
}


@Preview(showBackground = true, showSystemUi = true, name = "UiStatePatternNewsScreen - Loading")
@Composable
fun UiStatePatternNewsScreenLoadingPreview() {
    // Simulate the loading UI as shown by UiStatePatternNewsScreen
    Column(modifier = Modifier.fillMaxSize()) {
        LoadingOverlayLayout()
    }
}


@Composable
fun EmptyStateComponent(message: String = "Empty State") {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            .testTag("empty_state"),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = TextStyle(
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EmptyStateComponentPreview() {
    UiStatePatternViewModelTheme {
        EmptyStateComponent(message = "No News Available")
    }
}
