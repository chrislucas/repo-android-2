package br.com.xplorer.anotheropenglproject;

import android.content.Context;
import android.graphics.Canvas;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by r028367 on 09/04/2018.
 */

public class ImplGLSurfaceView extends GLSurfaceView {

    private GLRenderer glRenderer;

    public ImplGLSurfaceView(Context context) {
        super(context);
    }

    public ImplGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
