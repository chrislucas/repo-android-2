package com.br.adaptativerecyclerview.feature.simplerecyclerview.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.action.ProviderViewHolder
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.model.EmptyStateType.Companion.EMPTY_STATE
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.model.ViewHolderData

class GenericRecyclerViewAdapter<T>(
    private val data: List<ViewHolderData<ViewHolder, T>>,
    private val providerViewHolder: ProviderViewHolder
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (data.isNotEmpty()) {
            data[position].run {
                binderView.setLayout(holder, item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return providerViewHolder.provide(parent, viewType)
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        if (holder.adapterPosition >= 0 && data.isNotEmpty()) {
            data[holder.adapterPosition].run {
                binderView.onClick(holder, this.item)
            }
        }
        super.onViewAttachedToWindow(holder)
    }

    override fun getItemViewType(position: Int): Int {
        return if (data.isNotEmpty()) {
            data[position].viewTypeForViewHolder()
        } else {
            EMPTY_STATE
        }
    }

    override fun getItemCount(): Int = if (data.isEmpty()) 1 else data.size
}