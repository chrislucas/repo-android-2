package com.br.justcomposelabs.tutorial.google.compose.sideffects.rememberupdatedstate.navigation

import android.os.Build
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.navigation.NavType
import com.br.justcomposelabs.R
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf

@Serializable
sealed class NavRoute(@field:StringRes val title: Int) {

    @Serializable
    data object SplashScreenRoute : NavRoute(R.string.splashscreen)

    @Serializable
    data object HomeScreenRoute : NavRoute(R.string.homescreen)

    @Serializable
    data class ProfileScreenRoute(val profile: Profile) : NavRoute(R.string.profilescreen) {
        companion object {

            /*
                Guide: Migrating to Type-Safe Navigation in Compose and Navigation 2
                https://developer.android.com/guide/navigation/type-safe-destinations
             */
            val ProfileNavType = object : NavType<Profile>(isNullableAllowed = false) {
                override fun get(bundle: Bundle, key: String): Profile? {
                    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        bundle.getParcelable(key, Profile::class.java)
                    } else {
                        bundle.getParcelable(key)
                    }
                }

                override fun parseValue(value: String): Profile {
                    return Json.decodeFromString(value)
                }

                override fun put(bundle: Bundle, key: String, value: Profile) {
                    bundle.putParcelable(key, value)
                }

                override fun serializeAsValue(value: Profile): String {
                    return Json.encodeToString(value)
                }
            }
        }
    }

    companion object {
        val typeMap = mapOf(typeOf<Profile>() to ProfileScreenRoute.ProfileNavType)
    }
}

/*
    Jetpack compose navigation with custom NavType
    https://proandroiddev.com/jetpack-compose-navigation-with-custom-navtype-9b44dd8820e
 */
