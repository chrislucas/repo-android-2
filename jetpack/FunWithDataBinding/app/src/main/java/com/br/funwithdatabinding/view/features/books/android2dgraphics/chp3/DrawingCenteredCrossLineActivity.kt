package com.br.funwithdatabinding.view.features.books.android2dgraphics.chp3

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R
import kotlin.math.min

class DrawingCenteredCrossLineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_drawing_centered_crossline)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

class CenteredCrossLineView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null
) : View(context, attr) {


    private data class LineSpec(
        val start: PointF,
        val end: PointF,
        val textLineSpec: TextLineSpec,
        val paint: Paint
    ) {
        data class TextLineSpec(val text: String, val point: PointF, val paint: Paint)

        val path = Path().apply {
            moveTo(start.x, start.y)
            lineTo(end.x, end.y)
        }

        fun drawText(canvas: Canvas) {
            canvas.drawText(
                textLineSpec.text,
                textLineSpec.point.x,
                textLineSpec.point.y,
                textLineSpec.paint
            )
        }

        fun drawPath(canvas: Canvas) {
            canvas.drawPath(path, paint)
        }
    }


    private val drawPathX: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLUE
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.MITER
    }

    private val drawPathY: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.MITER
    }

    private lateinit var lineX: LineSpec
    private lateinit var lineY: LineSpec


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (::lineX.isInitialized) {
            lineX.run {
                drawPath(canvas)
                drawText(canvas)
            }
        }

        if (::lineY.isInitialized) {
            lineY.run {
                drawPath(canvas)
                drawText(canvas)
            }
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        val strokeWidth = min(w, h) * PEN_THICKNESS
        drawPathX.strokeWidth = strokeWidth
        drawPathY.strokeWidth = strokeWidth

        // TODO lembrar que na coordenada de dispositivo o plano cartesiano aponta para baixo
        val startLineX = PointF(ZERO_FLOAT, h * HALF)
        val endLineX = PointF(w.toFloat(), h * HALF)
        lineX = LineSpec(
            startLineX,
            endLineX,
            LineSpec.TextLineSpec(
                "f(x)",
                PointF(startLineX.x + OFFSET_X, startLineX.y - OFFSET_Y),
                Paint(drawPathX).apply {
                    textSize = TEXT_AXIS_SIZE
                }
            ),
            drawPathX
        )


        val startLineY = PointF(w * HALF, ZERO_FLOAT)
        val endLineY = PointF(w * HALF, h.toFloat())
        lineY = LineSpec(
            startLineY,
            endLineY,
            LineSpec.TextLineSpec(
                "f(y)",
                PointF(startLineY.x + OFFSET_X, startLineY.y + OFFSET_Y),
                Paint(drawPathY).apply {
                    textSize = TEXT_AXIS_SIZE
                }
            ),
            drawPathY
        )
    }

    companion object {
        const val OFFSET_X = 10f
        const val OFFSET_Y = 25f
        const val TEXT_AXIS_SIZE = 34f
        const val HALF = .5f
        const val ZERO_FLOAT = .0f
        const val PEN_THICKNESS = .002f
    }
}