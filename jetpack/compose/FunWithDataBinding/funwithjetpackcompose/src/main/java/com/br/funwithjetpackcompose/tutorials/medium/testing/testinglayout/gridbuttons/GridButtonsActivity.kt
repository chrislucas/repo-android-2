package com.br.funwithjetpackcompose.tutorials.medium.testing.testinglayout.gridbuttons

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.br.funwithjetpackcompose.tutorials.medium.testing.testinglayout.gridbuttons.ui.theme.FunWithDataBindingTheme
import com.br.mylibrary.R

/*
    https://developer.android.com/develop/ui/compose/lists#lazy-grids
    - LazyVerticalGrid and LazyHorizontalGrid
        - Prove suporte para mostrar items num grid
        - A Lazy vertical grid mostrara os items num container vertical "scrollavel",
        abrangendo varias colunas e linhas
        - Assim como A Lazy horizontal grid dispoe os elementos em no eixo horizontal

    - Grids tem a mesma api e funcionalidades que a List e utilizar uma DSL similar
        - LazyGridScope: https://developer.android.com/reference/kotlin/androidx/compose/foundation/lazy/grid/LazyGridScope
            - Receiver Scope usado por LazyVerticalGrid
                https://developer.android.com/reference/kotlin/androidx/compose/foundation/lazy/grid/package-summary#LazyVerticalGrid(androidx.compose.foundation.lazy.grid.GridCells,androidx.compose.ui.Modifier,androidx.compose.foundation.lazy.grid.LazyGridState,androidx.compose.foundation.layout.PaddingValues,kotlin.Boolean,androidx.compose.foundation.layout.Arrangement.Vertical,androidx.compose.foundation.layout.Arrangement.Horizontal,androidx.compose.foundation.gestures.FlingBehavior,kotlin.Boolean,androidx.compose.foundation.OverscrollEffect,kotlin.Function1)

 */
class GridButtonsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FunWithDataBindingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TestLazyVerticalGrid(
                        modifier = Modifier.padding(innerPadding)
                    )

                    TestLazyHorizontalGrid(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun TestLazyVerticalGrid(modifier: Modifier = Modifier) {
    val padding5 = Modifier.padding(5.dp)
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(minSize = 128.dp), content = {
            item(
                span = {
                    GridItemSpan(maxLineSpan)
                }
            ) {
                Button({}, modifier = padding5) {
                    Text("Button")
                }
            }

            item(
                span = {
                    GridItemSpan(maxLineSpan)
                }
            ) {
                FilledTonalButton({}, modifier = padding5) {
                    Text("Filled-Tonal-Button")
                }
            }

            item(
                span = {
                    GridItemSpan(maxLineSpan)
                }
            ) {
                OutlinedButton({}, modifier = padding5) {
                    Text("Outlined-Button")
                }
            }

            item(
                span = {
                    GridItemSpan(maxLineSpan)
                }
            ) {
                ElevatedButton({}, modifier = padding5) {
                    Text("Outlined-Button")
                }
            }

            item(
                span = {
                    GridItemSpan(maxLineSpan)
                }
            ) {
                TextButton({}, modifier = padding5) {
                    Text("Text-Button")
                }
            }
        })

    HorizontalDivider()
}


@Composable
fun TestLazyHorizontalGrid(modifier: Modifier = Modifier) {
    val padding5 = Modifier.padding(5.dp)
    LazyHorizontalGrid(
        modifier = modifier,
        rows = GridCells.Adaptive(minSize = 128.dp), content = {
            item(
                span = {
                    GridItemSpan(maxLineSpan)
                }
            ) {
                Button({}, modifier = padding5) {
                    Text("Button")
                }
            }

            item(
                span = {
                    GridItemSpan(maxLineSpan)
                }
            ) {
                FilledTonalButton({}, modifier = padding5) {
                    Text("Filled-Tonal-Button")
                }
            }

            item(
                span = {
                    GridItemSpan(maxLineSpan)
                }
            ) {
                OutlinedButton({}, modifier = padding5) {
                    Text("Outlined-Button")
                }
            }

            item(
                span = {
                    GridItemSpan(maxLineSpan)
                }
            ) {

                ElevatedButton({}, modifier = padding5) {
                    Text("Outlined-Button")
                }
            }

            item(
                span = {
                    GridItemSpan(maxLineSpan)
                }
            ) {
                TextButton({}, modifier = padding5) {
                    Text("Text-Button")
                }
            }
        })
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    FunWithDataBindingTheme {
        TestLazyVerticalGrid()
    }
}