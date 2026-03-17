package com.br.funwithcamerax.tutorials.google.introductionopengl

import android.content.Context
import android.opengl.GLES30
import android.opengl.GLES31
import android.opengl.GLSurfaceView
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.br.funwithcamerax.R
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/*
    TODO ler essa doc
    https://developer.android.com/develop/ui/views/graphics/opengl/about-opengl
    https://developer.android.com/develop/ui/views/graphics/opengl/environment

    <supports-gl-texture>
    https://developer.android.com/guide/topics/manifest/supports-gl-texture-element

 */
class IntroductionToTheOpenGLActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        CustomGlSurfaceView(this).run {
            setContentView(this)
            ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }
}

class CustomGLRender : GLSurfaceView.Renderer {
    /*
        https://developer.android.com/develop/ui/views/graphics/opengl/environment#renderer
     */
    override fun onSurfaceCreated(
        gl: GL10?,
        config: EGLConfig?
    ) {
        GLES31.glClearColor(ZERO_F, ZERO_F, ZERO_F, ONE_F)
    }

    override fun onSurfaceChanged(
        gl: GL10?,
        width: Int,
        height: Int
    ) {
        GLES31.glViewport(ZERO, ZERO, width, height)
    }

    override fun onDrawFrame(gl: GL10?) {
        GLES31.glClear(GLES31.GL_COLOR_BUFFER_BIT)
    }

    companion object {
        const val ZERO_F = 0.0f
        const val ZERO = 0
        const val ONE_F = 1.0f
    }
}

class CustomGlSurfaceView(context: Context) : GLSurfaceView(context) {
    private val renderer: CustomGLRender = CustomGLRender()

    init {
        setEGLContextClientVersion(3)
        /*
            opcional: Definir o render mode para somente desenhar a view
            quando ha uma mudanca nos dados de desenho (drawing data)
         */
        setRenderer(renderer)
        renderMode = RENDERMODE_WHEN_DIRTY
    }
}