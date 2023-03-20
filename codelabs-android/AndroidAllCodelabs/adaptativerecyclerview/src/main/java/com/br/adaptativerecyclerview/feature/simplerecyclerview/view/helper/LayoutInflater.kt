package com.br.adaptativerecyclerview.feature.simplerecyclerview.view.helper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

object LayoutInflaterHelper {

    fun inflate(viewGroup: ViewGroup, @LayoutRes layout: Int): View =
        LayoutInflater.from(viewGroup.context).inflate(layout, viewGroup, false)
}