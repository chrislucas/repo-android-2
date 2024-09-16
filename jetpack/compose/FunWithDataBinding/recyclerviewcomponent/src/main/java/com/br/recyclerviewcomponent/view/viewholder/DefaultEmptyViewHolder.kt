package com.br.recyclerviewcomponent.view.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.br.recyclerviewcomponent.behavior.MatcherViewType
import com.br.recyclerviewcomponent.databinding.LayoutDefaultEmptyViewHolderBinding
import com.br.recyclerviewcomponent.model.viewholdertype.EmptyStateViewType

class DefaultEmptyViewHolder(viewRoot: ViewGroup) : ViewHolder(
    LayoutDefaultEmptyViewHolderBinding.inflate(
        LayoutInflater.from(viewRoot.context),
        viewRoot,
        false
    ).root
), MatcherViewType {
    override fun equalsTo(viewType: Int): Boolean =
        EmptyStateViewType.EMPTY_STATE_VIEW_TYPE == viewType

}