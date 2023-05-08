package com.example.feature.android.api.v33.shaders

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidallcodelabs.R

/**
 * https://android-developers.googleblog.com/2022/02/first-preview-android-13.html
 * Resumo

    Shaders em Android definem como o objeto Paint deve aplicar a textura


    - Video Android Graphics - https://www.youtube.com/watch?v=qlBxrvhk3tg

    - Programmable shaders - Android 13 adds support for programmable
        - Android Graphics Shading Language (AGSL)
            - https://developer.android.com/develop/ui/views/graphics/agsl
        - RuntimeShader
            - https://developer.android.com/reference/android/graphics/RuntimeShader
        - https://yggr.medium.com/exploring-android-13-programmable-shaders-db91683127e3

 */
class AboutProgrammbleShadersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_programmable_shaders)
    }
}