package com.br.experience.funmobdatascience.views.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.experience.funmobdatascience.R
import com.br.experience.funmobdatascience.models.Share
import com.br.experience.funmobdatascience.views.list.action.ActionItemViewHolder
import com.br.experience.funmobdatascience.views.list.action.BinderAdapterToViewHolder
import com.br.experience.funmobdatascience.views.list.viewholder.CardInvestViewHolder
import com.br.experience.funmobdatascience.views.list.viewholder.builder.BuilderViewHolder
import java.text.DecimalFormat

class InvestmentAssetBinderAdapter(private val action: ActionItemViewHolder<Share>) : BinderAdapterToViewHolder<Share> {

    private val decimalFormat = DecimalFormat("###,##0.00")

    override fun onClick(viewHolder: RecyclerView.ViewHolder, data: List<Share>) {
        if (data.isNotEmpty()) {
            with(viewHolder) {
                itemView.setOnClickListener {
                    action.onInteract(data[adapterPosition])
                }
            }
        }
    }

    override fun getItemViewType(data: Share?): Int {
        return if (data != null) {
            BuilderViewHolder.ALTERNATIVE_VIEW_HOLDER
        } else {
            BuilderViewHolder.ALTERNATIVE_VIEW_HOLDER
        }
    }

    override fun fillFieldsInViewHolder(viewHolder: RecyclerView.ViewHolder, data: Share) {
        if (viewHolder is CardInvestViewHolder) {
            with(viewHolder.binding) {
                tvLabelAssetName.text = data.name
                tvLabelProductPrice.text = root.context.getString(
                    R.string.brazilian_price_placeholder,
                    decimalFormat.format(data.close)
                )
            }
        }
    }

    override fun getViewHolder(viewType: Int, viewRoot: ViewGroup): RecyclerView.ViewHolder =
        BuilderViewHolder.build(viewType, viewRoot) { CardInvestViewHolder(viewRoot) }
}