package com.br.adaptativerecyclerview.feature.simplerecyclerview.view.model

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.action.BindLayoutViewHolder
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.action.ProviderViewHolderType

/**
 * An Item must provide which ViewType should be used to show its values
 */
class ViewHolderItem<V : ViewHolder, T>(
    val item: T,
    val binderView: BindLayoutViewHolder<V, T>
) : ProviderViewHolderType {
    override fun viewTypeForViewHolder(): Int = binderView.getViewType().type
}
