package com.br.experience.funmobdatascience.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class PeriodoOfProjections(open val period: Int): Parcelable {
    class OneDay: PeriodoOfProjections(1)
    class SevenDays: PeriodoOfProjections(7)
    class FifteenDays: PeriodoOfProjections(15)
    class ThirdDays: PeriodoOfProjections(30)
    class Custom(override val period: Int): PeriodoOfProjections(period)
}
