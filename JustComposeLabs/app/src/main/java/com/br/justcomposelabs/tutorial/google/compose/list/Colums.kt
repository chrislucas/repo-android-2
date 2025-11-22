package com.br.justcomposelabs.tutorial.google.compose.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
    https://developer.android.com/develop/ui/compose/lists#lazy

    LazyListScope DSL
    https://developer.android.com/develop/ui/compose/lists#lazylistscope
 */


@Preview(showBackground = true)
@Composable
fun FakeItemsPreview() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(3.dp)
            .background(Color(0xFFB388FF)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            FakeItemCard("1")
        }

        items(5) { index ->
            FakeItemCard("$index")
        }

        item {
            FakeItemCard("6")
        }
    }
}


@Composable
private fun FakeItemCard(content: String) {
    Card(
        shape = RoundedCornerShape(2.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier
            .padding(4.dp)
            .fillMaxSize(),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = content, fontSize = 12.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

