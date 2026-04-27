package com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp3.primitives.compose

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.viewbinding.ViewBinding
import com.br.justcomposelabs.databinding.LayoutCustomViewOverlayBinding
import com.br.justcomposelabs.databinding.LayoutTextCenterSampleBindingComposeBinding
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

@Composable
fun <T : ViewBinding> LoadMergeLayoutComponent(
    factory: (inflater: LayoutInflater, parent: ViewGroup) -> T,
    modifier: Modifier = Modifier,
    update: T.() -> Unit = {},
) {
    /*
        Essa estratégia de ignorar o parâmetro attachToParent da factory serve para
        quando layout tem como elemento raiz <merge>. Nesse caso o métod0 inflate do
        layout não possui esse parâmetro booleano, então precisamos encapsular a chamada

        Discussão sobre carregar Layout XML cujo elemento raiz é um merge
        https://share.google/aimode/Y27EsTY1yZcLVtLK1
     */
    AndroidViewBinding(
        factory = { inflate, viewGroup, _ ->
            factory(inflate, viewGroup)
        },
        modifier = modifier,
        update,
    )
}

@Composable
fun <T : ViewBinding> LoadLayoutComponent(
    factory: (inflater: LayoutInflater, parent: ViewGroup, attachToParent: Boolean) -> T,
    modifier: Modifier = Modifier,
    update: T.() -> Unit = {},
) {
    AndroidViewBinding(
        factory = factory,
        modifier = modifier,
        update,
    )
}

@Preview(showBackground = true)
@Composable
fun LoadMergeLayoutComponentPreview() {
    JustComposeLabsTheme {
        LoadMergeLayoutComponent(
            LayoutCustomViewOverlayBinding::inflate,
            modifier =
            Modifier
                .navigationBarsPadding()
                .systemBarsPadding(),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadLayoutComponentPreview() {
    /**
     * @see com.br.justcomposelabs.tutorial.composable.interoperability.AndroidViewBindingExamplePreview
     */
    JustComposeLabsTheme {
        LoadLayoutComponent(
            LayoutTextCenterSampleBindingComposeBinding::inflate,
            modifier =
            Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .systemBarsPadding(),
        ) {
            tvContent.textAlignment = View.TEXT_ALIGNMENT_CENTER
        }
    }
}
