package com.br.newintentandroid13

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.br.newintentandroid13.databinding.ActivityMainBinding

/*
    https://medium.com/androiddevelopers/making-sense-of-intent-filters-in-android-13-8f6656903dde
    https://developer.android.com/about/versions/13/behavior-changes-13
 */


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding.btnCheckIntentBehavior.setOnClickListener {
            startActivity(Intent("one_action_1"))
        }
    }
}