package com.br.justcomposelabs.tutorial.google.usingviewsincompose.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView


/*
    https://developer.android.com/develop/ui/compose/migrate/interoperability-apis/views-in-compose
 */
@Preview(showBackground = true, showSystemUi = false, name = "PreviewAddCustomView")
@Composable
fun AddCustomView(modifier: Modifier = Modifier) {
    var selectedItem by remember { mutableIntStateOf(0) }
    AndroidView(
        modifier = modifier,
        factory = { ctx ->
            CustomView(ctx).apply {
                setOnClickListener {
                    counterClick = 1
                }
            }

        },
        update = { view ->
            view.counterClick = selectedItem
        }
    )
}


class CustomView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {
    var counterClick = 0
}