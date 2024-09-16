package com.br.recyclerviewcomponent.behavior

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.br.recyclerviewcomponent.view.viewholder.DefaultEmptyViewHolder

abstract class TemplateFactoryViewHolder(
    private val emptyStateViewHolder: ViewHolder? = null
) : FactoryViewHolder {

    abstract fun findViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder?

     override fun getViewHolder(viewRoot: ViewGroup, viewType: Int): ViewHolder =
        findViewHolder(viewRoot, viewType) ?: (emptyStateViewHolder ?: DefaultEmptyViewHolder(viewRoot))
}