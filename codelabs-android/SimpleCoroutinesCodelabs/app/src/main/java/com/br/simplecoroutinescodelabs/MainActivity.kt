package com.br.simplecoroutinescodelabs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.br.simplecoroutinescodelabs.databinding.ActivityMainBinding
import com.br.start.view.activities.ModuleStartMainActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.rootView)

        binding.btOpenStartModule.setOnClickListener {
            startActivity(Intent(this, ModuleStartMainActivity::class.java))
        }
    }
}