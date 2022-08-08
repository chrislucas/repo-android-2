package com.br.experience.funmobdatascience.features.shareprojection.models

import android.os.Parcelable
import com.br.experience.funmobdatascience.features.portfolio.models.PeriodOfProjections
import kotlinx.parcelize.Parcelize

@Parcelize
class PeriodProjectionFutureState(
    val futureState: ShareFutureState,
    val period: PeriodOfProjections
) : Parcelable