package com.br.experience.funmobdatascience.features.portfolio.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Carteira de portfolio de um usuário
 */
@Parcelize
class Portfolio(@SerializedName("shares") val shares: List<PortfolioAsset>): Parcelable