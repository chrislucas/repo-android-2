package com.br.adaptativerecyclerview.feature.simplerecyclerview.view.action

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.DefaultEmptyStateViewHolder

/**
 * Uma immplementacao de provider de viewHolder que nos retorna um ViewHolder que representa um Estado Vazio
 * caso o view type for de "EMPTY_STATE"
 */
abstract class TemplateProviderViewHolder(
    private val emptyStateViewHolder: RecyclerView.ViewHolder? = null
) : ProviderViewHolder {

    abstract fun find(viewRoot: ViewGroup, viewType: Int): RecyclerView.ViewHolder?

    override fun provide(viewRoot: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return find(viewRoot, viewType) ?: (emptyStateViewHolder ?: DefaultEmptyStateViewHolder(viewRoot))
    }
}




