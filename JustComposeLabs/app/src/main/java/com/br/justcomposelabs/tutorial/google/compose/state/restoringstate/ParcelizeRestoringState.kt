package com.br.justcomposelabs.tutorial.google.compose.state.restoringstate

import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import kotlinx.parcelize.Parcelize

/*
    Restoring state in Compose
        - https://developer.android.com/develop/ui/compose/state#restore-ui-state
 */

@Parcelize
private data class City(
    val name: String,
    val country: String,
) : Parcelable

@Composable
fun CityScreen() {
    val selectedCity =
        rememberSaveable {
            mutableStateOf(City("São Paulo", "Brazil"))
        }
}
