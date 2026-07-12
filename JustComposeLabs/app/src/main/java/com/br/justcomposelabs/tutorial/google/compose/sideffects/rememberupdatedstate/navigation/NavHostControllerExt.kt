package com.br.justcomposelabs.tutorial.google.compose.sideffects.rememberupdatedstate.navigation

import androidx.navigation.NavHostController

fun NavHostController.backTo(route: String) {
    popBackStack(route, inclusive = false)
}
