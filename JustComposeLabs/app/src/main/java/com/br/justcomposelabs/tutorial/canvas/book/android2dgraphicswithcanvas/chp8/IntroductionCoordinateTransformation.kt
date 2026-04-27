package com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp8

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import java.text.DecimalFormat

/*
    Trasformation Operation: Translation, scaling, rotation, skewing
     - Translation: move the canvas by a certain distance in x and y direction
     - Scaling: scale the canvas by a certain factor in x and y direction
     - Rotation: rotate the canvas by a certain angle
     - Skewing: skew the canvas by a certain angle in x and y direction (Skew = distorcer)
        - distorcer a tela por um determinado ângulo nas direções x e y
 */

class TransformationOperation(
    private val move: Pair<Float, Float> = 0f to 0f,
    private val scale: Pair<Float, Float> = 1f to 1f,
) {
    fun withMove(
        moveX: Float,
        moveY: Float,
    ) = TransformationOperation(moveX to moveY, scale)

    fun withScale(
        scaleX: Float,
        scaleY: Float,
    ) = TransformationOperation(move, scaleX to scaleY)

    fun transformX(x: Float): Float = x * scale.first + move.first

    fun transformY(y: Float): Float = y * scale.second + move.second
}

class TriangleView
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private val paintTriangle: Paint
    private val paintPosition: Paint

    private val pointA = .2f to .2f
    private val pointB = .8f to .8f
    private val pointC = .8f to .2f
    private var path =
        Path().apply {
            moveTo(.2f, .2f)
            lineTo(.8f, .8f)
            lineTo(.8f, .2f)
            close()
        }

    private var transformationOperation = TransformationOperation()

    init {
        setBackgroundColor(Color.WHITE)
        paintTriangle =
            Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = Color.BLACK
                style = Paint.Style.STROKE
                strokeWidth = 10f
                strokeJoin = Paint.Join.ROUND
            }

        paintPosition =
            Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = Color.RED
                style = Paint.Style.FILL
                strokeWidth = 5f
                textSize = 30f
            }
    }

    private val decimalFormat = DecimalFormat("#.00")

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paintTriangle)

        val (ax, ay) = transformXY(pointA)
        canvas.drawText(
            "A(${decimalFormat.format(ax)}, ${decimalFormat.format(ay)})",
            ax - 130f,
            ay + 40f,
            paintPosition,
        )

        val (bx, by) = transformXY(pointB)
        canvas.drawText(
            "B(${decimalFormat.format(bx)}, ${decimalFormat.format(by)})",
            bx - 130f,
            by - 20f,
            paintPosition,
        )

        val (cx, cy) = transformXY(pointC)
        canvas.drawText(
            "C(${decimalFormat.format(cx)}, ${decimalFormat.format(cy)})",
            cx - 130f,
            cy + 40f,
            paintPosition,
        )
    }

    override fun onSizeChanged(
        deviceWidth: Int,
        deviceHeight: Int,
        oldw: Int,
        oldh: Int,
    ) {
        super.onSizeChanged(deviceWidth, deviceHeight, oldw, oldh)

        transformationOperation =
            if (deviceWidth > deviceHeight) {
                transformationOperation
                    .withMove(0.0f, deviceHeight.toFloat())
                    .withScale(deviceHeight.toFloat(), -deviceHeight.toFloat())
            } else {
                transformationOperation
                    .withMove(0.0f, deviceHeight.toFloat())
                    .withScale(deviceWidth.toFloat(), -deviceWidth.toFloat())
            }

        val (ax, ay) = transformXY(pointA)
        val (bx, by) = transformXY(pointB)
        val (cx, cy) = transformXY(pointC)

        path =
            Path().apply {
                moveTo(ax, ay)
                lineTo(bx, by)
                lineTo(cx, cy)
                close()
            }
    }

    private fun transformXY(p: Pair<Float, Float>): Pair<Float, Float> =
        transformationOperation.transformX(p.first) to transformationOperation.transformY(p.second)
}
