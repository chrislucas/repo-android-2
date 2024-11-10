package com.br.funwithdatabinding.view.features.tutorials.google.dynamicspandrawable

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.SpannableString
import android.text.Spanned
import android.text.style.DynamicDrawableSpan
import android.text.style.DynamicDrawableSpan.ALIGN_BASELINE
import android.text.style.ImageSpan
import android.text.style.ReplacementSpan
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources


@RequiresApi(Build.VERSION_CODES.Q)
class CustomDynamicDrawableSpan(
    private val context: Context,
    private val resource: Int,
    private val alignmentType: Int = ALIGN_BASELINE
) : DynamicDrawableSpan(alignmentType) {

    override fun getDrawable(): Drawable? {
        val drawable = AppCompatResources.getDrawable(context, resource)
        return drawable?.apply {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
        }
    }

    override fun getVerticalAlignment(): Int {
        return alignmentType
    }
}

/*
    https://developer.android.com/reference/android/text/style/ImageSpan
 */
class CenteredImageSpan(private val drawable: Drawable) : ImageSpan(drawable) {
    override fun getDrawable(): Drawable {
        return drawable.apply {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
        }
    }

    override fun draw(
        canvas: Canvas, text: CharSequence?, start: Int, end: Int,
        x: Float,
        top: Int,
        y: Int,
        bottom: Int,
        paint: Paint
    ) {
        canvas.save()
        val transY = top + (bottom - top) / 2 - drawable.bounds.height() / 2
        canvas.translate(x, transY.toFloat())
        drawable.draw(canvas)
        canvas.restore()
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
fun Context.toSpannableString(
    content: CharSequence,
    resource: Int,
    start: Int,
    end: Int,
    flag: Int = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE,
    alignmentType: Int = ALIGN_BASELINE
): SpannableString {
    return SpannableString(content).apply {
        setSpan(
            CustomDynamicDrawableSpan(this@toSpannableString, resource, alignmentType),
            start,
            end,
            flag
        )
    }
}


fun toSpannableString(
    content: CharSequence,
    span: ReplacementSpan,
    start: Int,
    end: Int,
    flag: Int = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE,
): SpannableString {
    return SpannableString(content).apply { setSpan(span, start, end, flag) }
}