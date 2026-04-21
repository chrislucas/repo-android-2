package com.br.justcomposelabs.tutorial.google.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import timber.log.Timber

@Preview(showBackground = true)
@Composable
fun LazyListWithKey() {
    var elements by remember { mutableStateOf(List(10) { "Item: $it" }) }
    var counter by remember { mutableIntStateOf(elements.size) }

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
            elements = elements + listOf("Item: ${++counter}")
        }) {
            Text("Add")
        }
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .navigationBarsPadding()
                .systemBarsPadding()
        ) {
            Text(
                "#$counter",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.fillMaxWidth()
            )

            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                items(elements, key = { it }) { element ->
                    ElementWithKey(element = element) {
                        elements = elements.filterNot { it == element }
                        counter = elements.size
                    }
                }
            }
        }
    }
}

@Composable
fun ElementWithKey(
    element: String,
    remove: (String) -> Unit
) {
    var loading by rememberSaveable { mutableStateOf(false) }

    /*
        Caso de uso de snapshotFlow.
        https://share.google/aimode/LPUJIdnBEmGYJZZRX

        Tracking de scroll em uma LazyColumn usando snapshotFlow.
     */

    val scrollTrackingState = rememberLazyListState()

    LaunchedEffect(element) {
        snapshotFlow { scrollTrackingState.firstVisibleItemIndex }
            .map { it > 0 }
            .distinctUntilChanged()
            .collect { isPastFirst ->
                if (isPastFirst) {
                    // poderíamos trocar por uma ferramenta de analytics, por exemplo
                    Timber.tag("LazyListKey").d("Element $element is past the first item")
                }
            }
        loading = true
        // Simulate a loading delay
        delay(1000)
        loading = false
    }

    Card(
        modifier = Modifier
            .width(256.dp)
            .height(128.dp)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RectangleShape
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(48.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            } else {
                Text(
                    text = element,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            remove(element)
                        },
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 32.sp,
                        fontFamily = MaterialTheme.typography.bodyMedium.fontFamily
                    )
                )
            }
        }
    }
}
