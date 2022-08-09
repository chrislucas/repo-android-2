package com.br.experience.funmobdatascience.features.portfolio.views.list

import com.br.experience.funmobdatascience.features.portfolio.models.PortfolioAsset
import com.br.experience.funmobdatascience.views.list.action.ProvideItemViewType
import com.br.experience.funmobdatascience.views.list.viewholder.builder.BuilderViewHolder

class ProvidePortfolioAssetViewType : ProvideItemViewType<PortfolioAsset> {

    override fun provide(data: PortfolioAsset?): Int {
        return if (data != null) {
            BuilderViewHolder.ALTERNATIVE_VIEW_HOLDER
        } else {
            BuilderViewHolder.ALTERNATIVE_VIEW_HOLDER
        }
    }
}