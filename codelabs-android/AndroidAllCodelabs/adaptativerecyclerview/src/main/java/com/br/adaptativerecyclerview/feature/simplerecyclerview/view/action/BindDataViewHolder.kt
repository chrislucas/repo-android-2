package com.br.adaptativerecyclerview.feature.simplerecyclerview.view.action

import androidx.recyclerview.widget.RecyclerView

interface BindDataViewHolder<T> {
    fun onClick(viewHolder: RecyclerView.ViewHolder, data: T)
    fun setLayout(viewHolder: RecyclerView.ViewHolder, data: T)
}