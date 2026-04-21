package com.br.justcomposelabs.tutorial.medium.sideeffects.snapshotflow.usecases

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.textservice.SentenceSuggestionsInfo
import android.view.textservice.SpellCheckerSession
import android.view.textservice.SuggestionsInfo
import android.view.textservice.TextInfo
import android.view.textservice.TextServicesManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import timber.log.Timber

/*
    https://share.google/aimode/sOwGhW7AI8wvAUNx4
 */

interface SpellCheckerCallback {
    fun checkSpelling(text: String)

    fun close()
}

data class SpellCheckResult(
    val offset: Int,
    val suggestions: List<String>
)

data class SpellCheckResultWithoutSuggestion(
    val offset: Int,
    val length: Int
) {
    val realLength: Int
        get() = offset + length
}

class SpellCheckerImpl(
    context: Context,
    private val onResultWithSuggestion: (List<SpellCheckResult>) -> Unit,
    private val onResultWithoutSuggestion: (List<SpellCheckResultWithoutSuggestion>) -> Unit
) : SpellCheckerSession.SpellCheckerSessionListener, SpellCheckerCallback {

    /*
        https://share.google/aimode/kfe6Kr9elM6jwiWP1
     */

    private val textServiceManager =
        context.getSystemService(Context.TEXT_SERVICES_MANAGER_SERVICE) as TextServicesManager
    private val spellCheckerSession: SpellCheckerSession? = textServiceManager.newSpellCheckerSession(
        null,
        null,
        this,
        true
    )

    // Handler para garantir que os callbacks voltem para a Main Thread
    private val mainHandler = Handler(Looper.getMainLooper())

    override fun checkSpelling(text: String) {
        spellCheckerSession?.getSentenceSuggestions(arrayOf(TextInfo(text)), 5)
    }

    override fun close() {
        spellCheckerSession?.close()
    }

    override fun onGetSentenceSuggestions(results: Array<out SentenceSuggestionsInfo?>?) {
        val errorsWithSuggestions = mutableListOf<SpellCheckResult>()
        val errorsWithoutSuggestions = mutableListOf<SpellCheckResultWithoutSuggestion>()
        results?.forEach { sentenceInfo ->
            sentenceInfo?.let { info ->
                for (i in 0 until info.suggestionsCount) {
                    val suggestionsInfo = info.getSuggestionsInfoAt(i)
                    val flags = suggestionsInfo.suggestionsAttributes

                    // 2. FILTRO IMPORTANTE: Verifica se a API realmente identificou como erro
                    val isTypo = (flags and SuggestionsInfo.RESULT_ATTR_LOOKS_LIKE_TYPO) != 0 ||
                        (flags and SuggestionsInfo.RESULT_ATTR_HAS_RECOMMENDED_SUGGESTIONS) != 0

                    if (isTypo) {
                        if (suggestionsInfo.suggestionsCount > 0) {
                            val suggestions = (0 until suggestionsInfo.suggestionsCount).mapNotNull { index ->
                                suggestionsInfo.getSuggestionAt(index)
                            }
                            errorsWithSuggestions.add(SpellCheckResult(info.getOffsetAt(i), suggestions))
                        } else {
                            val offset = info.getOffsetAt(i)
                            val length = info.getLengthAt(i)
                            errorsWithoutSuggestions.add(SpellCheckResultWithoutSuggestion(offset, length))
                        }
                    }
                }
            }
        }

        mainHandler.post {
            onResultWithSuggestion(errorsWithSuggestions)
            onResultWithoutSuggestion(errorsWithoutSuggestions)
        }
    }

    override fun onGetSuggestions(suggestionsInfos: Array<out SuggestionsInfo?>?) {
        val errorsWithSuggestions = mutableListOf<SpellCheckResult>()
        suggestionsInfos?.forEach { suggestionsInfo ->
            suggestionsInfo?.let { info ->

                val isType = (info.suggestionsAttributes and SuggestionsInfo.RESULT_ATTR_LOOKS_LIKE_TYPO) != 0

                if (isType) {
                    val suggestions = (0 until info.suggestionsCount).mapNotNull { index ->
                        info.getSuggestionAt(index)
                    }
                    errorsWithSuggestions.add(SpellCheckResult(-1, suggestions))
                }
            }
        }

        mainHandler.post {
            onResultWithSuggestion(errorsWithSuggestions)
        }
    }
}

class SearchViewModel(
    private val callback: SpellCheckerCallback,
    private val contextDispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    fun onSearchQueryChanged(newQuery: String) {
        _searchQuery.value = newQuery
    }

    fun performSearch(query: String) {
        // Simula uma busca, por exemplo, fazendo uma chamada de rede
        // Aqui você pode adicionar a lógica para buscar os resultados com base no query
        Timber.tag("SearchViewModel").d("Performing search for query: $query")
        /*
            TODO: Implementar uma função de busca real que usa a api de Spell Check
            https://developer.android.com/develop/ui/views/touch-and-input/spell-checker-framework
            GenAI Proofreading API
            https://developers.google.com/ml-kit/genai/proofreading/android
         */

        if (query.isNotBlank()) {
            viewModelScope.launch(contextDispatcher) {
                /*
                    withContext(Dispatchers.Main) {
                        callback.checkSpelling(query)
                    }
                 */
                callback.checkSpelling(query)
            }
        }
    }

    fun closeResources() {
        callback.close()
    }

    override fun onCleared() {
        super.onCleared()

        // Certifique-se de fechar a sessão do SpellChecker para liberar recursos
        callback.close()
        // Aqui você pode liberar recursos relacionados ao SpellChecker, se necessário
        Timber.tag("SearchViewModel").d("ViewModel cleared, releasing resources if needed.")
    }

    companion object {

        fun create(callback: SpellCheckerCallback): ViewModelProvider.Factory {
            return viewModelFactory {
                initializer {
                    SearchViewModel(callback)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@OptIn(FlowPreview::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = viewModel(
        factory = SearchViewModel.create(
            SpellCheckerImpl(
                context = LocalContext.current,
                onResultWithSuggestion = { results ->
                    Timber.tag("SpellChecker").d(
                        "Errors with suggestions: $results"
                    )
                },
                onResultWithoutSuggestion = { results ->
                    Timber.tag("SpellChecker").d(
                        "Errors without suggestions: $results"
                    )
                }
            )
        )
    )
) {
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()

    LaunchedEffect(searchQuery) {
        snapshotFlow { searchQuery }
            .debounce(200)
            .collect { query ->
                // Realiza a busca com o query atualizado
                viewModel.performSearch(query)
            }
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.closeResources()
        }
    }

    SearchContent(
        currentQuery = searchQuery,
        onQueryChange = viewModel::onSearchQueryChanged
    )
}

@Composable
fun SearchContent(currentQuery: String, onQueryChange: (String) -> Unit) {
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
            .systemBarsPadding()
            .padding(6.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Query=$currentQuery",
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.headlineMedium
        )
        TextField(
            value = currentQuery,
            onValueChange = onQueryChange,
            label = { Text("Search") },
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth()
        )
    }
}
