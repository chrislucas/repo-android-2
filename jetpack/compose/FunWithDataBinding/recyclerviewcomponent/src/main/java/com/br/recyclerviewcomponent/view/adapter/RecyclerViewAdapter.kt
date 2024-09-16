package com.br.recyclerviewcomponent.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.br.recyclerviewcomponent.behavior.FactoryViewHolder
import com.br.recyclerviewcomponent.model.ViewElement
import com.br.recyclerviewcomponent.model.viewholdertype.EmptyStateViewType

class RecyclerViewAdapter<T>(
    private val elements: List<ViewElement<T, ViewHolder>>,
    private val factoryViewHolder: FactoryViewHolder
): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        factoryViewHolder.getViewHolder(parent, viewType)

    override fun getItemCount(): Int = if (elements.isEmpty()) {
        1
    } else {
        elements.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (elements.isEmpty()) {
            EmptyStateViewType.EMPTY_STATE_VIEW_TYPE
        } else {
            elements[position].getViewType()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (elements.isNotEmpty()) {
            elements[position].run {
                bindViewHolder.setLayout(content, holder)
            }
        }
    }
}