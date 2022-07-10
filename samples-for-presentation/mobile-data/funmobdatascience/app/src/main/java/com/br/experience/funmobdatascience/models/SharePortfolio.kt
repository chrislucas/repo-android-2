package com.br.experience.funmobdatascience.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Carteira de portfolio de um usuário
 */
@Parcelize
class SharePortfolio(val purchasedShares: List<Portfolio>): Parcelable