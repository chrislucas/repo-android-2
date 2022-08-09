package com.br.experience.funmobdatascience.views.list.action

import androidx.recyclerview.widget.RecyclerView

interface GenericBuildViewHolder<T> {
    fun build(viewHolder: RecyclerView.ViewHolder, data: T)
}