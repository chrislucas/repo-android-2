package com.xp.samplemvvmarch.udata.spotify.events

sealed class HttpEvent {
    object requesting : HttpEvent()
}