package com.br.experience.funmobdatascience.features.portfolio.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class PeriodOfProjections(
    @SerializedName("period") open val period: Int,
    @SerializedName("description") open val description: String
) : Parcelable {
    class OneDay : PeriodOfProjections(1, "1 dia")
    class SevenDays : PeriodOfProjections(7, "7 dias")
    class FifteenDays : PeriodOfProjections(15, "15 dias")
    class ThirdDays : PeriodOfProjections(30, "30 dias")
    class Custom(
        @SerializedName("period") override val period: Int,
        @SerializedName("description") override val description: String
    ) : PeriodOfProjections(period, description)
}
