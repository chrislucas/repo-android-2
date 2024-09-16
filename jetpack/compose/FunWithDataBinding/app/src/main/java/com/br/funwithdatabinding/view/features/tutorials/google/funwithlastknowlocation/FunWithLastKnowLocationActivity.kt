package com.br.funwithdatabinding.view.features.tutorials.google.funwithlastknowlocation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R

/**
 * Build location-aware apps
 * https://developer.android.com/develop/sensors-and-location/location?authuser=1
 *
 * Request location permissions
 * https://developer.android.com/develop/sensors-and-location/location/permissions?authuser=1
 *
 *
 * https://developer.android.com/develop/sensors-and-location/location/retrieve-current
 */
class FunWithLastKnowLocationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fun_with_last_know_location)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}