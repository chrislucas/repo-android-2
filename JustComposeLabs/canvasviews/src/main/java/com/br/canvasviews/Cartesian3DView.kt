package com.br.canvasviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

class Cartesian3DView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private val textSizePx: Float
    private val textPaint = Paint()

    private val axisPaint = Paint().apply {
        strokeWidth = 4f
        style = Paint.Style.STROKE
        isAntiAlias = true
    }

    // Variáveis de controle de rotação em radianos
    private var rotationX = 0f
    private var rotationY = 0f

    private var lastTouchX = 0f
    private var lastTouchY = 0f

    init {
        isClickable = true

        val scale = resources.displayMetrics.scaledDensity
        // Converter 23sp para pixels
        textSizePx = 15f * scale

        // Configurar o Paint para o texto
        textPaint.apply {
            textSize = textSizePx
            isAntiAlias = true
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                lastTouchX = event.x
                lastTouchY = event.y
            }

            MotionEvent.ACTION_MOVE -> {
                val dx = event.x - lastTouchX
                val dy = event.y - lastTouchY
                // Atualiza a rotação com movimento do dedo
                rotationY += dx * 0.01f
                rotationX += dy * 0.01f
                lastTouchX = event.x
                lastTouchY = event.y
                invalidate()
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width.toFloat()
        val height = height.toFloat()
        val centerX = width / 2
        val centerY = height / 2
        val spaceSize = 300f

        val axes = listOf(
            floatArrayOf(spaceSize, 0f, 0f),    // X positivo
            floatArrayOf(-spaceSize, 0f, 0f),   // X negativo
            floatArrayOf(0f, spaceSize, 0f),    // Y positivo
            floatArrayOf(0f, -spaceSize, 0f),   // Y negativo
            floatArrayOf(0f, 0f, spaceSize),    // Z positivo
            floatArrayOf(0f, 0f, -spaceSize)    // Z negativo
        )

        val colors = listOf(Color.RED, Color.RED, Color.GREEN, Color.GREEN, Color.BLUE, Color.BLUE)

        // Desenha os eixos rotacionados
        for (i in axes.indices step 2) {
            val startXYZ = axes[i]
            val endXYZ = axes[i + 1]
            val lineColor = colors[i]
            val startRot = rotatePoint(startXYZ, rotationX, rotationY)
            val endRot = rotatePoint(endXYZ, rotationX, rotationY)

            val start2D = projectPoint(startRot, width, height, centerX, centerY)
            val end2D = projectPoint(endRot, width, height, centerX, centerY)

            // Desenha o eixo
            axisPaint.color = lineColor
            textPaint.color = lineColor

            canvas.drawLine(start2D.x, start2D.y, end2D.x, end2D.y, axisPaint)

            // Desenha as etiquetas no começo e no final, com a mesma cor do eixo
            val labelOffset = 40f
            when (i) {
                0 -> { // eixo X
                    // no início
                    canvas.drawText("X", start2D.x - labelOffset, start2D.y, textPaint)
                    // no final
                    canvas.drawText("X", end2D.x + labelOffset, end2D.y, textPaint)
                }
                2 -> { // eixo Y
                    // no início
                    canvas.drawText("Y", start2D.x, start2D.y - labelOffset, textPaint)
                    // no final
                    canvas.drawText("Y", end2D.x, end2D.y + labelOffset, textPaint)
                }
                4 -> { // eixo Z
                    // no início
                    canvas.drawText("Z", start2D.x, start2D.y + labelOffset, textPaint)
                    // no final
                    canvas.drawText("Z", end2D.x, end2D.y - labelOffset, textPaint)
                }
            }
        }
    }

    /**
     * Rotaciona um ponto 3D ao redor dos eixos X e Y.
     * Usa matrizes de rotação:
     * y' = y * cos(θ) - z * sin(θ) (rotação X)
     * z' = y * sin(θ) + z * cos(θ)
     * x' = x * cos(φ) + z1 * sin(φ) (rotação Y)
     * z' = -x * sin(φ) + z1 * cos(φ)
     */
    private fun rotatePoint(point: FloatArray, rotX: Float, rotY: Float): FloatArray {
        val (x, y, z) = point

        // Rotação em torno de X
        val y1 = y * cos(rotX) - z * sin(rotX)
        val z1 = y * sin(rotX) + z * cos(rotX)

        // Rotação em torno de Y
        val x2 = x * cos(rotY) + z1 * sin(rotY)
        val z2 = -x * sin(rotY) + z1 * cos(rotY)

        return floatArrayOf(x2, y1, z2)
    }

    /**
     * Projeta um ponto 3D na tela usando perspective projection.
     * Quanto maior o valor de z, menor a escala, simulando profundidade.
     *
     * @param point3D O ponto no espaço 3D (x, y, z)
     * @param screenWidth Largura da tela (não usado explicitamente, mas pode ser útil)
     * @param screenHeight Altura da tela (não usado explicitamente)
     * @param centerX Ponto central X na tela
     * @param centerY Ponto central Y na tela
     * @param distance Distância da câmera ao plano de projeção (padrão 500)
     * @return Offset contendo a posição projetada na tela
     */
    private fun projectPoint(
        point3D: FloatArray,
        screenWidth: Float,
        screenHeight: Float,
        centerX: Float,
        centerY: Float,
        distance: Float = 500f
    ): PointF {
        val (x, y, z) = point3D

        // Fator de escala que diminui com a profundidade z
        val scale = distance / (distance + z)

        // Calcula a posição na tela ajustando pelo centro e escala
        val projectedX = centerX + x * scale
        val projectedY = centerY + y * scale

        return PointF(projectedX, projectedY)
    }
}