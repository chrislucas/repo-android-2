package com.br.experience.funmobdatascience.features.portfolio.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class PeriodOfProjections(open val period: Int, open val description: String) : Parcelable {
    class OneDay : PeriodOfProjections(1, "1 dia")
    class SevenDays : PeriodOfProjections(7, "7 dias")
    class FifteenDays : PeriodOfProjections(15, "15 dias")
    class ThirdDays : PeriodOfProjections(30, "30 dias")
    class Custom(override val period: Int, override val description: String) : PeriodOfProjections(period, description)
}
