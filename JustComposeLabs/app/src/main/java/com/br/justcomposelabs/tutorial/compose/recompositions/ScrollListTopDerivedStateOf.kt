package com.br.justcomposelabs.tutorial.compose.recompositions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch


/*
    https://share.google/aimode/f64s0DINXHHxEXMvK
 */


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ScrollToTopExample() {
    val listState = rememberLazyListState()

    val showButtonScrollTop by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .systemBarsPadding()
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(.9f)
                .fillMaxWidth(),
            state = listState
        ) {
            items(100) {
                Text(
                    "Item #$it",
                    modifier = Modifier.padding(16.dp),
                    style = TextStyle(
                        fontSize = 23.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }

        if (showButtonScrollTop) {
            Button(
                modifier = Modifier
                    .weight(.1f)
                    .padding(4.dp)
                    .fillMaxWidth(),
                onClick = {
                    /*
                        gostaria de saber como configurar o
                        scroll para parar em uma posição
                        específica ou com um offset

                        https://share.google/aimode/3W3ts6pZvBLkx6Oto
                     */
                    coroutineScope.launch {
                        listState.animateScrollToItem(index = 0)
                    }
                },
                shape = RectangleShape
            ) {
                Text("Top")
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ScrollToSpecificPositionExample() {
    val listState = rememberLazyListState()

    val showButtonScrollTop by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 30
        }
    }

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .systemBarsPadding()
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(.9f)
                .fillMaxWidth(),
            state = listState
        ) {
            items(100) {
                Text(
                    "Item #$it",
                    modifier = Modifier.padding(16.dp),
                    style = TextStyle(
                        fontSize = 23.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }
        }

        if (showButtonScrollTop) {
            Button(
                modifier = Modifier
                    .weight(.1f)
                    .padding(4.dp)
                    .fillMaxWidth(),
                onClick = {
                    /*
                        gostaria de saber como configurar o
                        scroll para parar em uma posição
                        específica ou com um offset

                        https://share.google/aimode/3W3ts6pZvBLkx6Oto
                     */
                    coroutineScope.launch {
                        // https://share.google/aimode/STwSpmefLMmFoZgmF
                        // 1. Recupera as informações de layout da lista
                        val layoutInfo = listState.layoutInfo

                        // 2. Altura total visível da lista (Viewport) em pixels
                        val viewportHeight = layoutInfo.viewportSize.height

                        // 3. Tenta pegar a altura de um item que já esteja visível
                        // Se o item não estiver visível, você pode usar uma estimativa ou valor fixo
                        val itemHeight = layoutInfo.visibleItemsInfo.firstOrNull()?.size ?: 0

                        // 4. Cálculo do offset para centralizar
                        // Usamos um valor negativo porque o 'scrollOffset' em animateScrollToItem
                        // "empurra" o item para baixo se for negativo.
                        val centerOffset = (viewportHeight / 2) - (itemHeight / 2)

                        listState.animateScrollToItem(
                            index = 20, // O índice que você quer centralizar
                            scrollOffset = -centerOffset // Negativo para centralizar na tela
                        )

                    }
                },
                shape = RectangleShape
            ) {
                Text("Top")
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HorizontalCenteredScroll() {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val showButtonScrollTop by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 5
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .systemBarsPadding()
    ) {
        LazyRow(
            state = listState,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(50) { index ->
                Card(
                    modifier = Modifier.size(width = 150.dp, height = 100.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.LightGray)
                ) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Text("Item $index")
                    }
                }
            }
        }

        val buttonSpringFloat = spring<Float>(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessMedium
        )
        val buttonSpringOffset = spring<IntOffset>(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessMedium
        )

        AnimatedVisibility(
            visible = showButtonScrollTop,
            enter = fadeIn(buttonSpringFloat) + slideInVertically(
                animationSpec = buttonSpringOffset,
                initialOffsetY = { fullHeight -> -fullHeight }
            ),
            exit = fadeOut(buttonSpringFloat) + slideOutVertically(
                animationSpec = buttonSpringOffset,
                targetOffsetY = { fullHeight -> -fullHeight }
            )
        ) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        val layoutInfo = listState.layoutInfo

                        // 1. Largura total visível da LazyRow na tela
                        val viewportWidth = layoutInfo.viewportSize.width

                        // 2. Largura de um item (pegamos o primeiro visível como referência)
                        val itemWidth = layoutInfo.visibleItemsInfo.firstOrNull()?.size ?: 0

                        // 3. Cálculo para centralizar (valor negativo empurra o item para a direita)
                        val centerOffset = (viewportWidth / 2) - (itemWidth / 2)

                        listState.animateScrollToItem(
                            index = 10,
                            scrollOffset = -centerOffset
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RectangleShape
            ) {
                Text("Centralizar Item 10")
            }
        }
    }
}
