package com.br.recyclerviewcomponent.behavior

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.br.recyclerviewcomponent.view.viewholder.DefaultEmptyViewHolder
import com.br.recyclerviewcomponent.view.viewholder.IconTextViewHolder
import com.br.recyclerviewcomponent.view.viewholder.SimpleLinkViewHolder

class FactoryViewHolderDefault(
    emptyStateViewHolder: ViewHolder? = null,
    private val matcherViewTypes: MutableList<MatcherViewType> = mutableListOf()
) : TemplateFactoryViewHolder(emptyStateViewHolder) {

    override fun findViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return listOf(
            SimpleLinkViewHolder(viewGroup),
            IconTextViewHolder(viewGroup),
            DefaultEmptyViewHolder(viewGroup)
        ).plus(matcherViewTypes).find { it.equalsTo(viewType) } as ViewHolder
    }
}
