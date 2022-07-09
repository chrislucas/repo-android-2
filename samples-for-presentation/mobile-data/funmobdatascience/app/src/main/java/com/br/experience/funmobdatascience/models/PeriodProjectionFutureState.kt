package com.br.experience.funmobdatascience.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class PeriodProjectionFutureState(
    val futureState: ShareFutureState,
    val period: PeriodoOfProjections
) : Parcelable