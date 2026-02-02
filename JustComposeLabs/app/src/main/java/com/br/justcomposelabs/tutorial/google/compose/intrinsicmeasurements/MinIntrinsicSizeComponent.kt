package com.br.justcomposelabs.tutorial.google.compose.intrinsicmeasurements

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
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


/*
    Intrinsics in action
    https://developer.android.com/develop/ui/compose/layouts/intrinsic-measurements#intrinsics-in-action
 */


@Composable
fun TwoTextsUndesiredResultComponent(
    modifier: Modifier = Modifier,
    first: String,
    second: String
) {
    Row(
        modifier = modifier
            .systemBarsPadding()
    ) {
        Text(
            text = first,
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp)
                .wrapContentWidth(Alignment.Start)
        )

        /*
            O componente VerticalDivider vai se extender pela Row
            inteira, causando um resultado nao esperado.
            - Isso ocorre porque Row mede cada componente filho de forma
            individual, e a altura do componente Text nao pode ser usada para
            restringir a altura do componente Divider

         */
        VerticalDivider(
            color = Color.Black,
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )

        Text(
            text = second,
            modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp)
                .wrapContentWidth(Alignment.End)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TwoTextsUndesiredResultComponentPreview() {
    JustComposeLabsTheme {
        TwoTextsUndesiredResultComponent(first = "Hi", second = "There")
    }
}

data class Content(val first: String, val second: String)
@Composable
private fun TwoTextsDesiredResultComponent(
    modifier: Modifier = Modifier,
    content: Content
) {

    /*
       Para que o componente Divider preencha somente o espaco disponivel dado um tamano
       especifico, devemos usar o IntrinsicSize.Min passando dentro do metodo height
       - height(IntrinsicSize.Min) dimensiona os seus nós filhos para que tenham a
       altura maxima limitada pela sua altura minima intrisica


       Explicacao

       - minINtrisicHeight da funcao Row é o maximo minIntrinsicHeight de
       seus componentes filhos

       - o minIntrinsicHeight do divider é 0, e nao ocupa um espaco se nao
       existir restricoes/constraints

       - minIntrinsicheight do Text é o especificado pelo texto que cotem a chamda
       para width.

       - Assim a restricao de altura do componente Row se torna o maximo minIntrinsicHeight do Texto

       - O divider entao expandi sua altura para o height até  o tem

     */
    Row(
        modifier = modifier
            .systemBarsPadding()
            .height(IntrinsicSize.Min)
    ) {
        Text(
            text = content.first,
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp)
                .wrapContentWidth(Alignment.Start)
        )


        VerticalDivider(
            color = Color.Black,
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )

        Text(
            text = content.second,
            modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp)
                .wrapContentWidth(Alignment.End)
        )
    }
}


class ContentPreviewParameterProvider(
    override val values: Sequence<Content>
    = sequenceOf(
        Content("Hello", "World"),
        Content("This is a longer text that might wrap around several lines.", "Jetpack"),
        Content("Compose", "Labs"),
    )
) : PreviewParameterProvider<Content>


@Preview(showBackground = true)
@Composable
private fun TwoTextsDesiredResultComponentPreview(
    @PreviewParameter(ContentPreviewParameterProvider::class) content: Content
) {
    JustComposeLabsTheme {
        TwoTextsDesiredResultComponent(content = content)
    }
}