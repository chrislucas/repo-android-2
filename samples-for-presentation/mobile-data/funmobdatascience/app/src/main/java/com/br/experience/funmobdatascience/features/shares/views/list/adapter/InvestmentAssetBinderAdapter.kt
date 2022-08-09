package com.br.experience.funmobdatascience.features.shares.views.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.experience.funmobdatascience.R
import com.br.experience.funmobdatascience.features.portfolio.models.PortfolioAsset
import com.br.experience.funmobdatascience.features.shares.models.Share
import com.br.experience.funmobdatascience.features.shares.views.list.viewholder.CardAssetViewHolder
import com.br.experience.funmobdatascience.views.list.action.ActionItemViewHolder
import com.br.experience.funmobdatascience.views.list.action.BinderAdapterToViewHolder
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
        if (viewHolder is CardAssetViewHolder) {
            with(viewHolder.binding.layoutItemInvestment) {
                tvLabelAbbreviationNameAsset.text = data.name
                tvLabelProductPrice.text = root.context.getString(
                    R.string.brazilian_price_placeholder,
                    decimalFormat.format(data.close)
                )
            }
        }
    }

    override fun getViewHolder(viewType: Int, viewRoot: ViewGroup): RecyclerView.ViewHolder =
        BuilderViewHolder.build(viewType, viewRoot) { CardAssetViewHolder(viewRoot) }
}

class PortfolioBinderAdapter(private val action: ActionItemViewHolder<PortfolioAsset>) : BinderAdapterToViewHolder<PortfolioAsset> {

    private val decimalFormat = DecimalFormat("###,##0.00")

    override fun onClick(viewHolder: RecyclerView.ViewHolder, data: List<PortfolioAsset>) {
        if (data.isNotEmpty()) {
            with(viewHolder) {
                itemView.setOnClickListener {
                    action.onInteract(data[adapterPosition])
                }
            }
        }
    }

    override fun getItemViewType(data: PortfolioAsset?): Int {
        return if (data != null) {
            BuilderViewHolder.ALTERNATIVE_VIEW_HOLDER
        } else {
            BuilderViewHolder.ALTERNATIVE_VIEW_HOLDER
        }
    }

    override fun fillFieldsInViewHolder(viewHolder: RecyclerView.ViewHolder, portfolio: PortfolioAsset) {
        if (viewHolder is CardAssetViewHolder) {
            with(viewHolder.binding.layoutItemInvestment) {
                with(portfolio.share) {
                    tvLabelAbbreviationNameAsset.text = name
                    tvLabelProductPrice.text = root.context.getString(
                        R.string.brazilian_price_placeholder,
                        decimalFormat.format(close)
                    )
                }
            }
        }
    }

    override fun getViewHolder(viewType: Int, viewRoot: ViewGroup): RecyclerView.ViewHolder =
        BuilderViewHolder.build(viewType, viewRoot) { CardAssetViewHolder(viewRoot) }
}