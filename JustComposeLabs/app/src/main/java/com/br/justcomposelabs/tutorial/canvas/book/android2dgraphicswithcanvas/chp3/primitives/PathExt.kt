package com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp3.primitives

import android.graphics.Path
import android.graphics.PointF
import kotlin.math.cos
import kotlin.math.sin

/**
 * @see com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.UnitCircleComponentPreview
 */
fun Path.drawHexagon(
    cx: Float,
    cy: Float,
    radius: Float,
    rotation: Float = (-Math.PI / 4).toFloat()
): List<PointF> = run {
    val points = mutableListOf<PointF>()
    val sides = 6
    // Divide 360° pelo número de lados
    val angleStep = Math.PI * 2 / sides
    /*
        Ajuste o "- Math.PI / 2" se quiser mudar a rotação inicial
        PI / 2 = 90 graus
        ao subtrair 90 graus o primeiro vértice do desenho (moveTo)
        fica no topo da figura.
        - rotation = -Math.PI / 2

        - https://share.google/aimode/56FGrfoWASGH9BqiD
            - Explicação:
                - Iteracao de 60 graus: O loop percorre os 6 vertices, com ângulos internos
                de 60, ao termino do loop completa os 360

                - Cálculo das coordenadas
                    - x = cx + raio * cos(ângulo)
                    - y = cy + raio * sin(ângulo)

               - Orientacao
                    - O primeiro vertice do hexagono começa em -PI/6
                    - Para ter um hexágono com as arestas no topo e em baixo na horizontal o ângulo
                    deve ser 0.

        # Por que Math.PI / 2 muda a posição do polígono?
            - No sistema de coordenadas do Android, 0 radianos começa na posição
            "3 hpras"
        # Uso de seno e cosseno para encontrar os pontos x e y a partir do ponto central
            -
        https://share.google/aimode/Bwi35Q6WIWNuxqgju
     */

    for (i in 0 until sides) {
        val angle = i * angleStep + rotation
        val x = (cx + radius * cos(angle)).toFloat()
        val y = (cy + radius * sin(angle)).toFloat()
        points += PointF(x, y)
        if (i == 0) {
            moveTo(x, y)
        } else {
            lineTo(x, y)
        }
    }
    close()
    points.toList()
}

fun Path.drawRegularPolygon(
    cx: Float,
    cy: Float,
    radius: Float,
    sides: Int,
    rotation: Float = (-Math.PI / 4).toFloat()
): List<PointF> = if (sides < 3) {
    emptyList()
} else {
    run {
        val points = mutableListOf<PointF>()
        val angleSteps = Math.PI * 2 / sides
        for (i in 0 until sides) {
            val angle = i * angleSteps + rotation
            val x = (cx + radius * cos(angle)).toFloat()
            val y = (cy + radius * sin(angle)).toFloat()
        }
        points
    }
}

fun Path.drawIrregularPolygon(points: List<PointF>) = run {
    for ((i, p) in points.withIndex()) {
        if (i == 0) {
            moveTo(p.x, p.y)
        } else {
            lineTo(p.x, p.y)
        }
    }
    close()
}
