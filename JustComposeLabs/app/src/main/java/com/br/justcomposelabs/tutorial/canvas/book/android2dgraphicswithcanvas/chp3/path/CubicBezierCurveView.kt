package com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp3.path

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

/*
    https://share.google/aimode/XcJPiJkj55XKPuEQs
 */
class CubicBezierCurveView @JvmOverloads constructor(
    ctx: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(ctx, attrs, defStyle) {

    private val path = Path()

    private val paintPath = Paint().apply {
        color = Color.rgb(120, 10, 120)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        path.run {
            moveTo(100.0f, 100.0f)
            path.cubicTo(
                200f,
                100f, // x1, y1: Primeiro ponto de controle
                400f,
                900f, // x2, y2: Segundo ponto de controle
                600f,
                500f // x3, y3: Ponto final
            )
            canvas.drawPath(path, paintPath)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }
}
