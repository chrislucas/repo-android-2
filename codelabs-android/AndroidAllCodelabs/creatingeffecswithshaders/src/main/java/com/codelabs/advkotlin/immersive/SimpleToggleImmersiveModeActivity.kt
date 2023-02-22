package com.codelabs.advkotlin.immersive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.codelabs.advkotlin.canvas.drawing.view.toggleImmersiveMode
import com.codelabs.advkotlin.creatingeffecswithshaders.R
import com.codelabs.advkotlin.creatingeffecswithshaders.databinding.ActivityDrawingCanvasBinding
import com.codelabs.advkotlin.creatingeffecswithshaders.databinding.ActivitySimpleToggleImmersiveModeBinding
import com.codelabs.advkotlin.ext.views.ImmersiveModeCallback

class SimpleToggleImmersiveModeActivity : AppCompatActivity(), ImmersiveModeCallback {

    private val binding: ActivitySimpleToggleImmersiveModeBinding by lazy {
        ActivitySimpleToggleImmersiveModeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_toggle_immersive_mode)
        window.toggleImmersiveMode(binding.root, binding.btnToggleImmersiveMode)
    }

    override fun on() {
        Log.i("IMMERSIVE_MODE_ON", "ON")
    }

    override fun off() {
        Log.i("IMMERSIVE_MODE_OFF", "OFF")
    }
}