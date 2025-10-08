package com.br.justcomposelabs.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

/*
    https://github.com/android/snippets/blob/30ed522851a9273c94afcd3a4c30bf674346ad18/compose/snippets/src/main/java/com/example/compose/snippets/util/ImageLoadingSampleUtils.kt
 */


private val range = (0 .. 1000000)


/*
    https://picsum.photos/
 */

fun fetchRandomImageLorenPicsum(
    seed: Int = range.random(),
    width: Int = 300,
    height: Int = width,
) = "https://picsum.photos/seed/$seed/$width/$height"


@Composable
fun rememberFetchRandomImageLorenPicsum(
    seed: Int = range.random(),
    width: Int = 300,
    height: Int = width,
): String = remember { fetchRandomImageLorenPicsum(seed, width, height) }