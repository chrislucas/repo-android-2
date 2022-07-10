package com.br.experience.funmobdatascience.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class PeriodProjectionFutureState(
    val futureState: ShareFutureState,
    val period: PeriodoOfProjections
) : Parcelable