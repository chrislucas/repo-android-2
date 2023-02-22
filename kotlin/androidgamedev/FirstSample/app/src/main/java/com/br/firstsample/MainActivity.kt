package com.br.firstsample

import android.view.View
import com.google.androidgamesdk.GameActivity

/*
    https://developer.android.com/studio/projects/install-ndk?hl=pt-br

    NDK (native development kit)

    Kit de desenvolvimento nativo do Android (NDK, na sigla em inglês):
    um conjunto de ferramentas que permitem usar códigos C e C++ com o Android.

    CMake: uma ferramenta de compilação externa que funciona com o Gradle para
    criar bibliotecas nativas. Esse componente não é necessário se você pretende usar apenas o ndk-build.

    LLDB: o depurador usado pelo Android Studio para depurar códigos nativos.
    Por padrão, o LLDB será instalado com o Android Studio.
 */
class MainActivity : GameActivity() {
    companion object {
        init {
            System.loadLibrary("firstsample")
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            hideSystemUi()
        }
    }

    private fun hideSystemUi() {
        val decorView = window.decorView
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }
}