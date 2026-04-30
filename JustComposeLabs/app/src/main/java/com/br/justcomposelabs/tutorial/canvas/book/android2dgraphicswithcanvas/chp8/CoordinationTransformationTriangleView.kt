package com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp8

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import java.text.DecimalFormat
import kotlin.properties.Delegates

/**
 * CoordinationTransformationTriangleView implementa um sistema de mapeamento entre espaços de coordenadas.
 *
 * Esta View demonstra como converter coordenadas de um "Espaço Lógico" para o "Espaço do Dispositivo":
 * 1. Espaço Lógico (Mundo): Onde definimos os pontos de forma abstrata (ex: pointA = .2f to .2f),
 *    geralmente em uma escala unitária (0.0 a 1.0).
 * 2. Espaço do Dispositivo (Tela): Onde os pixels realmente vivem (ex: de 0 a 1080).
 *
 * A mágica acontece no método `onSizeChanged`, onde a `TransformationOperation` é calibrada:
 * - Escala: Define quanto cada unidade lógica vale em pixels. O Y é invertido (-deviceHeight)
 *   para que o sistema matemático (Y cresce para cima) combine com o do Android (Y cresce para baixo).
 * - Translação (Move): Posiciona a origem (0,0) no canto inferior esquerdo da tela.
 *
 * No `onDraw`, as coordenadas lógicas são convertidas para pixels via `transformXY` antes de serem
 * passadas para as funções de desenho do Canvas.
 */
class CoordinationTransformationTriangleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private val paintTriangle: Paint
    private val paintPosition: Paint
    private val paintBarycenter: Paint

    private val pointA = .2f to .2f
    private val pointB = .8f to .8f
    private val pointC = .8f to .2f

    private var barycenter: Pair<Float, Float> by Delegates.notNull()

    private var dimension: Pair<Int, Int> by Delegates.notNull()

    private var path = Path()

    private var transformationOperation = TransformationOperation()

    private var edgeAC: Path by Delegates.notNull()
    private var edgeCB: Path by Delegates.notNull()
    private var edgeBA: Path by Delegates.notNull()

    init {
        setBackgroundColor(Color.WHITE)
        paintTriangle = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.BLACK
            style = Paint.Style.STROKE
            strokeWidth = 10f
            strokeJoin = Paint.Join.ROUND
        }

        paintPosition = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.RED
            style = Paint.Style.FILL
            strokeWidth = 5f
            textSize = 30f
        }

        paintBarycenter = Paint(paintPosition).apply {
            textAlign = Paint.Align.CENTER
        }
    }

    private val decimalFormat = DecimalFormat("#.00")

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paintTriangle)

        val (barycenterX, barycenterY) = barycenter
        val (w, h) = dimension

        canvas.drawText(
            "Dimension($w, $h)",
            barycenterX,
            barycenterY,
            paintBarycenter
        )

        /**
         * TODO substituir drawText por drawTextOnPath
         */

            val (ax, ay) = transformXY(pointA)
        canvas.drawTextOnPath(
            "A(${decimalFormat.format(ax)}, ${decimalFormat.format(ay)})",
            edgeAC,
            40f,
            -10f,
            paintPosition
        )

        val (bx, by) = transformXY(pointB)
        canvas.drawTextOnPath(
            "B(${decimalFormat.format(bx)}, ${decimalFormat.format(by)})",
            edgeBA,
            40f,
            -10f,
            paintPosition
        )

        val (cx, cy) = transformXY(pointC)
        canvas.drawTextOnPath(
            "C(${decimalFormat.format(cx)}, ${decimalFormat.format(cy)})",
            edgeCB,
            10f,
            -10f,
            paintPosition
        )
    }

    override fun onSizeChanged(
        deviceWidth: Int,
        deviceHeight: Int,
        oldw: Int,
        oldh: Int,
    ) {
        super.onSizeChanged(deviceWidth, deviceHeight, oldw, oldh)

        /*
            // Define a escala (Y negativo para inverter o eixo)
            Isso faz com que o ponto lógico (0, 0) (origem matemática)
            seja mapeado para o canto inferior esquerdo da tela do Android, e o ponto (1, 1) aponte para o topo.
         */
        transformationOperation = if (deviceWidth > deviceHeight) {
            transformationOperation
                .withScale(deviceHeight.toFloat(), -deviceHeight.toFloat())
        } else {
            transformationOperation
                .withScale(deviceWidth.toFloat(), -deviceWidth.toFloat())
        }

        /*
            Move a origem para o canto inferior esquerdo
         */
        transformationOperation = transformationOperation
            .withMove(0f, deviceHeight.toFloat())

        dimension = Pair(deviceWidth, deviceHeight)

        val (ax, ay) = transformXY(pointA)
        val (bx, by) = transformXY(pointB)
        val (cx, cy) = transformXY(pointC)

        barycenter = Pair((ax + bx + cx) / 3.0f, (ay + by + cy) / 3.0f)

        edgeAC = Path().apply {
            moveTo(ax, ay)
            lineTo(cx, cy)
        }

        edgeCB = Path().apply {
            moveTo(cx, cy)
            lineTo(bx, by)
        }

        edgeBA = Path().apply {
            moveTo(bx, by)
            lineTo(ax, ay)
        }

        path.run {
            moveTo(ax, ay)
            lineTo(bx, by)
            lineTo(cx, cy)
            close()
        }
    }

    private fun transformXY(p: Pair<Float, Float>): Pair<Float, Float> =
        transformationOperation.transformX(p.first) to transformationOperation.transformY(p.second)
}
