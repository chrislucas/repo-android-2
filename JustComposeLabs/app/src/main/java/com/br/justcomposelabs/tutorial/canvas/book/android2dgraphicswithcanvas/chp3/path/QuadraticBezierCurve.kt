package com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp3.path

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

/*
    https://share.google/aimode/XcJPiJkj55XKPuEQs
    https://share.google/aimode/KwY1GSB79bMvfjor7
 */
class QuadraticBezierCurveView @JvmOverloads constructor(
    ctx: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(ctx, attrs, defStyle) {

    private val path = Path()

    private val paintPath = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.rgb(123, 234, 45)
    }

    init {
    }
}
