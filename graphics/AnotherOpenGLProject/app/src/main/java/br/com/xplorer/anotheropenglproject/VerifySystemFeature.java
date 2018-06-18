package br.com.xplorer.anotheropenglproject;

import android.content.Context;
import android.content.pm.PackageManager;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.support.annotation.RequiresApi;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by r028367 on 09/04/2018.
 */

public class VerifySystemFeature {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static boolean systemHasOpenGLExtPack(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_OPENGLES_EXTENSION_PACK);
    }

    public static boolean systeHasBluetooth(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH);
    }


    public static boolean createContextOpenGL(Context context, final double version) {

        final int EGL_CONTEXT_CLIENT_VERSION = 0x3098;

        GLSurfaceView.EGLContextFactory eglContextFactory = new GLSurfaceView.EGLContextFactory() {
            @Override
            public EGLContext createContext(EGL10 egl, EGLDisplay display, EGLConfig eglConfig) {
                return null;
            }

            @Override
            public void destroyContext(EGL10 egl, EGLDisplay display, EGLContext context) {

            }
        };


        //eglContextFactory.createContext()

        return false;
    }


    public static String getOpenGLVersion() {
        return "";
    }

}
