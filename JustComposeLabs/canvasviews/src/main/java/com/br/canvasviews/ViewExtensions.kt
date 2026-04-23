package com.br.canvasviews

import android.os.Build
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import androidx.annotation.RequiresApi

/**
 *
TypedValue.applyDimension(int, float, DisplayMetrics) instead public float scaledDensity;
Deprecated
 *  @see android.util.DisplayMetrics.scaledDensity
 */


@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
// Conversion for SP (Scale-independent Pixels)

fun View.toScalePixel(pixelValue: Float) = TypedValue.convertPixelsToDimension(
    TypedValue.COMPLEX_UNIT_SP, pixelValue, resources.displayMetrics
)

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
// Conversion for DP (Density-independent Pixels)

fun View.toDensityPixel(pixelValue: Float) = TypedValue.convertDimensionToPixels(
    TypedValue.COMPLEX_UNIT_DIP, pixelValue, resources.displayMetrics
)