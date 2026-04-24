package com.br.funwithcustommodifiers.tutorials.medium.countrecompositions

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


/*
    Catching excessive recompositions in Jetpack Compose with tests
    https://proandroiddev.com/catching-excessive-recompositions-in-jetpack-compose-with-tests-8d0b952e2853

    - O autor do artigo motivado em descobrir onde estava o trecho de codigo que gerava excessiva
    recomposicao reduziu o codigo ao minimo possível para realizar investigacao.
        - O bug original aparecia num trecho de codigo complexo onde executava-se multiplas animacoes,
        Condicionais para construcao de UI e atualizacoes de estado

        - O problema nao se limitava a uma simples composable, um componente shimmer usado
        para indicar carregamento provia o ambiente necessário, pequeno e auto contido para demonstrar
        o problema

    - ABaixo teremos
        - codigo que gera excessiva recomposicao
        - a correcao


     Por que essa funcao composable recompoe excessivamente?
     - xShimmer:

 */

@Preview(showBackground = true)
@Composable
fun ShimmerWithMuchRecomposition(
    modifier: Modifier = Modifier,
    width: Dp = 0.dp,
    height: Dp = 0.dp,
    gradientWidth: Dp = 16.dp,
    shape: CornerBasedShape = RoundedCornerShape(4.dp),
    shimmerColor: Color = Color(0xFFD2D2D2),
    backColor: Color = Color(0xFFA2A2A2)
) {
    val density = LocalDensity.current
    val widthPx = with(density) { width.toPx() }
    val heightPx = with(density) { height.toPx() }
    val gradientWidth = with(density) { gradientWidth.toPx() }

    /*
        rememberInfiniteTransition
            - composable: https://composables.com/docs/androidx.compose.animation/animation-core/composable-functions/rememberInfiniteTransition
                -  Cria um InfiniteTransition que executa infinite child animations.
     */
    val transition = rememberInfiniteTransition(label = "shimmer")


    val xShimmer by transition.animateFloat(
        initialValue = -gradientWidth,
        targetValue = widthPx + heightPx,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val brush = Brush.linearGradient(
        colors = listOf(backColor, shimmerColor, backColor),
        start = Offset(xShimmer - gradientWidth, 0f),
        end = Offset(xShimmer, heightPx)
    )

    Spacer(
        modifier = modifier
            .size(width, height)
            .background(brush, shape)
    )
}
