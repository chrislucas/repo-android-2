package com.br.experience.funmobdatascience.features.portfolio.views.list

import androidx.recyclerview.widget.RecyclerView
import com.br.experience.funmobdatascience.R
import com.br.experience.funmobdatascience.features.portfolio.models.PortfolioAsset
import com.br.experience.funmobdatascience.features.shares.views.list.viewholder.CardAssetViewHolder
import com.br.experience.funmobdatascience.views.list.action.GenericBuildViewHolder
import java.text.DecimalFormat

class BuildPortfolioAssetViewHolder : GenericBuildViewHolder<PortfolioAsset> {

    private val decimalFormat = DecimalFormat("###,##0.00")

    override fun build(viewHolder: RecyclerView.ViewHolder, data: PortfolioAsset) {
        if (viewHolder is CardAssetViewHolder) {
            with(viewHolder.binding.layoutItemInvestment) {
                with(data.share) {
                    tvLabelAbbreviationNameAsset.text = name
                    tvLabelProductPrice.text = root.context.getString(
                        R.string.brazilian_price_placeholder,
                        decimalFormat.format(close)
                    )
                }
            }
        }
    }
}