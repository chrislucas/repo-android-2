package com.br.adaptativerecyclerview.feature.simplerecyclerview.view.action

import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.model.viewtype.ViewHolderType

interface BindLayoutViewHolder<out VH, T> {

    fun onClick(viewHolder: @UnsafeVariance VH, data: T)

    fun setLayout(viewHolder: @UnsafeVariance VH, data: T)

    fun getViewType(): ViewHolderType
}