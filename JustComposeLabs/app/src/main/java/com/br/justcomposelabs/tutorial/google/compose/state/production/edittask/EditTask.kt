package com.br.justcomposelabs.tutorial.google.compose.state.production.edittask

/*
    Mutating the UI state from asynchronous calls
    https://developer.android.com/topic/architecture/ui-layer/state-production#mutating_the_ui_state_from_asynchronous_calls
 */
data class AddEditTaskUiState(
    val title: String = "",
    val description: String = "",
    val isTaskCompleted: Boolean = false,
    val isLoading: Boolean = false,
    val userMessage: String? = null,
    val isTaskSaved: Boolean = false
)
