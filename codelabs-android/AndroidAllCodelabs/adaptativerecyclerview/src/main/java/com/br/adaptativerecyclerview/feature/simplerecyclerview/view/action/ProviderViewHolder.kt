package com.br.adaptativerecyclerview.feature.simplerecyclerview.view.action

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder

interface ProviderViewHolder {
    fun provide(viewRoot: ViewGroup, viewType: Int): ViewHolder
}