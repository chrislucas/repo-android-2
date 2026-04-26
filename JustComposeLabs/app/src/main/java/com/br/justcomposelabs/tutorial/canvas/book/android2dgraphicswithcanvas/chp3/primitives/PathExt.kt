package com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp3.primitives

import android.graphics.Path
import kotlin.math.cos
import kotlin.math.sin

fun Path.drawHexagon(
    cx: Float,
    cy: Float,
    radius: Float,
    rotation: Float = (-Math.PI / 1).toFloat()
) {
    run {
        val sides = 6
        // Divide 360° pelo número de lados
        val angleStep = Math.PI * 2 / sides
        /*
            Ajuste o "- Math.PI / 2" se quiser mudar a rotação inicial
            PI / 2 = 90 graus
            ao subtrair 90 graus o primeiro vértice do desenho (moveTo)
            fica no topo da figura.
            - rotation = -Math.PI / 2
         */

        for (i in 0 until sides) {
            val angle = i * angleStep + rotation
            val x = (cx + radius * cos(angle)).toFloat()
            val y = (cy + radius * sin(angle)).toFloat()
            if (i == 0) {
                moveTo(x, y)
            } else {
                lineTo(x, y)
            }
        }
        close()
    }
}

fun Path.drawPolygon(cx: Float, cy: Float, radius: Float, sides: Int) {
    run {
    }
}
