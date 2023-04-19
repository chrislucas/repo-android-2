package com.br.adaptativerecyclerview.feature.simplerecyclerview.view.action

import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.model.ViewHolderType

interface BindLayoutViewHolder<out V, T> {
    val viewType: ViewHolderType

    fun onClick(viewHolder: @UnsafeVariance V, data: T)
    fun setLayout(viewHolder: @UnsafeVariance V, data: T)
}