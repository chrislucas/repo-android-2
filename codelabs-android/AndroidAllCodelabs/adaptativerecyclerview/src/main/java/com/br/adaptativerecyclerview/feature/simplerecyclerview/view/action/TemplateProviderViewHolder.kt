package com.br.adaptativerecyclerview.feature.simplerecyclerview.view.action

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.model.DefaultEmptyStateViewHolder
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.model.ViewHolderType

class TemplateProviderViewHolder(private val viewHolderBuilder: ViewHolderBuilder,
    private val customEmptyStateViewHolder: RecyclerView.ViewHolder? = null) : ProviderViewHolder {

    override fun provide(viewRoot: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == ViewHolderType.EMPTY_STATE) {
            customEmptyStateViewHolder ?: DefaultEmptyStateViewHolder(viewRoot)
        } else {
            viewHolderBuilder.build(viewRoot, viewType)
        }
}
