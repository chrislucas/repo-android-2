package com.br.funwithcamerax.tutorials.coursera.baseproject

import android.content.Context
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
    Curso: https://www.coursera.org/learn/android-graphics-opengl-es/supplement/52HV4/download-the-example-program
    Base de codigp: https://github.com/bennyplo/Advanced-Android-MOOC-OpenGL
 */
class BaseProjectOpenGLActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_base_project_open_glactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}


class CustomGlSurfaceView(context: Context): GLSurfaceView(context) {
    private val renderer: Renderer = CustomRendererGlSurfaceView()

    init {
        setEGLContextClientVersion(3)
        setRenderer(renderer)
        /*
            Renderiza a view somente quando ha uma mudanca nos dados de desenho (drawing data
         */
        renderMode = GLSurfaceView.RENDERMODE_WHEN_DIRTY
    }
}

class CustomRendererGlSurfaceView: GLSurfaceView.Renderer {
    override fun onSurfaceCreated(
        gl: GL10?,
        config: EGLConfig?
    ) {
        TODO("Not yet implemented")
    }

    override fun onSurfaceChanged(
        gl: GL10?,
        width: Int,
        height: Int
    ) {
        TODO("Not yet implemented")
    }

    override fun onDrawFrame(gl: GL10?) {
        TODO("Not yet implemented")
    }

}