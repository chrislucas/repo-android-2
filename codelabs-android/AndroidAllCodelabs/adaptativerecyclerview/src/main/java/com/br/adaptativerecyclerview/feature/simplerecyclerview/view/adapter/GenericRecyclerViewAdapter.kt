package com.br.adaptativerecyclerview.feature.simplerecyclerview.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.action.ProviderViewHolder
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.model.EmptyStateType.Companion.EMPTY_STATE
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.model.ViewHolderData

class GenericRecyclerViewAdapter<T>(
    private val data: List<ViewHolderData<T>>,
    private val providerViewHolder: ProviderViewHolder
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return providerViewHolder.provide(parent, viewType)
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        if (holder.adapterPosition >= 0 && data.isNotEmpty()) {
            data[holder.adapterPosition].run {
                bindViewHolderLayout.onClick(holder, this.item)
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

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (data.isNotEmpty()) {
            data[position].run {
                bindViewHolderLayout.setLayout(holder, this.item)
            }
        }
    }
}