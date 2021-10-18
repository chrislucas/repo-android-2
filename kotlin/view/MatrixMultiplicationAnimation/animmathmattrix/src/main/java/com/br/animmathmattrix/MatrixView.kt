package com.br.animmathmattrix

import android.content.Context
import android.util.AttributeSet
import android.view.View




class MatrixView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val leftBracket: BracketDrawable = BracketDrawable(this.context)

    val rightBracket: BracketDrawable = BracketDrawable(this.context)
}