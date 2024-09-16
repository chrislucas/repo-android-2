package com.example.feature.intnavigation.view.rc.action

import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.action.BindLayoutViewHolder
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.model.viewtype.ViewHolderType
import com.example.feature.intnavigation.models.Deeplink
import com.example.feature.intnavigation.view.model.viewtype.JustTextDeepLinkViewType
import com.example.feature.intnavigation.view.rc.viewholder.JustTextDeeplinkViewTypeViewHolder
import com.example.openactivity.startActivityByDeeplink

class BindJustTextDeeplinkLayout :
    BindLayoutViewHolder<JustTextDeeplinkViewTypeViewHolder, Deeplink> {

    override fun onClick(viewHolder: JustTextDeeplinkViewTypeViewHolder, data: Deeplink) {
        viewHolder.itemView.setOnClickListener { view ->
            if (data.uri.isNotEmpty()) {
                view.context.startActivityByDeeplink(data.uri)
            } else {
                data.intent?.let {
                    view.context.startActivity(it)
                }
            }
        }
    }

    override fun setLayout(viewHolder: JustTextDeeplinkViewTypeViewHolder, data: Deeplink) {
        viewHolder.setTitle(data.description)
    }

    override fun getViewType(): ViewHolderType = JustTextDeepLinkViewType()
}