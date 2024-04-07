package com.br.funwithencriptsharedpreference.tutorial.androiddocs.settingsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.br.funwithencriptsharedpreference.R
import com.br.funwithencriptsharedpreference.databinding.ActivitySettingsBinding

/**
 * https://developer.android.com/training/data-storage/shared-preferences?hl=pt-br
 *
 * Settings
 * https://developer.android.com/develop/ui/views/components/settings
 * https://developer.android.com/guide/topics/ui/settings?hl=pt-br
 */
class SettingsActivity : AppCompatActivity() {

    /**
     * PreferenceFragmentCompat
     * https://developer.android.com/reference/androidx/preference/PreferenceFragmentCompat
     *
     * Esse Fragment disponibiliza um objeto de preferencias de forma hierarquica para o usuario
     */
     class SettingsFragment private constructor(): PreferenceFragmentCompat() {

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            /*
                TODO estudar a diferenca set e add
                addPreferencesFromResource(R.xml.preferences)
             */
            setPreferencesFromResource(R.xml.preferences, rootKey)
        }

        companion object {
            @JvmStatic
            fun newInstance() = SettingsFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportFragmentManager.beginTransaction()
            .replace(R.id.settings_container, SettingsFragment.newInstance())
            .commit()
    }
}