package com.br.justcomposelabs.tutorial.google.coroutines.ai.stateinexample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn


sealed class UIState {
    object Loading : UIState()
    class Success<T>(val data: T) : UIState()
    object Error : UIState()
}

interface FakeRepository {
    suspend fun fetch(): List<String>
}


class StateInViewModel(val repository: FakeRepository) : ViewModel() {
    // 1. Create a trigger
    private val refreshTrigger = MutableSharedFlow<Unit>(replay = 1)

    // 2 & 3. Chain flows and use stateIn

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<UIState> = refreshTrigger
        /*
            flatMapLatest
            https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/flat-map-latest.html
         */
        .flatMapLatest {
            flowOf(UIState.Success(repository.fetch()))
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UIState.Loading
        )

    // 4. Expose a public function to trigger the reload
    fun refreshData() {
        refreshTrigger.tryEmit(Unit)
    }

    init {
        // Trigger initial data load
        refreshData()
    }
}
