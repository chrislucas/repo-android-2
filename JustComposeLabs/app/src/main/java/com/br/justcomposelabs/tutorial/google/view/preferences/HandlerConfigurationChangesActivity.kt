package com.br.justcomposelabs.tutorial.google.view.preferences

import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.br.justcomposelabs.R
import com.br.justcomposelabs.databinding.ActivityHandlerConfigurationChangesBinding
import com.br.justcomposelabs.tutorial.google.view.Orientation
import com.br.justcomposelabs.tutorial.google.view.adjustFontSize
import com.br.justcomposelabs.tutorial.google.view.handleconfigurationchanges.ConfigurationDeviceInfoComponent
import com.br.justcomposelabs.ui.theme.JustComposeLabsTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import timber.log.Timber

class HandlerConfigurationChangesActivity : AppCompatActivity() {

    /*
        Criar um layout que seja possível mudar em tempo de execução
            - escala da fonte
            - orientação da tela
            - e demais configurações

        - Como manipular mudanças de configuração em tempo de execução?
            - Podemos chamar o metodo Activity.recreate() depois de mudar
            uma determinada configuracao. Isso vai reiniciar a activity e
            o metodo attachBaseContext(newBase: Context?) será chamado


         RangeSlider material design
         https://developer.android.com/reference/com/google/android/material/slider/RangeSlider
     */

    // Estado persistente que é restaurado do Bundle antes de attachBaseContext
    private var initConfigurationState: ConfigurationState? = null

    private val viewModel: ConfigurationViewModel by lazy {
        initConfigurationState = if (initConfigurationState == null) {
            resources?.run {
                ConfigurationState(
                    fontScale = configuration.fontScale,
                    orientation = configuration.orientation
                )
            }
        } else {
            initConfigurationState
        }

        ConfigurationViewModel(initConfigurationState!!)
    }

    private val binding: ActivityHandlerConfigurationChangesBinding by lazy {
        ActivityHandlerConfigurationChangesBinding.inflate(layoutInflater)
    }


    companion object {
        private const val TAG = "HandlerConfigChanges"
        private const val SAVE_CONFIG_STATE = "saveConfigState"
        private const val TAG_CHANGE_FONT_SCALE = "ChangeFontScale"
        private const val PREFS_NAME = "configuration_prefs"
        private const val PREF_FONT_SCALE = "font_scale"
    }

    /**
     * ✅ Método privado para recuperar e sincronizar fontScale de SharedPreferences
     * Elimina duplicação de código em onCreate(), onRestoreInstanceState() e attachBaseContext()
     * 
     * @param source Origem da chamada (para logging)
     */
    private fun syncFontScaleFromPreferences(source: String = "syncFontScale") {
        val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val savedFontScale = prefs.getFloat(PREF_FONT_SCALE, 1.0f)
        
        // Sincronizar se o fontScale foi salvo e é diferente
        if (savedFontScale != 1.0f && initConfigurationState?.fontScale != savedFontScale) {
            initConfigurationState = initConfigurationState?.copy(fontScale = savedFontScale)
            Timber.tag(TAG).d("$source: FontScale restaurado de SharedPreferences: $savedFontScale")
        }
    }

    /**
     * ✅ Método privado para recuperar fontScale de SharedPreferences
     * Usado em attachBaseContext() para aplicar a escala ao contexto
     */
    private fun getFontScaleFromPreferences(context: Context?): Float {
        return context?.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
            ?.getFloat(PREF_FONT_SCALE, 1.0f) ?: 1.0f
    }

    /**
     * ✅ Método privado para restaurar ConfigurationState do Bundle
     * Elimina duplicação entre onCreate() e onRestoreInstanceState()
     */
    private fun restoreConfigurationStateFromBundle(bundle: Bundle?): ConfigurationState? {
        return bundle?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getParcelable(SAVE_CONFIG_STATE, ConfigurationState::class.java)
            } else {
                @Suppress("DEPRECATION")
                it.getParcelable(SAVE_CONFIG_STATE)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        /**
         * ✅ Restaurar ConfigurationState do Bundle usando método privado
         * Se não houver Bundle, usar valores padrão do resources
         */
        initConfigurationState = restoreConfigurationStateFromBundle(savedInstanceState)
            ?: resources?.run {
                ConfigurationState(
                    fontScale = configuration.fontScale,
                    orientation = configuration.orientation
                )
            }

        // ✅ Sincronizar fontScale de SharedPreferences usando método privado
        syncFontScaleFromPreferences("onCreate")

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        /**
         * ✅ Agora que o estado foi restaurado, aplicar as configurações.
         * A escala de fonte será reaplicada quando recreate() for chamado,
         * pois adjustFontSize será chamado novamente em attachBaseContext durante a recriação.
         */
        initConfigurationState?.let { state ->
            // Aplicar orientação da tela
            requestedOrientation = if (state.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            } else {
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
        }

        binding.run {
            setContentView(root)
            ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.configurationState.collect { configurationState ->
                        if (initConfigurationState != configurationState) {
                            Timber.tag(TAG).d("Configuration changed: [$configurationState], recreating")
                            initConfigurationState = configurationState
                            recreate()
                        }
                    }
                }
            }

            rangeSliderFontScale.addOnChangeListener { _, value, fromUser ->
                Timber.tag(TAG_CHANGE_FONT_SCALE).d(
                    "Message: [Value: $value, fromUser: $fromUser]"
                )
                viewModel.updateFontScale(value)
            }

            // Sincroniza o estado visual com o estado atual
            initConfigurationState?.let { state ->
                if (state.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    toggleButtonHandleOrientationScreenLabel.setText(
                        R.string.toggle_button_handle_orientation_screen_landscape
                    )
                    toggleButtonHandleOrientationScreen.isChecked = true
                } else {
                    toggleButtonHandleOrientationScreenLabel.setText(
                        R.string.toggle_button_handle_orientation_screen_portrait
                    )
                    toggleButtonHandleOrientationScreen.isChecked = false
                }

                // Sincroniza o slider com o valor atual da escala de fonte
                rangeSliderFontScale.setValues(state.fontScale)
            }

            toggleButtonHandleOrientationScreen.setOnClickListener {
                val orientation = if (toggleButtonHandleOrientationScreen.isChecked) {
                    toggleButtonHandleOrientationScreenLabel.setText(
                        R.string.toggle_button_handle_orientation_screen_landscape
                    )
                    Configuration.ORIENTATION_LANDSCAPE
                } else {
                    toggleButtonHandleOrientationScreenLabel.setText(
                        R.string.toggle_button_handle_orientation_screen_portrait
                    )
                    Configuration.ORIENTATION_PORTRAIT
                }
                viewModel.updateOrientationScreen(orientation)
            }

            val stepSize = rangeSliderFontScale.stepSize
            val valueTo = rangeSliderFontScale.valueTo
            val valueFrom = rangeSliderFontScale.valueFrom

            buttonIncreaseFontScale.setOnClickListener {
                val currentValues = rangeSliderFontScale.values
                val newStartValue = (currentValues[0] + stepSize).coerceAtMost(valueTo)
                rangeSliderFontScale.setValues(newStartValue)
            }

            buttonDecreaseFontScale.setOnClickListener {
                val currentValues = rangeSliderFontScale.values
                val newStartValue = (currentValues[0] - stepSize).coerceAtLeast(valueFrom)
                rangeSliderFontScale.setValues(newStartValue)
            }

            containerDeviceConfigInfo.setContent {
                JustComposeLabsTheme {
                    ConfigurationDeviceInfoComponent()
                }
            }
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        /**
         * ✅ SOLUÇÃO: Ler fontScale de SharedPreferences que foi salvo em onSaveInstanceState()
         * 
         * Fluxo:
         * 1. User move slider
         * 2. viewModel.updateFontScale(value)
         * 3. Observer chama recreate()
         * 4. onSaveInstanceState() salva fontScale em SharedPreferences ✅
         * 5. attachBaseContext() LEITURA de SharedPreferences ✅
         * 6. adjustFontSize() aplica a escala ✅
         * 7. onCreate() restaura estado e sincroniza UI ✅
         */
        val savedFontScale = getFontScaleFromPreferences(newBase)
        
        Timber.tag(TAG).d("attachBaseContext: FontScale lido = $savedFontScale")
        
        // ✅ Aplicar fontScale ao contexto
        val contextWithFontScale = if (savedFontScale != 1.0f) {
            newBase?.adjustFontSize(savedFontScale)
        } else {
            newBase
        }
        super.attachBaseContext(contextWithFontScale)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(SAVE_CONFIG_STATE, initConfigurationState)
        
        /**
         * ✅ Salvar fontScale em SharedPreferences para que attachBaseContext()
         * possa acessá-lo antes de onCreate() ser chamado.
         */
        initConfigurationState?.let { state ->
            val prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
            prefs.edit {
                putFloat(PREF_FONT_SCALE, state.fontScale)
            }
            Timber.tag(TAG).d("FontScale salvo em SharedPreferences: ${state.fontScale}")
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        
        /**
         * ✅ Restaurar estado de duas fontes para garantir consistência:
         * 1. Bundle: Para dados gerais de configuração (usando método privado)
         * 2. SharedPreferences: Para fontScale (sincronizar após restauração)
         */
        restoreConfigurationStateFromBundle(savedInstanceState)?.let {
            initConfigurationState = it
        }
        
        // ✅ Sincronizar fontScale de SharedPreferences usando método privado
        syncFontScaleFromPreferences("onRestoreInstanceState")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Timber.tag("CONFIG_CHANGE").i("Message: $newConfig")
    }
}


@Parcelize
data class ConfigurationState(
    val fontScale: Float, @param:Orientation val orientation: Int
) : Parcelable

class ConfigurationViewModel(initConfigurationState: ConfigurationState) : ViewModel() {

    private val mutableConfigurationState = MutableStateFlow(initConfigurationState)

    val configurationState: StateFlow<ConfigurationState> = mutableConfigurationState.asStateFlow()

    fun updateFontScale(fontScale: Float) {
        mutableConfigurationState.value =
            mutableConfigurationState.value.copy(fontScale = fontScale)
    }

    fun updateOrientationScreen(@Orientation orientation: Int) {
        mutableConfigurationState.value =
            mutableConfigurationState.value.copy(orientation = orientation)
    }
}