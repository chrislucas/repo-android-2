package com.br.funwithdatabinding.view.features.tutorials.medium.screenshotwithpixelcopy

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R

/*
    https://medium.com/@shiveshmehta09/taking-screenshot-programmatically-using-pixelcopy-api-83c84643b02a
    https://developer.android.com/reference/android/view/PixelCopy

    How to use PixelCopy API in android java to get bitmap from View
    https://stackoverflow.com/questions/65828719/how-to-use-pixelcopy-api-in-android-java-to-get-bitmap-from-view
 */
class TakeScreenshotWithPixelCopyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_take_screenshot_with_pixel_copy)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}