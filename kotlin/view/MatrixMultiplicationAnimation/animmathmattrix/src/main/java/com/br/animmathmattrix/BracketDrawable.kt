package com.br.animmathmattrix

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

class BracketDrawable @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    private var mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        mPaint.color = Color.BLUE
    }

    private var mWidth: Int = 0
    private var mHeight: Int = 0

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawLines(
            arrayOf(
                mWidth * .2f,
                mHeight * .2f,
                mWidth * .8f,
                mHeight * .8f
            ).toFloatArray(), mPaint
        )
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
        mPaint.strokeWidth = min(w, h) * .1f
    }

}