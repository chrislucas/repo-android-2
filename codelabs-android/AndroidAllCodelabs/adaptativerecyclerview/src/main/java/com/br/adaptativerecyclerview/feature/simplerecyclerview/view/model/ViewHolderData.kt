package com.br.adaptativerecyclerview.feature.simplerecyclerview.view.model

import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.action.BindLayoutViewHolder
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.action.ProviderViewHolderType

class ViewHolderData<V, T>(
    val item: T,
    val binderView: BindLayoutViewHolder<V, T>
) : ProviderViewHolderType {
    override fun viewTypeForViewHolder(): Int = binderView.viewType.type
}
