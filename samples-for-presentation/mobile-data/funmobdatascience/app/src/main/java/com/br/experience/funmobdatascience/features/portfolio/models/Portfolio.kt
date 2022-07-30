package com.br.experience.funmobdatascience.features.portfolio.models

import android.os.Parcelable
import com.br.experience.funmobdatascience.features.share.models.Share
import kotlinx.parcelize.Parcelize

@Parcelize
data class Portfolio(
    val share: Share,
    private val sharePurchaseVolume: Double,
    private val currentValue: Double,
    private val totalSpent: Double
) : Parcelable {

    private val meanCost: Double
        get() = totalSpent / sharePurchaseVolume

    val balance: Double
        get() = currentValue - meanCost * sharePurchaseVolume
}