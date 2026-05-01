package com.br.justcomposelabs.tutorial.gptstudies.changegridlayout

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

// Cartão para itens de grid usando Material3
@Composable
fun GridItemCard(text: String) {
    val ctx = LocalContext.current
    ElevatedCard(
        modifier =
        Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(4.dp)
            .clickable {
                Toast.makeText(ctx, text, Toast.LENGTH_SHORT).show()
            },
        elevation = CardDefaults.elevatedCardElevation(4.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GridItemCardPreview() {
    JustComposeLabsTheme {
        GridItemCard(text = "Sample Item")
    }
}
