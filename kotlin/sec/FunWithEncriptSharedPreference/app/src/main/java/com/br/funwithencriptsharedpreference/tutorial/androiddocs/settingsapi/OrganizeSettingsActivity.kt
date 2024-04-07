package com.br.funwithencriptsharedpreference.tutorial.androiddocs.settingsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.br.funwithencriptsharedpreference.R

/**
 * https://developer.android.com/develop/ui/views/components/settings/organize-your-settings
 * https://developer.android.com/develop/ui/views/components/settings/organize-your-settings
 */
class OrganizeSettingsActivity : AppCompatActivity() {

    class Settings private constructor(): PreferenceFragmentCompat() {

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

        }

        companion object {
            @JvmStatic
            fun newInstance() = Settings()
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_organize_settings)
    }
}