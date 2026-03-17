package com.br.recyclerviewcomponent.view.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.br.recyclerviewcomponent.behavior.MatcherViewType
import com.br.recyclerviewcomponent.databinding.LayoutSimpleLinkViewHolderBinding
import com.br.recyclerviewcomponent.model.viewholdertype.SimpleTextViewType

class SimpleLinkViewHolder(viewRoot: ViewGroup) : ViewHolder(
    LayoutSimpleLinkViewHolderBinding.inflate(
        LayoutInflater.from(viewRoot.context),
        viewRoot,
        false
    ).root
), MatcherViewType {

    private val bind = LayoutSimpleLinkViewHolderBinding.bind(itemView)

    override fun equalsTo(viewType: Int): Boolean = SimpleTextViewType.JUST_TEXT == viewType

    val title = bind.tvTitleItemDeeplink
}