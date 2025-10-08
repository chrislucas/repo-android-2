package com.br.justcomposelabs.tutorial.google.lifecycleaware

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

/*
    https://developer.android.com/topic/libraries/architecture/lifecycle
 */

enum class CallbackLifecycleObserver {
    ON_CREATE,
    ON_START,
    ON_RESUME,
    ON_PAUSE,
    ON_STOP,
    ON_DESTROY,
}

class CustomLifecycleObserver(
    private val callback: (CallbackLifecycleObserver, LifecycleOwner) -> Unit
) : DefaultLifecycleObserver {

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        callback(CallbackLifecycleObserver.ON_CREATE, owner)
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        callback(CallbackLifecycleObserver.ON_START, owner)
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        callback(CallbackLifecycleObserver.ON_RESUME, owner)
    }


    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        callback(CallbackLifecycleObserver.ON_STOP, owner)
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        callback(CallbackLifecycleObserver.ON_PAUSE, owner)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        callback(CallbackLifecycleObserver.ON_DESTROY, owner)
    }
}