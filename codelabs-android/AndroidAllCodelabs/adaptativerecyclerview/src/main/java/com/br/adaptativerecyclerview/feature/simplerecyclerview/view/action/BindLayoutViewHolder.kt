package com.br.adaptativerecyclerview.feature.simplerecyclerview.view.action

import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.model.viewtype.ViewHolderType

interface BindLayoutViewHolder<out V, T> {

    fun onClick(viewHolder: @UnsafeVariance V, data: T)
    fun setLayout(viewHolder: @UnsafeVariance V, data: T)

    fun getViewType(): ViewHolderType
}