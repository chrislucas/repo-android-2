package com.example.feature.intnavigation.view.rc.action

import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.action.BindLayoutViewHolder
import com.br.adaptativerecyclerview.feature.simplerecyclerview.view.model.viewtype.ViewHolderType
import com.example.feature.intnavigation.models.Deeplink
import com.example.feature.intnavigation.view.model.viewtype.ImageAndTextDeeplinkViewType
import com.example.feature.intnavigation.view.rc.viewholder.ImageAndTextDeeplinkViewTypeViewHolder
import com.example.openactivity.startActivityByDeeplink

class BindDeeplinkImageAndTextLayoutViewHolder : BindLayoutViewHolder<ImageAndTextDeeplinkViewTypeViewHolder, Deeplink> {

    override fun onClick(viewHolder: ImageAndTextDeeplinkViewTypeViewHolder, data: Deeplink) {
        viewHolder.itemView.setOnClickListener {
            it.context.startActivityByDeeplink(data.uri)
        }
    }

    override fun setLayout(viewHolder: ImageAndTextDeeplinkViewTypeViewHolder, data: Deeplink) {
        viewHolder.setTitle(data.description)
    }

    override fun getViewType(): ViewHolderType = ImageAndTextDeeplinkViewType()
}