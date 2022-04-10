package com.br.lockingorientation

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.Surface
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.br.lockingorientation.databinding.ActivityControlRotationActivtyBinding

class ControlRotationActivity : AppCompatActivity() {

    private val bindView: ActivityControlRotationActivtyBinding by lazy {
        ActivityControlRotationActivtyBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindView.root)
        checkOrientation()
    }

    private fun checkOrientation() {
        val rotation = (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.rotation
        val (orientation, textAngle) = when (rotation) {
            Surface.ROTATION_0 -> {
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT to "Ângulo: 0"
            }

            Surface.ROTATION_90 -> {
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE to "Ângulo: 90"
            }

            Surface.ROTATION_180 -> {
                ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT to "Ângulo: 180"
            }
            else -> {
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE to "Ângulo: 270"
            }
        }
        requestedOrientation = orientation
        bindView.tvLabelRotation.text = textAngle
    }
}