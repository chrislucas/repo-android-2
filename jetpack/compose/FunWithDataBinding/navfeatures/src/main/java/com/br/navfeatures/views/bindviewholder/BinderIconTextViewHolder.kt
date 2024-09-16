package com.br.navfeatures.views.bindviewholder

import com.br.navfeatures.model.Feature
import com.br.recyclerviewcomponent.behavior.BindViewHolder
import com.br.recyclerviewcomponent.model.viewholdertype.IconTextViewType
import com.br.recyclerviewcomponent.model.viewholdertype.ViewHolderType
import com.br.recyclerviewcomponent.view.viewholder.IconTextViewHolder


class BinderIconTextViewHolder: BindViewHolder<Feature, IconTextViewHolder> {
    override fun onClick(content: Feature, viewHolder: IconTextViewHolder) {
        viewHolder.itemView.setOnClickListener {
            content.fireIntention(it.context)
        }
    }

    override fun setLayout(content: Feature, viewHolder: IconTextViewHolder) {
        viewHolder.description.text = content.description
    }

    override fun getViewType(): ViewHolderType = IconTextViewType
}