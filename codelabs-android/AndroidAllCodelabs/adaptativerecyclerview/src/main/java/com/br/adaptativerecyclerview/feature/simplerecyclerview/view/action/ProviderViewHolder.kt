package com.br.adaptativerecyclerview.feature.simplerecyclerview.view.action

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder

/**
 * Given a viewType then construct a ViewHolder with viewRoot:ViewGroup as View Root
 */
interface ProviderViewHolder {
    fun provide(viewRoot: ViewGroup, viewType: Int): ViewHolder
}