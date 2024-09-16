package com.br.funwithdatabinding.view.features.utils

import android.os.SystemClock
import android.view.View
import android.view.View.OnClickListener

class SafeClickListener(
    private val interval: Long = 500,
    private val safeClickListener: (View?) -> Unit
) : OnClickListener {

    private var latestEventClick = 0L

    override fun onClick(v: View?) {
        val time = SystemClock.elapsedRealtime()
        if (time - latestEventClick >= interval) {
            latestEventClick = time
            safeClickListener(v)
        }
    }
}


fun View.setOnSafeClickListener(
    interval: Long = 500,
    safeClickListener: (View?) -> Unit
) {
    setOnClickListener(SafeClickListener(interval, safeClickListener))
}