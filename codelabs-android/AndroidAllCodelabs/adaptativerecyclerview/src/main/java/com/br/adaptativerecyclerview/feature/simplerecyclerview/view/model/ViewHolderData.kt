package com.br.adaptativerecyclerview.feature.simplerecyclerview.view.model

import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.action.BindDataViewHolder
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.action.ProviderViewHolderType

class ViewHolderData<T>(
    val item: T,
    val bindDataViewHolder: BindDataViewHolder<T>,
    val viewHolderType: ViewHolderType
) : ProviderViewHolderType {
    override fun viewTypeForViewHolder(): Int = viewHolderType.type
}
