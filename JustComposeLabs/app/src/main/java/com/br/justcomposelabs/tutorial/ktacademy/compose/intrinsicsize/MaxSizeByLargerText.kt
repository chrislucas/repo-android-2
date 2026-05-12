package com.br.justcomposelabs.tutorial.ktacademy.compose.intrinsicsize

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme


/*
    Como fazer com que uma lista de componentes que envolvem um Text
    tenha a mesma largura baseada no maior texto entre eles ?

    - Modifier.width(IntrinsicSize.Max) no componente "pai"
        - No Caso abaixo no componente Column
    - Modifier.fillMaxWidth() no componente filho
    
    - Por que isso funciona?
        - width(IntrinsicSize.Max) significa pegar o máximo de espaço que o
        componente mais largo precisa
        - é possível ter o resultado oposto, pegar o menor tamanho baseado no menor componente

 */
@Composable
internal fun MaxBoxComponent() {
    Box(modifier = Modifier, contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
            // 1. Margem externa (afasta a Column de outros elementos)
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp)) // 2. Corta o que vier depois (background)
            .background(Color.LightGray)     // 3. Pinta a área clipada
            // 4. Padding interno (afasta o conteúdo das bordas cinzas)
            .padding(10.dp)
                /*
                    usar o IntrinsicSize.Min faz com que o container
                    pai utilize o espaço mínimo, baseado no componente
                    filho que usa o menor espaço
                 */
                .width(IntrinsicSize.Max),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DecoratedTextComponent("Text 1")
            DecoratedTextComponent("T")
            DecoratedTextComponent("Text 2")
            DecoratedTextComponent("Text 3 larger than 1 and 2")
        }
    }
}


@Composable
internal fun MinBoxComponent() {
    Box(modifier = Modifier, contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                // 1. Margem externa (afasta a Column de outros elementos)
                .padding(10.dp)
                .clip(RoundedCornerShape(10.dp)) // 2. Corta o que vier depois (background)
                .background(Color.LightGray)     // 3. Pinta a área clipada
                // 4. Padding interno (afasta o conteúdo das bordas cinzas)
                .padding(10.dp)
                /*
                    usar o IntrinsicSize.Min faz com que o container
                    pai utilize o espaço mínimo, baseado no componente
                    filho que usa o menor espaço
                 */
                .width(IntrinsicSize.Min),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DecoratedTextComponent("Text 1")
            DecoratedTextComponent("T")
            DecoratedTextComponent("Text 2")
            DecoratedTextComponent("Text 3 larger than 1 and 2")
        }
    }
}


@Composable
internal fun DecoratedTextComponent(text: String, modifier: Modifier = Modifier) {
    Text(
        text,
        style = TextStyle(
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
            color = Color.White
        ),
        modifier = modifier
            .fillMaxWidth()
            // 1. Espaçamento entre os itens da lista (Margem)
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp)) // 2. Arredonda os cantos do fundo cinza escuro
            .background(Color.DarkGray)     // 3. Cor de fundo do texto
            // 4. Espaçamento entre o texto e a borda cinza (Padding)
            .padding(10.dp)
    )
}

@Preview(showBackground = true)
@Composable
internal fun MaxBoxComponentPreview() {
    JustComposeLabsTheme {
        MaxBoxComponent()
    }
}

@Preview(showBackground = true)
@Composable
internal fun MinBoxComponentPreview() {
    JustComposeLabsTheme {
        MinBoxComponent()
    }
}
