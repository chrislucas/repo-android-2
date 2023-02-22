package com.codelabs.advkotlin.canvas.drawing.view

import android.view.View
import android.view.Window
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.codelabs.advkotlin.ext.views.ImmersiveModeCallback

fun Window.toggleImmersiveMode(container: View, viewFireAction: View, immersiveModeCallback: ImmersiveModeCallback? = null) {
    /*
        https://developer.android.com/develop/ui/views/layout/immersive
     */

    val windowInsetsController = WindowCompat.getInsetsController(this, decorView)
    // Configurando o comportamento ao esconder as barras laterais
    windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

    // adicionando listener para atualizar o comportameto de entrar e sair do modo imersivo

    decorView.setOnApplyWindowInsetsListener { _, insets ->

        val windowInsetCompat = WindowInsetsCompat.toWindowInsetsCompat(insets)

        // checar a visibilidade da naviation bar e startus baer ao inves de checar a visibilidade da systembar

        windowInsetCompat.run {
            if (isVisibleNavigationBars() || isVisibleStatusBars()) {
                viewFireAction.setOnClickListener {
                    windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
                    immersiveModeCallback?.on()
                }
            } else {
                viewFireAction.setOnClickListener {
                    windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
                    immersiveModeCallback?.off()
                }
            }
        }
        container.onApplyWindowInsets(insets)
    }
}

private fun WindowInsetsCompat.isVisibleNavigationBars() = isVisible(WindowInsetsCompat.Type.navigationBars())

private fun WindowInsetsCompat.isVisibleStatusBars() = isVisible(WindowInsetsCompat.Type.statusBars())

/*
  https://stackoverflow.com/questions/62643517/immersive-fullscreen-on-android-11
 */

fun Window.startImmersiveMode(view: View) {
    WindowCompat.setDecorFitsSystemWindows(this, false)
    WindowInsetsControllerCompat(this, view).let { controller ->
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }

}

fun Window.stopImmersiveMode(view: View) {
    WindowCompat.setDecorFitsSystemWindows(this, true)
    WindowInsetsControllerCompat(this, view).let { controller ->
        controller.show(WindowInsetsCompat.Type.systemBars())
    }
}