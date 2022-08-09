package com.br.experience.funmobdatascience.features.portfolio.models

import android.os.Parcelable
import com.br.experience.funmobdatascience.features.shares.models.Share
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PortfolioAsset(
    @SerializedName("shared") val share: Share,
    @SerializedName("sharePurchaseVolume") val sharePurchaseVolume: Double,
    @SerializedName("currentValue") val currentValue: Double,
    @SerializedName("totalSpent") val totalSpent: Double
) : Parcelable {

    private val meanCost: Double
        get() = totalSpent / sharePurchaseVolume

    val balance: Double
        get() = currentValue - meanCost * sharePurchaseVolume
}