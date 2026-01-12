package com.br.justcomposelabs.tutorial.customview.loadoverlaycustomview

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.core.graphics.toColorInt

class WrapperLoadView(private val parentView: ViewGroup) {

    private var container: FrameLayout?
    private var progressBar: ProgressBar?

    init {
        progressBar = ProgressBar(parentView.context).apply {
            val dimens = (SPINNER_SIZE_DP * context.resources.displayMetrics.density).toInt()

            layoutParams = FrameLayout.LayoutParams(
                dimens,
                dimens,
                Gravity.CENTER
            )

            isIndeterminate = true
            visibility = View.GONE
        }

        container = FrameLayout(parentView.context).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
            setBackgroundColor(DIM_BACKGROUND_COLOR.toColorInt())
            elevation = OVERLAY_ELEVATION
            isClickable = true
            isFocusable = true
            visibility = View.GONE
            addView(progressBar)
        }

        parentView.addView(container)
    }

    fun show() {
        container?.visibility = View.VISIBLE
        progressBar?.visibility = View.VISIBLE
    }

    fun hide() {
        container?.visibility = View.GONE
        progressBar?.visibility = View.GONE
    }

    fun release() {
        container?.removeAllViews()
        (container?.parent as? ViewGroup)?.removeView(container)
        container = null
        progressBar = null
    }

    companion object {
        private const val SPINNER_SIZE_DP = 48
        private const val OVERLAY_ELEVATION = 20f
        private const val DIM_BACKGROUND_COLOR = "#99000000"
    }
}