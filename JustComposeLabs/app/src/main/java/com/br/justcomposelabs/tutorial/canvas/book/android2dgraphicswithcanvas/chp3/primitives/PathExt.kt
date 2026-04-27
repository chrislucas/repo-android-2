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
    rotation: Float = -Math.PI.toFloat(), // poderia rotacionar 90 graud PI / 2
): List<PointF> =
    run {
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
            - No sistema de coordenadas do Android, 0 grau começa na posição
            "3 hpras", o eixo X positivo a direita (primeiro quadrante do plano cartesiano)

            - Ao subtrair PI/2 ou 90 graus, rotacionamos o ponto inicial para esquerda,
            no sentido anti-horário

            - Motivo técnico
                - Se o ângulo for 0, o primeiro ângulo é calculado a direita x = raio + cx, y = raio + cy
                - -PI/2 move o primeiro ponto para o topo da reta Y, indo em sentido anti-horário
            - Visualizando no Plano Cartesiano:
                - 0 rad: Lado direito (Leste).
                - π/2 (90°): Base (Sul) — Lembre-se que no Android o Y cresce para baixo.
                - π (180°): Lado esquerdo (Oeste).
                - 3π/2 ou -π/2 (-90°): Topo (Norte).


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
    rotation: Float = (-Math.PI / 4).toFloat(),
): List<PointF> =
    if (sides < 3) {
        emptyList()
    } else {
        run {
            val points = mutableListOf<PointF>()
            val angleSteps = Math.PI * 2 / sides
            for (i in 0 until sides) {
                val angle = i * angleSteps + rotation
                val x = (cx + radius * cos(angle)).toFloat()
                val y = (cy + radius * sin(angle)).toFloat()
                if (i == 0) {
                    moveTo(x, y)
                } else {
                    lineTo(x, y)
                }
                points += PointF(x, y)
            }
            close()
            points
        }
    }

fun Path.drawIrregularPolygon(points: List<PointF>) =
    run {
        for ((i, p) in points.withIndex()) {
            if (i == 0) {
                moveTo(p.x, p.y)
            } else {
                lineTo(p.x, p.y)
            }
        }
        close()
    }
