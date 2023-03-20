package com.example.feature.intnavigation.view.rc.action

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

object InflaterLayoutHelper {

    fun inflate(viewGroup: ViewGroup, @LayoutRes layout: Int): View =
        LayoutInflater.from(viewGroup.context).inflate(layout, viewGroup, false)
}