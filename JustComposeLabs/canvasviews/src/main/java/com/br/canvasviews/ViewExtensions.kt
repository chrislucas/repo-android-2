package com.br.canvasviews

import android.os.Build
import android.util.TypedValue
import android.view.View
import androidx.annotation.RequiresApi

/**
 *
TypedValue.applyDimension(int, float, DisplayMetrics) instead public float scaledDensity;
Deprecated
 *  @see android.util.DisplayMetrics.scaledDensity
 */

// Conversion for SP (Scale-independent Pixels)

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun View.toScalePixel(pixelValue: Float) = TypedValue.convertPixelsToDimension(
    TypedValue.COMPLEX_UNIT_SP,
    pixelValue,
    resources.displayMetrics
)

// Conversion for DP (Density-independent Pixels)

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun View.toDensityPixel(pixelValue: Float) = TypedValue.convertDimensionToPixels(
    TypedValue.COMPLEX_UNIT_DIP,
    pixelValue,
    resources.displayMetrics
)
