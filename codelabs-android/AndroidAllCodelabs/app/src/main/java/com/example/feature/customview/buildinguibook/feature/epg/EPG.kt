package com.example.feature.customview.buildinguibook.feature.epg

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.os.SystemClock
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.example.androidallcodelabs.R
import java.util.Calendar

/**
 * EPG (eletronic programming guide)
 *
 * Basics
 *
 * - View background
 * - EPG body com todos os canais e programas de tv
 * - uma barra de ferramentas com o hora atual
 *
 * https://github.com/PacktPublishing/Building-Android-UIs-with-Custom-Views/blob/master/Chapter09/Example33-EPG/app/src/main/java/com/rrafols/packt/epg/EPG.java
 */
class EPG @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        private const val BACKGROUND_COLOR = 1
        private const val HEX_255 = 0xff
        private const val TIME_THRESHOLD = 0
    }

    private val initialTimeValue = System.currentTimeMillis() - 30 * 60 * 1000

    private val scrollXY: Pair<Float, Float> = Pair(0f, 0f)
    private var frChaNameWidth = 0.0f
    private var chNameWidth = 0.0f
    private var chNameWidthTarget = 0.0f

    private val scrollTargetXY: Pair<Float, Float> = Pair(0f, 0f)

    private lateinit var frScrollXY: Pair<Float, Float>

    private var accTime = 0L

    private var timeStart = SystemClock.elapsedRealtime()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val currentTime = System.currentTimeMillis()
        drawBackground(canvas)
        drawEPGBody(canvas, currentTime,0)
        drawTimeBar(canvas, currentTime)
        drawCurrentTime(canvas, currentTime)

        if (missingAnimations()) {
            invalidate()
        }
    }

    private fun startAnimation() {
        val currentTime = SystemClock.elapsedRealtime()
        accTime += currentTime - timeStart
        timeStart = currentTime

        var (scrollX, scrollY) = scrollXY
        val (scrollTargetX, scrollTargetY) = scrollTargetXY


        while (accTime > TIME_THRESHOLD) {
            scrollX += (scrollTargetX - scrollX) / 4.0f
            scrollY += (scrollTargetY - scrollY) / 4.0f
        }


        val factor = accTime.toFloat() / TIME_THRESHOLD
        val nextScrollX = scrollX + (scrollTargetX - scrollX) / 4.0f
        val nextScrollY = scrollY + (scrollTargetY - scrollY) / 4.0f
        val nextChNameWidth = chNameWidth + (chNameWidthTarget - chNameWidth) / 4.0f



        frScrollXY = Pair(
            scrollX * (1.0f - factor) + nextScrollX * factor,
            scrollY * (1.0f - factor) + nextScrollY * factor
        )
    }

    private fun drawBackground(canvas: Canvas) {
        canvas.drawARGB(
            BACKGROUND_COLOR shr 24,
            (BACKGROUND_COLOR shr 16) and HEX_255,
            (BACKGROUND_COLOR shr 8) and HEX_255,
            BACKGROUND_COLOR and HEX_255
        )
    }

    private fun drawEPGBody(canvas: Canvas, currentTime: Long, scrollY: Int) {

    }

    private fun getTimeHorizontalPosition(time: Long): Long = 0L

    private fun drawTimeBar(canvas: Canvas, currentTime: Long) {
        /**
         *
         */

        with(Calendar.getInstance()) {
            timeInMillis = initialTimeValue - 120 * 60 * 1000
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)

            val time = timeInMillis
            //val x = getTimeHorizontalPosition()
        }
    }

    private fun drawCurrentTime(canvas: Canvas, currentTime: Long) {

    }

    private fun missingAnimations(): Boolean {
        return false
    }
}