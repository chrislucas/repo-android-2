package com.br.justcomposelabs.tutorial.google.acessibility.guide

import android.content.Context
import android.util.AttributeSet
import android.view.View


/*
    https://developer.android.com/guide/topics/ui/accessibility/custom-views#custom-touch-events
 */
class CustomTouchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
}