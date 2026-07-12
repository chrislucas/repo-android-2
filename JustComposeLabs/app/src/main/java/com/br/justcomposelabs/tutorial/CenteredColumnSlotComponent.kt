package com.br.justcomposelabs.tutorial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CenteredColumnSlotComponent(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit // Agora expõe o escopo de coluna
) {
    Column(
        modifier = modifier.wrapContentHeight(), // Garante altura mínima necessária
        horizontalAlignment = Alignment.CenterHorizontally, // Centraliza horizontalmente
        verticalArrangement = Arrangement.Center // Centraliza verticalmente se houver espaço extra
    ) {
        content()
    }
}
