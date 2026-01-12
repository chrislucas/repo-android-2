package com.br.justcomposelabs.tutorial.composable.interoperability

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.br.justcomposelabs.databinding.LayoutTextCenterSampleBindingComposeBinding

/*
    Pesquisa
    https://developer.android.com/s/results?hl=pt-br&q=AndroidViewBinding

    AndroidView with view binding
    https://developer.android.com/develop/ui/compose/migrate/interoperability-apis/views-in-compose#androidview_with_view_binding

    TODO
    explorar mais essa nota feita pela IA

    Considerations for Lazy lists:
    If you are using AndroidViewBinding within a LazyColumn or LazyRow,
    consider using the specific AndroidView overload designed for lazy lists.
    This overload (introduced in Compose 1.4.0-rc01 and later) optimizes performance
    by allowing Compose to reuse the underlying View instances, preventing unnecessary re-inflations.

 */


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AndroidViewBindingExamplePreview() {
    AndroidViewBinding(LayoutTextCenterSampleBindingComposeBinding::inflate) {

    }
}