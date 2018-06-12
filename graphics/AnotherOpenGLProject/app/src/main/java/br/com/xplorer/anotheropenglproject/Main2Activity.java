package br.com.xplorer.anotheropenglproject;

import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class Main2Activity extends AppCompatActivity {

    /**
     * {@link GLSurfaceView} uma view onde podemoa desenhar e manipular objetos usando a API
     * OpenGL. A chamada é parecida com a implementacao da classe {@link android.view.SurfaceView}.
     *
     *
     * */
    private GLSurfaceView glSurfaceView;

    /***
     *
     * {@link GLSurfaceView.Renderer} Essa interface define os metodos que devem ser implementados
     * para desenhar numa {@link GLSurfaceView}. Devemos criar uma instancia dessa interface e adiciona-la
     * a uma instancia de GLSurfaceView usando o metodo{@link GLSurfaceView#setRenderer(GLSurfaceView.Renderer)}
     *
     * {@link GLSurfaceView.Renderer#onSurfaceCreated(GL10, EGLConfig)}
     *
     * O sistema chama esse método uma única vez, quando uma instancia de GLSurfaceView é criada. Aqui
     * é um bom lugar para executar configurações, iniciar varáveis etc.
     *
     * {@link GLSurfaceView.Renderer#onDrawFrame(GL10)}
     *
     * O sistema chama este metodo toda vez que ocorre um re-desenho na GLSurfaceView
     *
     * {@link GLSurfaceView.Renderer#onSurfaceChanged(GL10, int, int)} (GL10)}
     *
     * O sistema executa esse metodo quando a geometria da GLSurfaceView, dimensão ou orientação,
     * mudam.
     *
     * */
    private GLSurfaceView.Renderer renderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String msg =VerifySystemFeature.systemHasOpenGLExtPack(this)
                ? "Seu dispositivo Possui OpenGL Extension Pack" : "Seu dispositivo não Possui OpenGL Extension Pack";
        Toast.makeText(this, String.format("%s",  msg), Toast.LENGTH_LONG).show();

    }
}
