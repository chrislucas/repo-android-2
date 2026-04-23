package com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

fun Canvas.drawTextOnScreenMiddle(text: String, paint: Paint) {
    val middleX = width / 2f
    val middleY = height / 2f

    val paintAlignCenter = paint.apply {
        textAlign = Paint.Align.CENTER
        isAntiAlias = true
    }

    val fontMetrics = paint.fontMetrics
    val textHeight = fontMetrics.descent - fontMetrics.ascent
    val adjustedMiddleY = textHeight / 2 - fontMetrics.descent

    drawText(text, middleX, middleY + adjustedMiddleY, paintAlignCenter)
}


fun Canvas.drawTextOnScreenMiddleWithTextBounds(text: String, paint: Paint) {
    val textBounds = android.graphics.Rect()
    paint.getTextBounds(text, 0, text.length, textBounds)
    val textWidth = textBounds.width()
    val textHeight = textBounds.height()

    val middleX = (width - textWidth) / 2f - textBounds.left
    val middleY = (height + textHeight) / 2f - textBounds.bottom

    drawText(text, middleX, middleY, paint)
}

fun Canvas.drawTextOnScreenMiddle(text: String, paint: Paint, showTextWidth: Boolean) {
    val textWidth = paint.measureText(text)
    val middleX = (width - textWidth) / 2
    val middleY = (height / 2 - (paint.descent() + paint.ascent()) / 2)
    drawText(text, middleX, middleY, paint)
    if (showTextWidth) {
        val textWidthPaint = Paint().apply {
            color = Color.DKGRAY
            textSize = 40f
            isAntiAlias = true
        }
        val textWidthInfo = "Text width: ${textWidth.toInt()}px"
        val textWidthInfoX = (width - textWidthPaint.measureText(textWidthInfo)) / 2
        val textWidthInfoY = middleY + paint.textSize + 10f // Position below the main text
        drawText(textWidthInfo, textWidthInfoX, textWidthInfoY, textWidthPaint)
    }
}