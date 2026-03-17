package com.br.funwithcustommodifiers.tutorials.composable.animations

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview

/*
    rememberInfiniteTransition
        - Cria um InfiniteTransition que pode executar infinite child animations.
        - public fun rememberInfiniteTransition(label: String = "InfiniteTransition"): InfiniteTransition
            -  Cria InfiniteTransition
            - Child Animations podem ser adicionadoas usando
                - InfiniteTransition.animateColor
                - InfiniteTransition.animateFloat
                - InfiniteTransition.animateValue

                - Child animations iniciam tao logo  elas entrem em composition, e nao vao encerrar ate que
                elas forem removidas da composition
 */


@Preview(showBackground = true)
@Composable
fun InfiniteTransitionAnimationBox() {

    @Composable
    fun InfinitelyPulsingHeart() {
        val infiniteTransition = rememberInfiniteTransition()

        // child animation float como parte do InfiniteTransition
        val scale by infiniteTransition.animateFloat(
            initialValue = 3f,
            targetValue = 6f,
            animationSpec = infiniteRepeatable(
                /*
                    Repete a cada 1000ms tween animation usando easing curve
                 */
                animation = tween(1000),
                /*
                    Apos cada iteracao de animacao,
                    a animacao iniciara novamente a partir o valor inicial/InitialValue
                    Esse ;e o comportamento padrao [RepeatMode]
                 */
                repeatMode = RepeatMode.Restart
            )
        )

        /*
            Color animation
         */
        val color by infiniteTransition.animateColor(
            initialValue = Color.Red,
            targetValue = Color(0xff800000),
            animationSpec = infiniteRepeatable(
                /*
                    Interpolacao linear entre initialValue e targeValue a cada 1000ms
                 */
                animation = tween(1000, easing = LinearEasing),
                /*
                    Uma vez que alcance o valor de targetValue, inicia uma nova iteracao em
                    no modo reverso, de targetValue para initialValue
                 */
                repeatMode = RepeatMode.Reverse
            )
        )

        Box(Modifier.fillMaxSize()) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = null,
                modifier = Modifier.align(Alignment.Center)
                    .graphicsLayer(scaleX = scale, scaleY = scale),
                tint = color
            )
        }
    }

    InfinitelyPulsingHeart()
}


@Preview(showBackground = true)
@Composable
fun ReverseInfiniteTransitionAnimationBox() {

    @Composable
    fun InfinitelyPulsingHeart() {
        val infiniteTransition = rememberInfiniteTransition()

        // child animation float como parte do InfiniteTransition
        val scale by infiniteTransition.animateFloat(
            initialValue = 3f,
            targetValue = 6f,
            animationSpec = infiniteRepeatable(
                /*
                    Repete a cada 1000ms tween animation usando easing curve
                 */
                animation = tween(1000),
                /*
                    Apos cada iteracao de animacao,
                    a animacao iniciara novamente a partir o valor inicial/InitialValue
                    Esse ;e o comportamento padrao [RepeatMode]
                 */
                repeatMode = RepeatMode.Reverse
            )
        )

        val color by infiniteTransition.animateColor(
            initialValue = Color.Red,
            targetValue = Color(0xff800000),
            animationSpec = infiniteRepeatable(
                /*
                    Interpolacao linear entre initialValue e targeValue a cada 1000ms
                 */
                animation = tween(1000, easing = LinearEasing),
                /*
                    Uma vez que alcance o valor de targetValue, inicia uma nova iteracao em
                    no modo reverso, de targetValue para initialValue
                 */
                repeatMode = RepeatMode.Restart
            )
        )

        Box(Modifier.fillMaxSize()) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = null,
                modifier = Modifier.align(Alignment.Center)
                    .graphicsLayer(scaleX = scale, scaleY = scale),
                tint = color
            )
        }
    }

    InfinitelyPulsingHeart()
}