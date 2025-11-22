package com.br.justcomposelabs.tutorial.views.numberaddremoveanimtion

import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.view.animation.AccelerateDecelerateInterpolator

fun countAnimator(start: Float, end: Float, duration: Long = 1000L) =
    ValueAnimator.ofFloat(start, end).apply {
        setDuration(duration)
        withInterpolator()
        withDuration()
    }

fun ValueAnimator.withInterpolator(interpolator: TimeInterpolator = AccelerateDecelerateInterpolator())  = apply {
    this.interpolator =interpolator
}

fun ValueAnimator.withDuration(duration: Long = 1000L) = apply {
    this.duration = duration
}