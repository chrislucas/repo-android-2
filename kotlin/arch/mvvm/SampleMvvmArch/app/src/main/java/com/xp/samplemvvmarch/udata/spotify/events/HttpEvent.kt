package com.xp.samplemvvmarch.feature2.events

sealed class HttpEvent {
    object requesting : HttpEvent()
}