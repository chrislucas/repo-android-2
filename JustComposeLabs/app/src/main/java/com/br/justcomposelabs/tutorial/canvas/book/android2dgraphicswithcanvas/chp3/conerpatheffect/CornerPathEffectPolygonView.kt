package com.br.justcomposelabs.tutorial.canvas.book.android2dgraphicswithcanvas.chp3.conerpatheffect

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * https://share.google/aimode/fs5jugMj8t47It3Q1
 * https://share.google/aimode/msHMP3NYCz8koc6iw

 * @param ctx The Context the view is running in, through which it can
 *        access the current theme, resources, etc.
 * @param attrs The attributes of the XML tag that is inflating the view.
 *
 * @param defStyleAttr  An attribute in the current theme that contains a
 *        reference to a style resource that supplies default values for
 *        the view. Can be 0 to not look for defaults.
 */
class CornerPathEffectPolygonView
@JvmOverloads
constructor(
    ctx: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : View(ctx, attrs, defStyleAttr)
