package com.br.recyclerviewcomponent.view.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.br.recyclerviewcomponent.behavior.MatcherViewType
import com.br.recyclerviewcomponent.databinding.LayoutIconTextViewHolderBinding
import com.br.recyclerviewcomponent.model.viewholdertype.IconTextViewType

class IconTextViewHolder(viewRoot: ViewGroup):  ViewHolder(
    LayoutIconTextViewHolderBinding.inflate(
        LayoutInflater.from(viewRoot.context),
        viewRoot,
        false
    ).root
), MatcherViewType {

    private val bind = LayoutIconTextViewHolderBinding.bind(itemView)

    val icon = bind.ivItem

    val description = bind.tvTitle

    override fun equalsTo(viewType: Int): Boolean = IconTextViewType.ICON_TEXT == viewType


}