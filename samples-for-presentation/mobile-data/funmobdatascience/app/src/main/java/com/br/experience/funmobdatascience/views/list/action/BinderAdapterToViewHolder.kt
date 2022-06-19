package com.br.experience.funmobdatascience.views.list.action

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface BinderAdapterToViewHolder<T> {
    fun onClick(viewHolder: RecyclerView.ViewHolder, data: List<T>)
    fun getItemViewType(data : T?) : Int
    fun fillFieldsInViewHolder(viewHolder: RecyclerView.ViewHolder, data: T)
    fun getViewHolder(viewType: Int, viewRoot: ViewGroup) : RecyclerView.ViewHolder
}