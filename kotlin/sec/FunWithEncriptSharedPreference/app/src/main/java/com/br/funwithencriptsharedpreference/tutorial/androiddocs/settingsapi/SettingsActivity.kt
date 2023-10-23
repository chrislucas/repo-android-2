package com.br.funwithencriptsharedpreference.tutorial.androiddocs.settingsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.br.funwithencriptsharedpreference.R

/**
 * https://developer.android.com/training/data-storage/shared-preferences?hl=pt-br
 *
 * Settings
 * https://developer.android.com/develop/ui/views/components/settings
 */
class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }
}