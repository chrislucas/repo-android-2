package com.example.feature.intnavigation.view.rc.action

import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.action.BindLayoutViewHolder
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.model.ViewHolderType
import com.example.feature.intnavigation.models.Deeplink
import com.example.feature.intnavigation.view.rc.viewholder.JustTextDeeplinkViewHolder
import com.example.openactivity.startActivityByDeeplink

class BindDeepLinkJustTextLayoutViewHolder(override val viewType: ViewHolderType) :
    BindLayoutViewHolder<JustTextDeeplinkViewHolder, Deeplink> {

    override fun onClick(viewHolder: JustTextDeeplinkViewHolder, data: Deeplink) {
        viewHolder.itemView.setOnClickListener {
            it.context.startActivityByDeeplink(data.uri)
        }
    }

    override fun setLayout(viewHolder: JustTextDeeplinkViewHolder, data: Deeplink) {
        viewHolder.setTitle(data.description)
    }
}