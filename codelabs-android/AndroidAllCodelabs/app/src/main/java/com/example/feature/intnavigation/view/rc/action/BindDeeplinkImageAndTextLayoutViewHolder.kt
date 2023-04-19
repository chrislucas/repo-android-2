package com.example.feature.intnavigation.view.rc.action

import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.action.BindLayoutViewHolder
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.model.ViewHolderType
import com.example.feature.intnavigation.models.Deeplink
import com.example.feature.intnavigation.view.rc.viewholder.ImageAndTextDeeplinkViewHolder
import com.example.openactivity.startActivityByDeeplink

class BindDeeplinkImageAndTextLayoutViewHolder(override val viewType: ViewHolderType) : BindLayoutViewHolder<ImageAndTextDeeplinkViewHolder, Deeplink> {

    override fun onClick(viewHolder: ImageAndTextDeeplinkViewHolder, data: Deeplink) {
        viewHolder.itemView.setOnClickListener {
            it.context.startActivityByDeeplink(data.uri)
        }
    }

    override fun setLayout(viewHolder: ImageAndTextDeeplinkViewHolder, data: Deeplink) {
        viewHolder.setTitle(data.description)
    }
}