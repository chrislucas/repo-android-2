package com.example.feature.intnavigation.view.rc.action

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.action.ViewHolderBuilder
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.model.DefaultEmptyStateViewHolder
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.model.ViewHolderType
import com.example.androidallcodelabs.R
import com.example.feature.intnavigation.view.model.JustTextViewType
import com.example.feature.intnavigation.view.rc.viewholder.JustTextDeeplinkViewHolder

class DeeplinkViewHolderBuilder : ViewHolderBuilder {

    override fun build(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == JustTextViewType.JUST_TEXT) {
            JustTextDeeplinkViewHolder(InflaterLayoutHelper.inflate(viewGroup, R.layout.layout_item_deep_link))
        } else {
            DefaultEmptyStateViewHolder(viewGroup)
        }
    }

    override fun getViewHolderType(): ViewHolderType = JustTextViewType()

}

