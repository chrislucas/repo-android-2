package com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp3.path

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

/*
    https://share.google/aimode/y98Q1SlZxBRJDlHF6
    https://share.google/aimode/KwY1GSB79bMvfjor7
 */
class RationalQuadraticBezierCurveView @JvmOverloads constructor(
    ctx: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(ctx, attrs, defStyle) {

    private val path = Path()

    private val paintPath = Paint(Paint.ANTI_ALIAS_FLAG).apply {}

    init {
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }
}
