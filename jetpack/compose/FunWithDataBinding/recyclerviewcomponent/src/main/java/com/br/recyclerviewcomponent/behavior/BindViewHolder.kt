package com.br.recyclerviewcomponent.behavior

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.br.recyclerviewcomponent.model.viewholdertype.ViewHolderType

interface BindViewHolder<Content, out ViewHolder> {
    fun onClick(content: Content, viewHolder: @UnsafeVariance ViewHolder)
    fun setLayout(content: Content, viewHolder: @UnsafeVariance ViewHolder)
    fun getViewType(): ViewHolderType
}