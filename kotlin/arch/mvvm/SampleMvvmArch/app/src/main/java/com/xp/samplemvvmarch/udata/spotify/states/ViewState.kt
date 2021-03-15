package com.xp.samplemvvmarch.udata.spotify.states

sealed class ViewState {
    object ShowLoading : ViewState()
    object HideLoading : ViewState()
}