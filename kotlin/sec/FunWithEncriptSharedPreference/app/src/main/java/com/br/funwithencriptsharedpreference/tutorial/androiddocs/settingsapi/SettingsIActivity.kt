package com.br.funwithencriptsharedpreference.tutorial.androiddocs.settingsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.br.funwithencriptsharedpreference.R

/**
 * PreferenceFragmentCompat
 * https://developer.android.com/reference/androidx/preference/PreferenceFragmentCompat
 *
 * - PreferenceFragmentCompat eh a ponto de entrada para usar a biblioteca de Preferences.
 *  - Esse Fragment mostra a hierarquia de Preference e tambmem ldida com valores persistentes no dispositivo
 *  - Podemos definir a hierarquia de Preferences atraves de um arquivo .xml
 *
 *
 */

class SettingsIActivity : AppCompatActivity() {

    class Settings private constructor() : PreferenceFragmentCompat() {

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            addPreferencesFromResource(R.xml.preferences_i)
        }

        companion object {
            @JvmStatic
            fun newInstance() = Settings()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_iactivity)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_i, Settings.newInstance())
            .commit()
    }



}