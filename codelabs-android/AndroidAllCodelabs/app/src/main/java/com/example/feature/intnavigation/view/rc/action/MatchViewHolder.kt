package com.example.feature.intnavigation.view.rc.action

import androidx.recyclerview.widget.RecyclerView.ViewHolder

interface MatchViewHolder {
    fun match(viewType: Int): Boolean

    fun getViewHolder(): ViewHolder
}