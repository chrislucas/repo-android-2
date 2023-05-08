package com.example.feature.android.api.v33.shaders

import android.graphics.Canvas
import android.graphics.RenderNode
import android.os.Build

/**
 * https://developer.android.com/reference/android/graphics/RenderNode
 *
 * Tutorial
 * https://medium.com/androiddevelopers/rendernode-for-bigger-better-blurs-ced9f108c7e2
 */

fun render(canvas: Canvas) {
    val renderNode: RenderNode? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        RenderNode("simpleRenderNode")
    } else {
        null
    }
}