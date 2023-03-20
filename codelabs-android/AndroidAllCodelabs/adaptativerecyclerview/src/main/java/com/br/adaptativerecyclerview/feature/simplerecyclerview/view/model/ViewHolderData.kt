package com.br.adaptativerecyclerview.feature.simplerecyclerview.view.model

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.action.BindViewHolderLayout
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.action.ProviderViewHolderType

class ViewHolderData<T>(
    val item: T,
    val bindViewHolderLayout: BindViewHolderLayout<out ViewHolder, T>,
    private val viewHolderType: ViewHolderType
) : ProviderViewHolderType {
    override fun viewTypeForViewHolder(): Int = viewHolderType.type
}
