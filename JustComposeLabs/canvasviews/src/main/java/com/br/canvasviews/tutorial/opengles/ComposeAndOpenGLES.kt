package com.br.canvasviews.tutorial.opengles

import android.opengl.GLSurfaceView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.viewinterop.AndroidView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/*
    https://developer.android.com/develop/ui/views/graphics/opengl

    Pesquisa: Open GL Es and Compose android

    https://developer.android.com/develop/ui/views/graphics/opengl/environment
    https://stackoverflow.com/questions/78796021/how-to-render-opengl-alongside-jetpack-compose-ui-without-covering-other-element
 */


@Composable
fun OpenGLView(modifier: Modifier = Modifier) {
    AndroidView(
        factory = { context ->
            GLSurfaceView(context).apply {
                setEGLContextClientVersion(3) // Or 3 for OpenGL ES 3.0
                setRenderer(
                    object : GLSurfaceView.Renderer {
                        override fun onDrawFrame(gl: GL10?) {

                        }

                        override fun onSurfaceChanged(
                            gl: GL10?,
                            width: Int,
                            height: Int
                        ) {
                            TODO("Not yet implemented")
                        }

                        override fun onSurfaceCreated(
                            gl: GL10?,
                            config: EGLConfig?
                        ) {
                            TODO("Not yet implemented")
                        }

                    }
                )
            }
        },
        modifier = modifier.clipToBounds(),
    )
}