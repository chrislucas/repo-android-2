package com.br.experience.funmobdatascience.views.list.viewholder.builder

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.br.experience.funmobdatascience.databinding.LayoutEmptyStateViewHolderBinding

class EmptyStateViewHolder(viewRoot: View) : RecyclerView.ViewHolder(viewRoot) {
    val binding: LayoutEmptyStateViewHolderBinding by lazy {
        LayoutEmptyStateViewHolderBinding.inflate(LayoutInflater.from(viewRoot.context))
    }
}