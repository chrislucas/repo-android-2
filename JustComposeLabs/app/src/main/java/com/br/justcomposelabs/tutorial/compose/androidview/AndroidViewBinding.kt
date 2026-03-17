package com.br.justcomposelabs.tutorial.compose.androidview

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.br.canvasviews.databinding.ActivityComposeInViewBinding
import com.br.justcomposelabs.R

/*
    AndroidView with view binding
    https://developer.android.com/develop/ui/compose/migrate/interoperability-apis/views-in-compose#androidview_with_view_binding
    Para incorporrar um layout xml a uma funcao composable usamos AndroidViewBinding API.
    Para conseguir utilizar tal api o projeto precisa implementar view binding (configuracao no arquivo
    gradle)
 */


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AndroidBindingViewSample() {
    val resource = LocalResources.current
    AndroidViewBinding(ActivityComposeInViewBinding::inflate) {
        //tvSampleText.setBackgroundColor(Color.GREEN)
        tvSampleText.text = resource.getString(R.string.hello_world)
    }
}