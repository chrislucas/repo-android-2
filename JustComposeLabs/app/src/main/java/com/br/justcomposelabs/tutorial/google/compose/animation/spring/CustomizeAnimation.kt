package com.br.justcomposelabs.tutorial.google.compose.animation.spring

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/*
    https://developer.android.com/develop/ui/compose/animation/customize
 */

class SizeAnimationViewModel : ViewModel() {
    var expanded by mutableStateOf(false)

    /*
        TODO: usar esse estado para criar uma interface com controles que possam
        modificar seus valores
            - Use algum componente de seleção com as opçoes de mudanca de:
                - dampingRatio
                - stiffness
                - talvez visibilidade

     */
    private val springSpecState = MutableStateFlow(
        SpringSpecState(visibilityThreshold = Dp.VisibilityThreshold)
    )

    val observerSpringSpecState: StateFlow<SpringSpecState<Dp>> =
        springSpecState.asStateFlow()

    fun init() {
        viewModelScope.launch {
            while (true) {
                expanded = !expanded
                delay(1000L)
            }
        }
    }

    fun updateSpringSpecState(newSpecState: SpringSpecState<Dp>) {
        springSpecState.update {
            newSpecState
        }
    }
}

/*
    https://share.google/aimode/cztBAibRoIEi73HtQ

    No Jetpack Compose a animacao Spring é especificação em física que cria movimentos
    naturais e fluídos.

    Diferente de animações baseadas em tempo (como tween), spring não possui duração fixa,
    elas param quando a força física atinge o equilíbrio, o que a torna idela para
    lidar com interrupções e mudanças bruscas de estado

    Parâmetros principais de spring()
    - dampingRatio: Define o quão "saltitante" (Bouncy) é a animação
        - Spring.DampingRatioHighBouncy: Muitas oscilações.
        - Spring.DampingRatioMediumBouncy: O padrão equilibrado.
        - Spring.DampingRatioLowBouncy: Pouca oscilação.
        - Spring.DampingRatioNoBouncy: Retorna ao estado final sem oscilar.

    - stiffness: Define a velocidade com que a mola (bouncy) retorna ao repouso (análgo
    à duração)
        - Spring.StiffnessHigh: Movimento muito rápido.
        - Spring.StiffnessMedium: O padrão moderado.
        - Spring.StiffnessLow ou StiffnessVeryLow: Movimentos lentos e graduais.
    - visibilityThreshold


    Quando usar spring vs tween ?
        - Use spring para elementos interativos (botoes, drag-n-drop, expansao de cards),
        onde o movimento precisa parecer "vivo"
 */

data class SpringSpecState<T>(
    var dampingRatio: Float = Spring.DampingRatioNoBouncy,
    var stiffness: Float = Spring.StiffnessMedium,
    var visibilityThreshold: T? = null,
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CustomizeAnimation(viewModel: SizeAnimationViewModel = viewModel()) {
    val size by animateDpAsState(
        targetValue = if (viewModel.expanded) 200.dp else 100.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    LaunchedEffect(Unit) {
        viewModel.init()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .systemBarsPadding()
            .border(
                border = BorderStroke(2.dp, Color.Black),
                shape = RectangleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .border(border = BorderStroke(2.dp, Color.Black))
                .drawBehind {
                    drawRect(Color(0xFFE91E63))
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                "inner",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                ),
            )
        }
    }
}

class FadeViewModel : ViewModel() {
    private val mutableStateVisibility = MutableStateFlow(true)
    val isVisible: StateFlow<Boolean> = mutableStateVisibility.asStateFlow()

    private val mutableVisibilityThreshold = MutableStateFlow(0.01f)
    val visibilityThreshold: StateFlow<Float> = mutableVisibilityThreshold.asStateFlow()

    init {
        alternarVisibilidade()
    }

    private fun alternarVisibilidade() {
        viewModelScope.launch {
            while (true) {
                delay(1000) // Aguarda 1 segundo
                mutableStateVisibility.update { !mutableStateVisibility.value }
            }
        }
    }

    fun updateVisibilityThreshold(threshold: Float) {
        mutableVisibilityThreshold.update { threshold }
    }
}

@Preview(showBackground = true)
@Composable
fun FadeAnimationScreen(viewModel: FadeViewModel = viewModel()) {
    // Coleta o estado da ViewModel de forma segura para o ciclo de vida
    val isVisible by viewModel.isVisible.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = isVisible,
            // Personalizando o fadeIn com spring
            enter = fadeIn(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessVeryLow
                )
            ) + scaleIn(
                initialScale = .5f, // inicia com metade do tamanho
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ),
            // Personalizando o fadeOut com spring
            exit = fadeOut(
                animationSpec = spring(stiffness = Spring.StiffnessLow)
            ) + scaleOut(
                targetScale = .5f, // Diminui para metade antes de sumir
                animationSpec = spring(stiffness = Spring.StiffnessLow)
            )
        ) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(Color.Magenta, RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("Olá Spring!", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = if (isVisible) "Visível" else "Escondido")
    }
}
