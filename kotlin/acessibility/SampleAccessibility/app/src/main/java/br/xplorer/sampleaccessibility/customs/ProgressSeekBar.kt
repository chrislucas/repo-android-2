package br.xplorer.sampleaccessibility.customs

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.SeekBar

class ProgressSeekBar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null
                                                , defStyleAttr: Int = 0) : SeekBar(context, attrs, defStyleAttr) {
    private var paint : Paint = Paint()

    init {
        paint.apply {
            color = Color.BLACK
            textSize  = 20.0f
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val thumbX = (1.0 * progress / max  * 1.0 * width).toFloat()
        canvas?.drawText("($progress)%", thumbX, height * 1.0f, paint)
    }

}