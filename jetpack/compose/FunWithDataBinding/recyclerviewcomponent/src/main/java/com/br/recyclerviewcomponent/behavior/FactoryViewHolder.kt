package com.br.recyclerviewcomponent.behavior

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder

interface FactoryViewHolder {
    fun getViewHolder(viewRoot: ViewGroup, viewType: Int): ViewHolder
}