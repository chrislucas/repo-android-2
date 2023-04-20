package com.example.feature.intnavigation.view.rc.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.feature.intnavigation.view.model.viewtype.JustTextDeepLinkViewType
import com.example.androidallcodelabs.databinding.LayoutTextItemDeepLinkBinding
import com.example.feature.intnavigation.view.rc.action.MatchViewTypeViewHolder

class JustTextDeeplinkViewTypeViewHolder(viewGroup: ViewGroup) : RecyclerView.ViewHolder(
    LayoutTextItemDeepLinkBinding.inflate(
        LayoutInflater.from(viewGroup.context), viewGroup, false
    ).root
), MatchViewTypeViewHolder {

    private val bind: LayoutTextItemDeepLinkBinding = LayoutTextItemDeepLinkBinding.bind(itemView)

    fun setTitle(title: String) {
        bind.tvTitleItemDeeplink.text = title
    }

    override fun match(viewType: Int) = viewType == JustTextDeepLinkViewType().type

}