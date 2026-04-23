package com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp3.primitives

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.Cap
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import androidx.core.graphics.toColorInt
import com.br.justcomposelabs.R
import kotlin.math.min
import kotlin.properties.Delegates

/**
 * Join Styles
 * outro atributo que afeta a aparecencia de uma linha é o Join Style.
 * Join Style controla o tipo de curva que é criado quando 2 segmentos de reta
 * encontram-se
 * @see Paint.Join
 * @see Paint.Join.BEVEL
 *  - Uma curva conectada por 2 linhas formando um triângulo chanfrado com preenchimento
 *
 * @see Paint.Join.MITER
 *  - A aresta de fora da linha sao continuas até a intersecção. O triângulo
 *  resultante é preenchido, criando uma curva afiada. Esse é o estilo padrao
 *
 * @see Paint.Join.ROUND
 * - Um arco preenchido que conecta 2 linhas, criando uma curva arredondada
 */


class TriangleNonClosedPathJoinView @JvmOverloads constructor(
    ctx: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(ctx, attrs, defStyleAttr) {

    private var joinStyle: Paint.Join by Delegates.notNull()

    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = "#FF003432".toColorInt()
        style = Paint.Style.STROKE
        strokeCap = Cap.ROUND
    }

    private val path = Path()

    init {
        context.withStyledAttributes(attrs, R.styleable.TriangleNonClosedPathJoinView) {
            val joinStyleValue = getInt(
                R.styleable.TriangleNonClosedPathJoinView_joinStyle,
                Paint.Join.ROUND.ordinal
            )
            joinStyle = Paint.Join.entries[joinStyleValue]
        }

        linePaint.run {
            strokeJoin = joinStyle
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, linePaint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        linePaint.strokeWidth = min(w, h) * .1f
        path.run {
            /*
                Set the beginning of the next contour to the point (x,y).
             */
            moveTo(w * .5f, h * .2f)
            /*
                Add a line from the last point to the specified point (x,y).
                If NO moveTo() call has been made for this contour,
                the first point is automatically set to (0,0).
             */
            lineTo(w * .8f, h * .8f)
            lineTo(w * .2f, h * .8f)
            lineTo(w * .5f, h * .2f)
        }
    }
}