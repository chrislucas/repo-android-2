package com.br.experience.funmobdatascience.features.share.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// modelo simples de ações financeiras
@Parcelize
class Share(
    val name: String,
    val date: String,
    val open: Double,
    val high: Double,
    val low: Double,
    val close: Double,
    val volume: Int,
) : Parcelable