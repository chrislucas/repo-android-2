package com.br.funwithdatabinding.view.features.tutorials.google.fragments.createsimplefragment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithdatabinding.R

/*
    https://developer.android.com/guide/fragments/create
    about fragment
    https://developer.android.com/guide/fragments

    Videos
    Single Activity: Why, when, and how (Android Dev Summit '18)
        https://www.youtube.com/watch?v=2k8x8V77CrU
    Fragments: Past, present, and future (Android Dev Summit '19)
        https://www.youtube.com/watch?v=RS1IACnZLy4
 */
class CreateSimpleFragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_simple_fragment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}