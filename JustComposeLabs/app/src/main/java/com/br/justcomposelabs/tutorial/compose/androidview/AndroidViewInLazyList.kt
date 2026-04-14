package com.br.justcomposelabs.tutorial.compose.androidview

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.br.canvasviews.databinding.ActivityComposeInViewBinding
import com.br.justcomposelabs.tutorial.canvas.DrawText

/*
    AndroidView in Lazy lists
    https://developer.android.com/develop/ui/compose/migrate/interoperability-apis/views-in-compose#androidview_in_lazy_lists
 */

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AndroidViewBindingInLazyList() {
    LazyColumn(modifier = Modifier.statusBarsPadding()) {
        items(10) {
            AndroidViewBinding(
                ActivityComposeInViewBinding::inflate,
                modifier = Modifier,
                update = {
                    tvSampleText.text = "$it"
                }
            )
        }
    }
}

/*
    AndroidView em Lazy Lists - DrawText com altura dinâmica

    Nota: height(IntrinsicSize.Min) não funciona bem com AndroidView em LazyColumn porque:
    1. O Compose tem dificuldade em medir a altura intrínseca do AndroidView
    2. LazyColumn aplica otimizações que podem quebrar a medição
    3. A solução é usar wrapContentHeight() que permite ao AndroidView relatar sua altura
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AndroidViewInLazyList() {
    LazyColumn(modifier = Modifier.statusBarsPadding()) {
        items(10) { count ->
            AndroidView(
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                factory = { ctx ->
                    DrawText(ctx)
                },
                update = {
                    it.content = "hello world My Friend, How are you doing ?\nIndex: $count."
                },
                onReset = {
                    it.content = ""
                }
            )
        }
    }
}
