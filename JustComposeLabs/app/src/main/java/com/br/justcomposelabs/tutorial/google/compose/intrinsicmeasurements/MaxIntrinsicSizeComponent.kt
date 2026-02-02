package com.br.justcomposelabs.tutorial.google.compose.intrinsicmeasurements

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme

/*
https://developer.android.com/develop/ui/compose/layouts/intrinsic-measurements

    - uma das regras do compose é que podemos medir os elementos filhos que compoe
    uma UI uma vez
        - fazer isso mais de uma vez gera um Runtime Exception
        - Porem existem situacoes onde necessitamos de informacoes de um componente antes
        dele ser "medido"
        - Intriics nos permite realizar uma consulta a um componente
        na estrutura antes dele ser medido

    compose Intrinsic Size.Min
        - IntrincicSize.Min e IntrinsicSize.Max
        - Modifier.width(IntrinsicSize.Min)
            - Qual a largura minima para que esse componente seja mostrado adequadamente?
        - Modifier.width(IntrinsicSize.Max)
            - Qual a largura maxima para que esse componente seja mostrado adequadamente?
        - Modifier.height(IntrinsicSize.Max)
            - Qual a altura maxima para que esse componente seja mostrado adequadamente?
        - Modifier.height(IntrinsicSize.Min)
            - Qual a altura minima para que esse componente seja mostrado adequadamente?

        Por exemplo: Se perguntarmos o minIntrinsicHeight de um Text com uma restricao de
        largura "infinita" (creio que wrap_content), a resposta da altura para esse componente
        será de uma única linha

        Note que realizar a query para obter o valor de instrinsic measurement nao mede o componente,
        logo nao vai ocorrer um Runtime Exception por medi-lo 2x. Essa query é realizada antes
        de medi-lo, e baseado nessa informacao, o componente "pai" calcula as "restricoes" para
        medir o seu componente filho
 */

@Composable
fun TwoTextsDesiredResultInstrinsicSizeComponent(
    modifier: Modifier = Modifier, first: String, second: String
) {


    /*
        example intrinsics Max compose
        Usar Modifier.width(IntrinsicsSize.Max) ou
        Modifier.height(IntrisicsSize.Max) forca o componente
        "pai" a redimensionar-se baseado na dimensao maxima de um de seus filhos,
        recurso util quando seus componentes filhos tem um tamanho flexivel e
        precisamos alinhar para uma medida. em comum
     */


    /*
        A Row usas IntrinsicSize.Max paaa calcular o tamanho baseado em seu nó com
        maior tamanho
     */
    Row(
        modifier = modifier
            .systemBarsPadding()

    ) {
        Text(
            text = first,
            modifier = Modifier
                .weight(1f) // vai encaixar o texto dentro do espaco disponivel de largura
                .padding(start = 4.dp),
            textAlign = TextAlign.Start
        )


        /*
            Usa o fillMaxHeight que refere-se ao componente Row
            Calcula o intrinsic max height
         */
        VerticalDivider(
            color = Color.Black, modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )

        Text(
            text = second,
            modifier = Modifier
                .weight(1f) // vai encaixar o texto dentro do espaco disponivel de largura
                .padding(end = 4.dp),
            textAlign = TextAlign.End
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TwoTextsDesiredResultInstrinsicSizeComponentPreview() {
    JustComposeLabsTheme {
        TwoTextsDesiredResultInstrinsicSizeComponent(
            modifier = Modifier.height(IntrinsicSize.Max),
            first = "This is a longer text that might wrap around several lines.",
            second = "World"
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun TwoTextsDesiredResultMinInstrinsicSizeComponentPreview() {
    JustComposeLabsTheme {
        TwoTextsDesiredResultInstrinsicSizeComponent(
            modifier = Modifier.height(IntrinsicSize.Min),
            first = "This is a longer text that might wrap around several lines.",
            second = "World"
        )
    }
}
