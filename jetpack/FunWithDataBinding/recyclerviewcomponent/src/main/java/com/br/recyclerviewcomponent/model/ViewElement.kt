package com.br.recyclerviewcomponent.model

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.br.recyclerviewcomponent.behavior.BindViewHolder
import com.br.recyclerviewcomponent.behavior.ProviderViewHolderType

class ViewElement<Content, V : ViewHolder>(
    val content: Content,
    val bindViewHolder: BindViewHolder<Content, V>
): ProviderViewHolderType {
    override fun getViewType(): Int = bindViewHolder.getViewType().type
}