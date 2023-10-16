package com.br.todoappcompose.features.basics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.br.todoappcompose.R

class BasicComposeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_compose)
    }
}