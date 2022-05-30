package com.br.funwithlifecyclerawareness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * https://developer.android.com/topic/libraries/architecture/lifecycle
 */
class FunLifecyclerAwarenessMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fun_lifecycler_awareness_main)
    }
}