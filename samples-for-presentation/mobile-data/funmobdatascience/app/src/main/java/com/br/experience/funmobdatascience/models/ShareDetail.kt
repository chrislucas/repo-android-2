package com.br.experience.funmobdatascience.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ShareDetail(
    val share: Share,
    val projections: List<PeriodProjectionFutureState>
): Parcelable