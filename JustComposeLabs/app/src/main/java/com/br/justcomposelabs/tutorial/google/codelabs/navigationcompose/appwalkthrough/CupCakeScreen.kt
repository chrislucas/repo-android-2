package com.br.justcomposelabs.tutorial.google.codelabs.navigationcompose.appwalkthrough

import androidx.annotation.StringRes
import com.br.justcomposelabs.R

enum class CupCakeScreen(@field:StringRes val title: Int) {
    Start(title = R.string.app_name),
    Flavor(R.string.choose_flavor),
    Pickup(R.string.choose_pickup_date),
    Summary(R.string.order_summary)
}