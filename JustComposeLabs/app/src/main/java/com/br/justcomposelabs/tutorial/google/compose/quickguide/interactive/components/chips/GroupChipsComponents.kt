package com.br.justcomposelabs.tutorial.google.compose.quickguide.interactive.components.chips

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GroupChipComponent(words: List<String> = emptyList()) {
    var selectedChip by remember(key1 = words) { mutableStateOf(words.first()) }

    FlowRow(
        modifier = Modifier
            .padding(8.dp)
            .systemBarsPadding()
            .navigationBarsPadding(),
        Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        words.forEach { word ->
            FilterChip(selected = selectedChip == word, onClick = { selectedChip = word }) {
                Text(
                    text = word,
                    style = TextStyle()
                )
            }
        }
    }
}

/**
 * TODO
 * criar um componente que o ususuário digita uma conteúdo e isso alimenta
 * GroupChipComponent
 */

@Preview(showBackground = true)
@Composable
private fun GroupChipComponentPreview() {
    GroupChipComponent(
        words = listOf(
            "Android",
            "Compose",
            "Kotlin",
            "Jetpack",
            "Material Design",
            "UI",
            "UX",
            "Development",
            "Clean Architecture",
            "MVI",
            "MVVM"
        )
    )
}
