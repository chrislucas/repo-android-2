package com.example.feature.codelabs.advkotlin.shaders

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidallcodelabs.R

/**
 * https://developer.android.com/codelabs/advanced-android-kotlin-training-shaders#4
 *  - Shader definie a textura para o objeto Paint. Uma subclasse de Shader é posta em Paint
 *  para chamar paint.setShader(Shader)
 *      - Apois definir um shader, qualquer objeto
 */

class ExploringShadersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exploring_shaders)
    }
}