package com.br.funwithdatabinding.view.features.allfeatures.view.bindviewholder

import com.br.funwithdatabinding.view.features.allfeatures.model.Feature
import com.br.recyclerviewcomponent.behavior.BindViewHolder
import com.br.recyclerviewcomponent.model.viewholdertype.SimpleTextViewType
import com.br.recyclerviewcomponent.model.viewholdertype.ViewHolderType
import com.br.recyclerviewcomponent.view.viewholder.SimpleLinkViewHolder

class BinderSimpleLinkViewHolder: BindViewHolder<Feature, SimpleLinkViewHolder> {

    override fun onClick(content: Feature, viewHolder: SimpleLinkViewHolder) {
        viewHolder.itemView.setOnClickListener {
            content.fireIntention(it.context)
        }
    }

    override fun setLayout(content: Feature, viewHolder: SimpleLinkViewHolder) {
        viewHolder.title.text = content.description
    }

    override fun getViewType(): ViewHolderType = SimpleTextViewType
}