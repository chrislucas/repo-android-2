package com.br.experience.funmobdatascience.models

import android.os.Parcelable
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