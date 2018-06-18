package br.com.telecom.xplore.xplorecustomview.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import br.com.telecom.xplore.xplorecustomview.background.UpdateSurfaceViewOnBackground;

/**
 * Created by r028367 on 22/12/2017.
 */

public class AbstractSurfaceView extends SurfaceView implements SurfaceHolder.Callback2
        , UpdateSurfaceViewOnBackground.UpdateSurfaceView {

    private UpdateSurfaceViewOnBackground thread;
    private SurfaceHolder surfaceHolder;
    private boolean isTouched;
    private Paint paintStroke;
    private Bitmap bitmapTempCanvas;
    private Canvas tempCanvas;
    private Matrix identityMatrix;
    private float xTouch, yTouch;
    private int w, h;

    public AbstractSurfaceView(Context context) {
        super(context);
    }

    public AbstractSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AbstractSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void surfaceRedrawNeeded(SurfaceHolder holder) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void update() {

    }
}
