package com.br.mviarchtecturepatternii.tutorials.simplemviwithflow

import androidx.lifecycle.ViewModel

/*
    mvi with channel android
 */
sealed class UiState {
    object Loading : UiState()
    data class Success(val data: String) : UiState()
}
sealed class UiEffect {
    data class ShowToast(val message: String) : UiEffect()
}


class StateScreenViewModel: ViewModel() {
}