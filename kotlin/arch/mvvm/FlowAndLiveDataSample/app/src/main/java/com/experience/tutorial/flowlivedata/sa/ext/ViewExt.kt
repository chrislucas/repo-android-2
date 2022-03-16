package com.experience.tutorial.flowlivedata.sa.ext

import android.view.View
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.experience.tutorial.R
import com.google.android.material.snackbar.Snackbar


fun View.displaySnackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG)
        .withBackground(ContextCompat.getColor(context, R.color.red_CA0000))
        .setTextColor(ContextCompat.getColor(context, R.color.black))
}

fun Snackbar.withBackground(@ColorInt color: Int): Snackbar =
    this.apply { view.setBackgroundColor(color) }

