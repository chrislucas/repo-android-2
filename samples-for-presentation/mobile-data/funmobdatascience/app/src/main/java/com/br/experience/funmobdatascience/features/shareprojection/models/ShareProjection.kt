package com.br.experience.funmobdatascience.features.shareprojection.models

import android.os.Parcelable
import com.br.experience.funmobdatascience.features.shares.models.Share
import kotlinx.parcelize.Parcelize

@Parcelize
class ShareProjection(
    val share: Share,
    val projections: List<PeriodProjectionFutureState>
): Parcelable