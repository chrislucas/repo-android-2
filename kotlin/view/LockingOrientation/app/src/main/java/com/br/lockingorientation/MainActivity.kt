package com.br.lockingorientation

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.br.lockingorientation.databinding.ActivityMainBinding

/**
 *
 * https://stackoverflow.com/questions/4675750/lock-screen-orientation-android
 * https://stackoverflow.com/questions/2366706/how-to-lock-orientation-during-runtime
 */
class MainActivity : AppCompatActivity() {

    private val bindView: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindView.root)
        bindView.btnToggleLockScreenRotation.setOnClickListener {
            val (nextOrientation, description) = when (resources.configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> {
                    (ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT to "SENSOR_PORTRAIT")
                }
                else -> {
                    (ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE to "SENSOR_LANDSCAPE")
                }
            }
            bindView.btnToggleLockScreenRotation.text = description
            requestedOrientation = nextOrientation
        }

        bindView.btnOpenControlRotationActivity.setOnClickListener {
            startActivity(Intent(this, ControlRotationActivity::class.java))
        }
    }
}