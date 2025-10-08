package com.br.justcomposelabs.tutorial.medium.dynamicclorstheme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable

/*
    TODO
    https://medium.com/@hiren6997/dynamic-colors-in-compose-how-to-use-material-you-the-right-way-8c7e9c3f2a1c
 */


@Composable
fun ThemeDynamicColor(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {

}