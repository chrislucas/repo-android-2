package com.br.start.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.br.start.R
import com.br.start.databinding.ActivityModuleStartMainBinding

class ModuleStartMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityModuleStartMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModuleStartMainBinding.inflate(layoutInflater)
        setContentView(binding.viewRoot)
    }
}