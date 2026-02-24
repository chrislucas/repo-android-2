package com.br.justcomposelabs.tutorial.google.compose.uitesting.rallyproject.data


import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/*
    Stability in Compose
    https://developer.android.com/develop/ui/compose/performance/stability

 */
@Immutable
data class Account(
    val name: String,
    val number: Int,
    val balance: Float,
    val color: Color
)

@Immutable
data class Bill(
    val name: String, val due: String, val amount: Float, val color: Color
)

object UserData {
    val accounts = listOf(
        Account(
            "Checking", 1234, 2210.65f, Color(0xFF004940)
        ), Account(
            "Home Savings", 5678, 8672.88f, Color(0xFF005D57)
        ), Account("Car Savings", 9012, 2530.75f, Color(0xFF04B97F)),

        Account(
            "Vacation", 3456, 253f, Color(0xFF37EFBA)
        )
    )

    val bills = listOf(
        Bill(
            "RedPay Credit", "Jan 29", 45.36f,
            Color(0xFFFFDC78)
        ),
        Bill(
            "Rent",
            "Feb 1",
            1275.00f,
            Color(0xFFFF6951)
        ),
        Bill(
            "TabFine Loans", "Feb 12", 459.23f, Color(0xFFFFD7D0)
        ),
        Bill("Gym Membership", "Feb 15", 29.00f, Color(0xFFFFAC12)),
        Bill("Credit Card", "Feb 22", 87.33f, Color(0xFFFFDC78)),
        Bill("Internet", "Feb 24", 64.00f, Color(0xFFFF6951)),
    )
}