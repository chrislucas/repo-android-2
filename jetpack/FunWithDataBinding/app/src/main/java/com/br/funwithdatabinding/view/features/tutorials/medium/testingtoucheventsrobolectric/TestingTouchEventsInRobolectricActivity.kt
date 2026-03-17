package com.br.funwithdatabinding.view.features.tutorials.medium.testingtoucheventsrobolectric

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R

/*
    TODO
    https://bterczynski.medium.com/testing-touch-events-in-robolectric-75f5faaa0d1f
    https://bterczynski.medium.com/how-to-test-imageview-color-filters-in-robolectric-475d55a624a6
    Shadows
    https://robolectric.org/extending/

    
 */
class TestingTouchEventsInRobolectricActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_testing_touch_events_in_robolectric)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}