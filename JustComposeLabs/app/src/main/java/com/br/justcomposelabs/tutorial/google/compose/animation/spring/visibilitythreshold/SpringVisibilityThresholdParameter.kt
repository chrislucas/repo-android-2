package com.br.justcomposelabs.tutorial.google.compose.animation.spring.visibilitythreshold

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.br.justcomposelabs.tutorial.google.compose.animation.spring.FadeViewModel

/*
    https://share.google/aimode/uMn0WossB6WyQjaiE

    - visibilityThreshold: No Spring isso define o quão perto do valor ifnal o objeto precisa
    estar para animação ser considerada "concluída" e parar de calcular

        - Em animacoes de escala ou movimento um threshold maior corta a
        animação no final do movimento, economizando processamento ou dando um aspecto de término
        seco)

        - Enquanto um threshold pequeno garante mais precisão, mas pode manter o "efeito-mola"
        calculando oscilações imperceptiveis por mais tempo.

    - Resumo da implementação
        - Performance: visibilityThreshold evita que o sitema gaste CPU em animacoes
        com muitos elementos. Ela calcula micro-movimentos

        - Valores Padrao:
            - Para Dp: IntOffset.VisibilityThreshold
            - Floats (Alpha/Scale): 0.01f
        - O exemplo dado pela IA.
            - Ele usa valor 0.1f e esse valor faz o efeito travar
            no estado final um pouco antes do que o normal. Um valor
            mais alto como .5f a animação parece "pular" para o final


    fonte
    https://share.google/aimode/F1XXjzKlPFejSk5zZ
    https://share.google/aimode/oEh4dAJuAVBlGyUQm
 */

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ThresholdAnimationScreen(viewModel: FadeViewModel = viewModel()) {
    val isVisible by viewModel.isVisible.collectAsStateWithLifecycle()
    val threshold by viewModel.visibilityThreshold.collectAsStateWithLifecycle()

    /**
     * visibilityThreshold
     * @see androidx.compose.animation.core.VisibilityThreshold

     Dp.VisibilityThreshold
     IntOffset.VisibilityThreshold

     Desempenho: Definir um limite maior pode melhorar levemente a desempenho
     ao encerrar animações complexas mais cedo, embora raramente seja necessário
     alterar os padrões, a menos que você perceba "vibrações" indesejadas no final da transição
     */

    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .systemBarsPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier =
            Modifier
                .fillMaxWidth()
                /*
                     fixa o tamanho desse componente mesmo quando ele desaparecer com a
                     animacao
                 */
                .height(180.dp),
            // .requiredHeight(intrinsicSize = IntrinsicSize.Min),
            contentAlignment = Alignment.Center,
        ) {
            this@Column.AnimatedVisibility(
                visible = isVisible,
                enter =
                fadeIn() +
                    scaleIn(
                        initialScale = 0.5f,
                        animationSpec =
                        spring(
                            dampingRatio = Spring.DampingRatioHighBouncy,
                            stiffness = Spring.StiffnessLow,
                            // Define que quando a escala chegar a 0.1 unidades do alvo, ela para.
                            // Isso evita que a mola fique "tremendo" infinitamente em escalas minúsculas.
                            visibilityThreshold = threshold,
                        ),
                    ),
                exit =
                fadeOut() +
                    scaleOut(
                        targetScale = 0.5f,
                        animationSpec =
                        spring(
                            stiffness = Spring.StiffnessLow,
                            visibilityThreshold = threshold,
                        ),
                    ),
            ) {
                Box(
                    modifier =
                    Modifier
                        .size(150.dp)
                        .background(Color.Yellow, CircleShape),
                    contentAlignment = Alignment.Center,
                ) {
                    Text("Threshold", color = Color.Black)
                }
            }
        }

        // Controles
        Card(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Visibility Threshold: ${"%.2f".format(threshold)}")
                Slider(
                    value = threshold,
                    onValueChange = { viewModel.updateVisibilityThreshold(it) },
                    valueRange = 0.01f..0.9f,
                )
                Text(
                    text = "Valores altos fazem a mola parar mais cedo (corte seco).",
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}
