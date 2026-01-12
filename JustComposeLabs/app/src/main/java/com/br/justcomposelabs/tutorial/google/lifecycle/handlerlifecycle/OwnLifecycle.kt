package com.br.justcomposelabs.tutorial.google.lifecycle.handlerlifecycle

import android.content.Context
import android.location.Location
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

/*
    https://developer.android.com/topic/libraries/architecture/lifecycle#lc

    -

    https://developer.android.com/reference/androidx/lifecycle/DefaultLifecycleObserver

    Classic Android Lifecycle vs AndroidX Lifecycle (KTX) — Under the Hood
    https://proandroiddev.com/classic-android-lifecycle-vs-androidx-lifecycle-ktx-under-the-hood-f2030bada86e

    Lifecycle observer in Android
    https://medium.com/@aritrodam773/lifecycle-observer-in-android-6e9d16ed49bc
 */
class MyLocationListener(
    private val context: Context,
    private val lifecycle: Lifecycle,
    private val callback: (Location) -> Unit
): DefaultLifecycleObserver {

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
    }
}