package com.br.justcomposelabs.tutorial.google.view.preferences

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.justcomposelabs.databinding.ActivityFontScaleConfigurationBinding
import com.br.justcomposelabs.tutorial.google.view.adjustFontSize
import timber.log.Timber

/**
 *
 *https://medium.com/@nicholas.rose/simple-in-app-text-resizing-f7c3fb8b1a02
 *
 * Tome como inspiracao
 * @see HandlerConfigurationChangesActivity
 */
class FontScaleConfigurationActivity : AppCompatActivity() {
    private val binding: ActivityFontScaleConfigurationBinding by lazy {
        ActivityFontScaleConfigurationBinding.inflate(layoutInflater)
    }

    private var fontScale: Float = 1.0f

    companion object {
        const val SAVE_FONT_SCALE = "save_font_scale"
        const val PREF_FONT_SCALE = "pref_font_scale"
        const val PREFS_NAME = "prefs_name"
        const val TAG_CHANGE_FONT_SCALE = "tag_change_font_scale"
        const val KEY_SAVE_FONT_SCALE_BUNDLE = "save_font_scale_bundle"
        const val TAG_SAVE_FONT_SCALE_BUNDLE = "save_font_scale_bundle"
        const val TAG_RESTORE_FONT_SCALE_BUNDLE = "getFontScale_sharedpref"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fontScale = restoreConfigurationStateFromBundle(savedInstanceState)
            ?: resources?.configuration?.fontScale ?: 1.0f

        syncFontScaleFromPreferences("onCreate")

        enableEdgeToEdge()
        binding.run {
            setContentView(root)
            ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            rangeSliderFontScale.addOnChangeListener { _, value, fromUser ->
                Timber.tag(TAG_CHANGE_FONT_SCALE).d(
                    "Message: [Value: $value, fromUser: $fromUser]",
                )

                fontScale = value
                recreate()
            }

            rangeSliderFontScale.setValues(fontScale)
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        val savedFontScale = getFontScaleFromPreferences(newBase)
        val contextWithFontScale =
            if (savedFontScale != 1.0f) {
                newBase?.adjustFontSize(savedFontScale)
            } else {
                newBase
            }
        super.attachBaseContext(contextWithFontScale)
    }

    override fun onSaveInstanceState(
        outState: Bundle,
        outPersistentState: PersistableBundle,
    ) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putFloat(KEY_SAVE_FONT_SCALE_BUNDLE, fontScale)

        /**
         * ✅ Salvar fontScale em SharedPreferences para que attachBaseContext()
         * possa acessá-lo antes de onCreate() ser chamado.
         */
        val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        prefs.edit { putFloat(PREF_FONT_SCALE, fontScale) }
        Timber.tag(TAG_SAVE_FONT_SCALE_BUNDLE).d(
            "FontScale salvo em SharedPreferences: $fontScale",
        )
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        restoreConfigurationStateFromBundle(savedInstanceState)?.let {
            this@FontScaleConfigurationActivity.fontScale = it
        }

        syncFontScaleFromPreferences("onRestoreInstanceState")
    }

    private fun restoreConfigurationStateFromBundle(bundle: Bundle?): Float? =
        bundle?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getFloat(SAVE_FONT_SCALE, 1.0f)
            } else {
                @Suppress("DEPRECATION")
                it.getFloat(SAVE_FONT_SCALE)
            }
        }

    private fun getFontScaleFromPreferences(context: Context?): Float =
        context
            ?.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
            ?.getFloat(PREF_FONT_SCALE, 1.0f) ?: 1.0f

    private fun syncFontScaleFromPreferences(source: String) {
        val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val savedFontScale = prefs.getFloat(PREF_FONT_SCALE, 1.0f)

        // Sincronizar se o fontScale foi salvo e é diferente
        if (savedFontScale != 1.0f && fontScale != savedFontScale) {
            fontScale = savedFontScale
            Timber.tag(TAG_RESTORE_FONT_SCALE_BUNDLE).d(
                "$source: FontScale restaurado de SharedPreferences: $savedFontScale",
            )
        }
    }
}
