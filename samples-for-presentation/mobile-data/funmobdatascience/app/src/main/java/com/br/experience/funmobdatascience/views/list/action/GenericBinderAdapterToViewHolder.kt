package com.br.experience.funmobdatascience.views.list.action

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.experience.funmobdatascience.views.list.viewholder.builder.BuilderViewHolder

class GenericBinderAdapterToViewHolder<T>(
    private val action: ActionItemViewHolder<T>,
    private val createViewType: ProvideItemViewType<T>,
    private val buildViewHolder: GenericBuildViewHolder<T>
) : BinderAdapterToViewHolder<T> {
    override fun onClick(viewHolder: RecyclerView.ViewHolder, data: List<T>) {
        if (data.isNotEmpty()) {
            with(viewHolder) {
                itemView.setOnClickListener {
                    action.onInteract(data[adapterPosition])
                }
            }
        }
    }

    override fun getItemViewType(data: T?): Int  = createViewType.provide(data)

    override fun fillFieldsInViewHolder(viewHolder: RecyclerView.ViewHolder, data: T) {
        buildViewHolder.build(viewHolder, data)
    }

    override fun getViewHolder(viewType: Int, viewRoot: ViewGroup): RecyclerView.ViewHolder =
        BuilderViewHolder.build(viewType, viewRoot)
}