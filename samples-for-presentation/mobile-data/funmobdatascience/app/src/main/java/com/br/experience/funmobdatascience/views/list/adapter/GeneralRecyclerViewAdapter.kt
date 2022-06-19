package com.br.experience.funmobdatascience.views.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.experience.funmobdatascience.views.list.action.BinderAdapterToViewHolder
import com.br.experience.funmobdatascience.views.list.viewholder.builder.BuilderViewHolder

class GeneralRecyclerViewAdapter<T>(private val collection: MutableList<T>, private val binder: BinderAdapterToViewHolder<T>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewRoot = binder.getViewHolder(viewType, parent)
        binder.onClick(viewRoot, collection)
        return viewRoot
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (collection.isNotEmpty()) {
            binder.fillFieldsInViewHolder(holder, collection[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (collection.isNotEmpty()) {
            binder.getItemViewType(collection[position])
        } else {
            BuilderViewHolder.VIEW_HOLDER_EMPTY_STATE
        }
    }

    override fun getItemCount(): Int = if (collection.isEmpty()) {
        1
    } else {
        collection.size
    }

    fun updateCollection(data: MutableList<T>) {
        collection.clear()
        collection.addAll(data)
        notifyDataSetChanged()
    }
}