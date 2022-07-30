package com.br.experience.funmobdatascience.features.sharedetails.models

import android.os.Parcelable
import com.br.experience.funmobdatascience.features.share.models.Share
import kotlinx.parcelize.Parcelize

@Parcelize
class ShareDetail(
    val share: Share,
    val projections: List<PeriodProjectionFutureState>
): Parcelable