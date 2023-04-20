package com.example.feature.intnavigation.view.rc.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.feature.intnavigation.view.model.viewtype.ImageAndTextDeeplinkViewType
import com.example.androidallcodelabs.databinding.LayoutImageAndTextItemDeepLinkBinding
import com.example.feature.intnavigation.view.rc.action.MatchViewTypeViewHolder

class ImageAndTextDeeplinkViewTypeViewHolder(viewGroup: ViewGroup) : ViewHolder(
    LayoutImageAndTextItemDeepLinkBinding.inflate(
        LayoutInflater.from(viewGroup.context), viewGroup, false
    ).root
), MatchViewTypeViewHolder {

    private val bind: LayoutImageAndTextItemDeepLinkBinding = LayoutImageAndTextItemDeepLinkBinding.bind(itemView)

    fun setTitle(title: String) {
        bind.tvTitleItemDeeplink1.text = title
    }

    override fun match(viewType: Int) = viewType == ImageAndTextDeeplinkViewType().type
}