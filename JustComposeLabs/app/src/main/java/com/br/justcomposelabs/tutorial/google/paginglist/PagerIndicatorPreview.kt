package com.br.justcomposelabs.tutorial.google.paginglist

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun PagerIndicatorPreview() {
    PagerIndicator(pageCount = 4, currentPageIndex = 1)
}