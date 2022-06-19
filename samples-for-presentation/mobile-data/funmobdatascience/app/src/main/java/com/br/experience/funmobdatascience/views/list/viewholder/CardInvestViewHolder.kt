package com.br.experience.funmobdatascience.views.list.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.experience.funmobdatascience.databinding.LayoutItemInvAssetBinding
import com.google.android.material.textview.MaterialTextView

class CardInvestViewHolder(private val root: ViewGroup) : RecyclerView.ViewHolder(root) {

    val binding: LayoutItemInvAssetBinding by lazy {
        LayoutItemInvAssetBinding.inflate(
            LayoutInflater.from(root.context),
            root,
            false
        )
    }


}