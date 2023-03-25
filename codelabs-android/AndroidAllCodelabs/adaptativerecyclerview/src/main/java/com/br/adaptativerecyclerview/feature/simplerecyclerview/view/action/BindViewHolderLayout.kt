package com.br.adaptativerecyclerview.feature.simplerecyclerview.view.action


import androidx.recyclerview.widget.RecyclerView.ViewHolder

interface BindViewHolderLayout<V: ViewHolder, T> {
    fun onClick(viewHolder:  V, data: T)
    fun setLayout(viewHolder:  V, data: T)
}