package com.xp.samplemvvmarch.feature2.states

sealed class ViewState {
    object ShowLoading : ViewState()
    object HideLoading : ViewState()
}