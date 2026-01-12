package com.br.justcomposelabs.tutorial.compose.flowlayout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.material3.FilterChip
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/*
    Flow layouts in Compose
    https://developer.android.com/develop/ui/compose/layouts/flow
    https://github.com/android/snippets/blob/a935e3af8a3ae1b6dce2200f1b0a6bc0be4d14e6/compose/snippets/src/main/java/com/example/compose/snippets/layouts/FlowLayoutSnippets.kt#L51-L60
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FlowRowSimpleUsageExample() {
    FlowRow(
        modifier = Modifier
            .padding(8.dp)
            .systemBarsPadding()
    ) {
        ChipItem("Price: High to Low")
        ChipItem("Avg rating: 4+")
        ChipItem("Free breakfast")
        ChipItem("Free cancellation")
        ChipItem("£50 pn")
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipItem(text: String, onClick: () -> Unit = {}) {
    FilterChip(
        modifier = Modifier.padding(end = 4.dp),
        onClick = onClick,
        leadingIcon = {},
        border = BorderStroke(1.dp, Color(0xFF3B3A3C)),
        label = {
            Text(text)
        },
        selected = false
    )
}