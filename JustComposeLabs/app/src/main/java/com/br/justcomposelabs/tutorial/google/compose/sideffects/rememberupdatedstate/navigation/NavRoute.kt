package com.br.justcomposelabs.tutorial.google.compose.sideffects.rememberupdatedstate.navigation

import androidx.annotation.StringRes
import com.br.justcomposelabs.R
import kotlinx.serialization.Serializable

@Serializable
sealed class NavRoute(@field:StringRes val title: Int) {

    @Serializable
    data object SplashScreenRoute : NavRoute(R.string.splashscreen)

    @Serializable
    data object HomeScreenRoute : NavRoute(R.string.homescreen)

    @Serializable
    data class ProfileScreenRoute(val profile: Profile) : NavRoute(R.string.profilescreen)
}