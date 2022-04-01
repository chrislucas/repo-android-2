package com.br.labslifecycleawareness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * codelabs
 * https://developer.android.com/codelabs/android-lifecycles?index#0
 */
class MainActivityLifeCycleAwareness : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_life_cycle_awareness)
    }
}