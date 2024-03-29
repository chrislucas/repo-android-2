package com.br.experience.funmobdatascience.features.shares.views.list.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.experience.funmobdatascience.databinding.LayoutItemInvestmentAssetBinding

class CardAssetViewHolder(private val root: ViewGroup) : RecyclerView.ViewHolder(root) {

    val binding: LayoutItemInvestmentAssetBinding by lazy {
        LayoutItemInvestmentAssetBinding.inflate(
            LayoutInflater.from(root.context),
            root,
            false
        )
    }
}