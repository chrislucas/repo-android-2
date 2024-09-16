package com.br.recyclerviewcomponent.behavior.binder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.br.recyclerviewcomponent.behavior.BindViewHolder
import com.br.recyclerviewcomponent.model.viewholdertype.CustomViewType
import com.br.recyclerviewcomponent.model.viewholdertype.ViewHolderType

class BinderCustomViewHolder<Content, V : ViewHolder>(
    private val actionOnClick: (Content) -> Unit,
    private val setLayout: (Content, V) -> Unit
) : BindViewHolder<Content, V> {

    override fun onClick(content: Content, viewHolder: V) {
        viewHolder.itemView.setOnClickListener {
            actionOnClick.invoke(content)
        }
    }

    override fun setLayout(content: Content, viewHolder: V) {
        setLayout.invoke(content, viewHolder)
    }

    override fun getViewType(): ViewHolderType = CustomViewType
}