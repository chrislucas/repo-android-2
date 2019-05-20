package br.xplorer.xplorerheaderanimation

import android.graphics.Bitmap

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView

object BitmapHelper {

    fun fromDrawableToBitmap(drawable: Drawable) : Bitmap {

        return if (drawable is BitmapDrawable) {
            drawable.bitmap
        }

        else {
            val width = drawable.intrinsicWidth
            val height = drawable.intrinsicHeight
            val bitmap : Bitmap
            bitmap = if(width <= 0 || height <= 0) {
                Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
            } else {
                Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            }
            if (!bitmap.isRecycled) {
                val canvas = Canvas(bitmap)
                drawable.setBounds(0, 0, canvas.width, canvas.height)
                drawable.draw(canvas)
            }
            bitmap
        }
    }

    fun fromImageViewToBitmap(imageView: ImageView) : Bitmap {
        return if(imageView.drawable != null) {
            fromDrawableToBitmap(imageView.drawable)
        }
        else {
            val width   = imageView.width
            val height  = imageView.height
            val bitmap  = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val canvas  = Canvas(bitmap)
            val background = imageView.background
            if(background != null)
                background.draw(canvas)
            else
                canvas.drawColor(Color.WHITE)
            imageView.draw(canvas)
            bitmap
        }
    }

}