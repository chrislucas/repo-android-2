package com.br.justcomposelabs.tutorial.gptstudies.changegridlayout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

// Cartão para itens de lista usando Material3
@Composable
fun ListItemCard(text: String) {
    ElevatedCard(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { /* ação ao clicar */ },
        elevation = CardDefaults.elevatedCardElevation(4.dp),
    ) {
        Box(
            modifier = Modifier.padding(16.dp),
            contentAlignment = Alignment.CenterStart,
        ) {
            Text(text)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListItemCardPreview() {
    JustComposeLabsTheme {
        ListItemCard(text = "Sample Item")
    }
}
