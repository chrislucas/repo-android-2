package com.br.funwithjetpackcompose.tutorials.medium.makeanyclasslifecycleaware

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import java.util.Date

/*
    https://buraaktasci.medium.com/making-any-class-lifecycle-aware-in-android-a-practical-guide-db9d1ce68a93
 */
interface DefaultLifecycleObserver: LifecycleObserver {
    fun onCreate(owner: LifecycleOwner) {}

    fun onStart(owner: LifecycleOwner) {}

    fun onResume(owner: LifecycleOwner) {}

    fun onPause(owner: LifecycleOwner) {}

    fun onStop(owner: LifecycleOwner) {}

    fun onDestroy(owner: LifecycleOwner) {}
}

class LifecycleAwareLogger private constructor(): DefaultLifecycleObserver {

    private var tag: String = ""
    private var isLifecycleLoggingEnabled: Boolean = false

    fun info(message: String) {
        Log.d(tag, message)
    }

    fun warning(message: String) {
        Log.w(tag, message)
    }

    fun error(message: String) {
        Log.e(tag, message)
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        if (isLifecycleLoggingEnabled) {
            info("onResume ${Date()}")
        }
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        if (isLifecycleLoggingEnabled) {
            info("onStop ${Date()}")
        }
    }

    fun isEnableLifecycleLogging() = isLifecycleLoggingEnabled

    fun enableLifecycleLogging() {
        isLifecycleLoggingEnabled = true
    }

    fun disableLifecycleLogging() {
        isLifecycleLoggingEnabled = false
    }

    companion object {
        fun create(tag: String): LifecycleAwareLogger =
            LifecycleAwareLogger().apply { this.tag = tag }
    }
}