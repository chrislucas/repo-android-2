package com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp3.primitives

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.br.justcomposelabs.R
import kotlin.properties.Delegates

class DashedTriangleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private val paintDashedTriangle = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        /*

         */
        pathEffect = DashPathEffect(floatArrayOf(90f, 30f), 0f)
    }

    private val path = Path()
    private var scaleStrokeWidth: Float by Delegates.notNull()

    private var join: Paint.Join by Delegates.notNull()

    private var strokeWidthValue: Float by Delegates.notNull()

    private var lineColor: Int by Delegates.notNull()


    init {
        context.obtainStyledAttributes(attrs, R.styleable.DashedTriangleView).apply {
            scaleStrokeWidth = getFloat(
                R.styleable.DashedTriangleView_scaleStrokeWidth,
                0.01f
            )

            join = Paint.Join.entries[
                getInt(
                    R.styleable.DashedTriangleView_joinStyle,
                    Paint.Join.ROUND.ordinal
                )
            ]

            strokeWidthValue = getFloat(
                R.styleable.DashedTriangleView_strokeWidthPaint,
                15.0f
            )

            lineColor = getColor(
                R.styleable.DashedTriangleView_lineColor,
                Color.rgb(130, 230, 110)
            )

            recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paintDashedTriangle)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        paintDashedTriangle.run {
            strokeWidth = strokeWidthValue // w * scaleStrokeWidth
            strokeJoin = join
            color = lineColor
        }

        path.run {
            moveTo(w * .5f, h * .2f)
            lineTo(w * .2f, h * .8f)
            lineTo(w * .8f, h * .8f)
            close()
        }
    }
}
