package com.br.justcomposelabs.utils

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding

/**
 * Aplica insets de janela (Window Insets) como padding na View.
 *
 * Este método é essencial ao usar `enableEdgeToEdge()` na Activity, pois garante que o conteúdo
 * da View Root não fique escondido sob as barras de sistema (Status Bar, Navigation Bar)
 * ou recortes de tela (Display Cutouts/Notch), além de reagir à abertura do teclado (IME).
 *
 * @param applyLeft Se deve aplicar padding à esquerda baseado nos insets.
 * @param applyTop Se deve aplicar padding no topo (ex: para a Status Bar/Toolbar).
 * @param applyRight Se deve aplicar padding à direita baseado nos insets.
 * @param applyBottom Se deve aplicar padding na base (ex: para a Navigation Bar/BottomBar/Teclado).
 * @param consume Se true, consome os insets, impedindo que views filhas os recebam. Default: false.
 */
fun View.applyWindowInsets(
    applyLeft: Boolean = true,
    applyTop: Boolean = true,
    applyRight: Boolean = true,
    applyBottom: Boolean = true,
    consume: Boolean = false
) {
    // 1. Capturamos o padding inicial da View.
    // Isso é crucial para que, se os insets mudarem (ex: teclado abre/fecha),
    // o cálculo do novo padding sempre parta do valor original definido no XML/código,
    // evitando o efeito de "padding acumulado" (padding que cresce infinitamente).
    val initialPaddingLeft = paddingLeft
    val initialPaddingTop = paddingTop
    val initialPaddingRight = paddingRight
    val initialPaddingBottom = paddingBottom

    ViewCompat.setOnApplyWindowInsetsListener(this) { v, windowInsets ->
        // 2. Definimos quais tipos de insets queremos observar.
        // systemBars(): Barras de status e navegação.
        // ime(): Teclado virtual.
        // displayCutout(): "Notch" ou recortes de câmera na tela.
        val types = WindowInsetsCompat.Type.systemBars() or
            WindowInsetsCompat.Type.ime() or
            WindowInsetsCompat.Type.displayCutout()

        val insets = windowInsets.getInsets(types)

        // 3. Atualizamos o padding da View somando o valor inicial aos insets recebidos.
        // Usamos if/else para respeitar os parâmetros de configuração da função.
        v.updatePadding(
            left = if (applyLeft) initialPaddingLeft + insets.left else initialPaddingLeft,
            top = if (applyTop) initialPaddingTop + insets.top else initialPaddingTop,
            right = if (applyRight) initialPaddingRight + insets.right else initialPaddingRight,
            bottom = if (applyBottom) initialPaddingBottom + insets.bottom else initialPaddingBottom
        )

        // 4. Decidimos se os insets devem continuar sendo propagados para as views filhas.
        // Na maioria das vezes, queremos retornar 'windowInsets' para que outros componentes
        // também possam reagir (ex: um campo de texto focar quando o teclado abrir).
        if (consume) WindowInsetsCompat.CONSUMED else windowInsets
    }
}
