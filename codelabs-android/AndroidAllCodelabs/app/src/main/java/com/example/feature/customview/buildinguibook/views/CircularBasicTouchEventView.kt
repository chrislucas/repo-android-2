package com.example.feature.customview.buildinguibook.views

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View


/*
    Livro Building android UI with custom views
    Cap 3 Basic event handling
 */

class CircularBasicTouchEventView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        event?.let {
            Log.i("TOUCH_EVENT", "$it")
        }

        return super.onTouchEvent(event)
    }
}