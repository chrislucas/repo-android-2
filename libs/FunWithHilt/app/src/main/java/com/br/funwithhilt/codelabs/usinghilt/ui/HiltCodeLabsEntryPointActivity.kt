package com.br.funwithhilt.codelabs.usinghilt.ui

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.br.funwithhilt.R
import com.br.funwithhilt.databinding.ActivityHiltCodeLabsEntryPointBinding

class HiltCodeLabsEntryPointActivity : AppCompatActivity() {

    private val binding: ActivityHiltCodeLabsEntryPointBinding by lazy {
        ActivityHiltCodeLabsEntryPointBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}