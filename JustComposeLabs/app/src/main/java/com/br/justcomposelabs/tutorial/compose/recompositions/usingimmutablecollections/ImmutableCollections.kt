package com.br.justcomposelabs.tutorial.compose.recompositions.usingimmutablecollections

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@Immutable
data class ItemUi(
    val id: String,
    val title: String
)

@Immutable
data class UiState(
    val items: PersistentList<ItemUi> = persistentListOf(),
    val isLoading: Boolean = false
)

class MyViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        loadItems()
    }

    private fun loadItems() {
        val apiItems = listOf(
            ItemUi("1", "Compose"),
            ItemUi("2", "PersistentList")
        )

        _uiState.update { current ->
            current.copy(
                items = apiItems.toPersistentList(),
                isLoading = false
            )
        }
    }

    fun addItem(item: ItemUi) {
        _uiState.update { current ->
            current.copy(items = current.items.add(item))
        }
    }

    fun removeItem(id: String) {
        _uiState.update { current ->
            current.copy(items = current.items.removeAll { it.id == id })
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PersistScreen(viewModel: MyViewModel = viewModel()) {
    val uiState: UiState by viewModel.uiState.collectAsState()
    PersistItems(uiState)
}

@Composable
private fun PersistItems(state: UiState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .systemBarsPadding()
    ) {
        if (state.isLoading) {
            Text("Carregando...")
        } else {
            LazyColumn {
                items(
                    state.items,
                    { item -> item.hashCode().toLong() }
                ) { item ->
                    Text(item.title)
                }
            }
        }

        AddItem()
    }
}

/*
    Adicionar na lista da view model um Item mockado cujos
    dados são um conteúdo aleatório gerado
 */
@Composable
private fun AddItem() {
}
