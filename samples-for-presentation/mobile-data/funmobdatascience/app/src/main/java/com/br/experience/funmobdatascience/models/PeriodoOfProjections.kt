package com.br.experience.funmobdatascience.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class PeriodoOfProjections(open val period: Int, open val description: String) : Parcelable {
    class OneDay : PeriodoOfProjections(1, "1 dia")
    class SevenDays : PeriodoOfProjections(7, "7 dias")
    class FifteenDays : PeriodoOfProjections(15, "15 dias")
    class ThirdDays : PeriodoOfProjections(30, "30 dias")
    class Custom(override val period: Int, override val description: String) : PeriodoOfProjections(period, description)
}
