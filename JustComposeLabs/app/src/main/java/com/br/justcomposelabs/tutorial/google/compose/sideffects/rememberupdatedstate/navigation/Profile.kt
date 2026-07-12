package com.br.justcomposelabs.tutorial.google.compose.sideffects.rememberupdatedstate.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Profile(val name: String, val age: Long) : Parcelable
