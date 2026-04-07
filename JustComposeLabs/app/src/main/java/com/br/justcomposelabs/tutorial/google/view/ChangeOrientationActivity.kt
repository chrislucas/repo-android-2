package com.br.justcomposelabs.tutorial.google.view

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.justcomposelabs.databinding.ActivityChangeOrientationBinding
import timber.log.Timber

class ChangeOrientationActivity : AppCompatActivity() {

    private val binding: ActivityChangeOrientationBinding by lazy {
        ActivityChangeOrientationBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding.run {
            setContentView(root)

            ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            toggleButtonHandleOrientation.setOnClickListener {
                requestedOrientation = if (toggleButtonHandleOrientation.isChecked) {
                    android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                } else {
                    android.content.pm.ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                }

                Timber.tag("change_orientation")
                    .d(getOrientationName(resources.configuration.orientation))
            }
        }
    }


    private fun getOrientationName(@Orientation orientation: Int): String =
        when (orientation) {
            android.content.res.Configuration.ORIENTATION_LANDSCAPE -> return "LANDSCAPE"
            android.content.res.Configuration.ORIENTATION_PORTRAIT -> return "PORTRAIT"
            else -> return "UNDEFINED"
        }

}