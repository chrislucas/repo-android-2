package com.br.justcomposelabs.utils.composable

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Modifier.fillMaxSizePadding() =
    this
        .fillMaxSize()
        .systemBarsPadding()
        .navigationBarsPadding()
        .padding(WindowInsets.safeDrawing.asPaddingValues())
