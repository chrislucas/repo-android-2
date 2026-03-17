package com.br.funwithjetpackcompose.tutorials.medium.testing.testinglayout.gridbuttons

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.br.mylibrary.R

/*
    Lazy grids
    https://developer.android.com/develop/ui/compose/lists#lazy-grids

 */

@Composable
fun LazyVerticalGridAdaptive(modifier: Modifier = Modifier) {
    LazyVerticalGrid(modifier = modifier, columns = GridCells.Adaptive(minSize = 30.dp), content = {
        item {
            RandomImage()
        }
    })

    LazyHorizontalGrid(modifier = modifier, rows = GridCells.Adaptive(minSize = 30.dp)) {

    }

    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Adaptive(minSize = 30.dp)
    ) {
        item {
            RandomImage()
        }
    }

    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Fixed(3)
    ) {

    }

    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.FixedSize(size = 30.dp)
    ) { }

    LazyHorizontalGrid(rows = GridCells.Adaptive(minSize = 30.dp)) { }
}

@Composable
fun LazyVerticalStaggeredGridAdaptive(modifier: Modifier = Modifier) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Adaptive(minSize = 30.dp)
    ) {
        item {
            RandomImage()
        }
    }
}


@Composable
fun LazyVerticalStaggeredGridFixed(modifier: Modifier = Modifier) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Fixed(3)
    ) {

    }
}


@Composable
fun LazyHorizontalGridAdaptive(modifier: Modifier = Modifier) {
    LazyHorizontalGrid(
        modifier = modifier,
        rows = GridCells.Adaptive(minSize = 30.dp)
    ) {

    }
}


@Composable
fun RandomImage() {
    // https://api.thecatapi.com/v1/images/search
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://cdn2.thecatapi.com/images/MTYyNTY0MA.jpg")
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.placeholder),
        contentDescription = stringResource(R.string.default_placeholder_image_description),
        contentScale = ContentScale.Crop,
        modifier = Modifier.clip(CircleShape),
    )
}