package com.example.feature.intnavigation.view.rc.viewholder

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidallcodelabs.R
import com.example.feature.intnavigation.view.model.JustTextDeepLinkViewType
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.helper.LayoutInflaterHelper
import com.example.feature.intnavigation.view.rc.action.MatchViewHolder
import com.google.android.material.textview.MaterialTextView

class JustTextDeeplinkViewHolder(viewGroup: ViewGroup) : RecyclerView.ViewHolder(viewGroup), MatchViewHolder {

    private val view: View by lazy {
        LayoutInflaterHelper.inflate(viewGroup, R.layout.layout_text_item_deep_link)
    }

    private val typeViewHolder = JustTextDeepLinkViewType().type

    private val tvTitle: MaterialTextView by lazy { view.findViewById(R.id.tv_title_item_deeplink) }

    fun setTitle(title: String) {
        tvTitle.text = title
    }

    override fun match(viewType: Int) = viewType == typeViewHolder

    override fun getViewHolder() = this
}