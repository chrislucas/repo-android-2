package com.br.justcomposelabs.tutorial.google.compose.state.storestate

import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import kotlinx.parcelize.Parcelize

/*
    https://developer.android.com/develop/ui/compose/state#parcelize

    Parcelable implementation generator
    https://developer.android.com/kotlin/parcelize
 */

@Parcelize
data class City(val name: String, val country: String) : Parcelable


@Composable
fun CityScreen(city: City) {
    val selectedCity = rememberSaveable {
        mutableStateOf(city)
    }
}