package com.br.adaptativerecyclerview.feature.simplerecyclerview.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.adaptativerecyclerview.R

class DefaultEmptyStateViewHolder(viewGroup: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(viewGroup.context).inflate(R.layout.layout_item_empty_state, viewGroup, false)
)