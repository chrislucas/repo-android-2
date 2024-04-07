package com.br.funwithencriptsharedpreference.tutorial.androiddocs.settingsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceCategory
import androidx.preference.PreferenceFragmentCompat
import com.br.funwithencriptsharedpreference.R

class SettingsIIIActivity : AppCompatActivity() {

    class PreferenceSettings private constructor() : PreferenceFragmentCompat() {

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

            /**
             * https://developer.android.com/reference/androidx/preference/PreferenceFragmentCompat
             * Leia como criar uma PreferenceScreen sem usar XML
             */
            activity?.run {
                    val context = this
                    with(preferenceManager.createPreferenceScreen(context)) {
                        addPreference(
                            PreferenceCategory(context)
                        )
                        setOnPreferenceClickListener { false }
                        // preferenceScreen.onPreferenceClickListener = Preference.OnPreferenceClickListener { false }
                    }
                }
        }

        companion object {
            @JvmStatic
            fun newInstance() = PreferenceSettings()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_iiiactivity)
    }
}