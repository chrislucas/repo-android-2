package com.br.justcomposelabs.tutorial.google.view

import android.content.Context
import android.content.res.Configuration
import androidx.annotation.IntDef

/**
 * Ajusta a escala de fonte do contexto.
 *
 * Esta função modifica a configuração do contexto para aplicar a escala de fonte fornecida.
 * A escala é aplicada através da criação de um novo contexto com a configuração modificada.
 *
 * O método é compatível com todas as versões de API modernas e evita o uso de
 * APIs obsoletas como WindowManager.getDefaultDisplay().
 *
 * @param scale Fator de escala para a fonte (ex: 1.0f para tamanho normal)
 * @return Um novo Context com a escala de fonte aplicada
 */
fun Context.adjustFontSize(scale: Float): Context {
    // ✅ CORREÇÃO: Criar uma NOVA Configuration em vez de modificar a existente
    val newConfiguration =
        Configuration(resources.configuration).apply {
            fontScale = scale
        }
    return createConfigurationContext(newConfiguration)
}

@IntDef(
    value = [
        Configuration.ORIENTATION_UNDEFINED,
        Configuration.ORIENTATION_PORTRAIT,
        Configuration.ORIENTATION_LANDSCAPE,
    ],
)
@Retention(AnnotationRetention.SOURCE)
annotation class Orientation

fun Context.adjustOrientation(
    @Orientation orientation: Int,
): Context {
    // ✅ CORREÇÃO: Criar uma NOVA Configuration em vez de modificar a existente
    val newConfiguration =
        Configuration(resources.configuration).apply {
            this.orientation = orientation
        }
    return createConfigurationContext(newConfiguration)
}

/*
    https://gist.github.com/hitesh-dhamshaniya/ba939fae5ebd22193a0f1125a54eea9a
 */
fun Context.adjustScreenSize(screenSize: Int): Context {
    // ✅ CORREÇÃO: Criar uma NOVA Configuration em vez de modificar a existente
    val newConfiguration =
        Configuration(resources.configuration).apply {
            screenLayout = screenSize
        }
    return createConfigurationContext(newConfiguration)
}

@IntDef(
    value = [
        Configuration.KEYBOARD_UNDEFINED,
        Configuration.KEYBOARDHIDDEN_YES,
        Configuration.KEYBOARDHIDDEN_NO,
    ],
)
@Retention(AnnotationRetention.SOURCE)
annotation class KeyboardHiddenState

fun Context.adjustKeyboardHiddenState(
    @KeyboardHiddenState keyboardHiddenState: Int,
): Context {
    // ✅ CORREÇÃO: Criar uma NOVA Configuration em vez de modificar a existente
    val newConfiguration =
        Configuration(resources.configuration).apply {
            keyboardHidden = keyboardHiddenState
        }
    return createConfigurationContext(newConfiguration)
}
