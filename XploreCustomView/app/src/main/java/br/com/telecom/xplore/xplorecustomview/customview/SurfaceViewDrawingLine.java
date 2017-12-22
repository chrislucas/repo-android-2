package br.com.telecom.xplore.xplorecustomview.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import br.com.telecom.xplore.xplorecustomview.background.InfiniteThreadUpdateSurfaceView;

/**
 * Created by r028367 on 22/12/2017.
 */

public class SurfaceViewDrawingLine extends SurfaceView
        implements InfiniteThreadUpdateSurfaceView.UpdateSurfaceView, SurfaceHolder.Callback2 {

    private InfiniteThreadUpdateSurfaceView thread;
    private SurfaceHolder surfaceHolder;
    private boolean isTouched;
    private Paint paintStroke;
    private Bitmap bitmapTempCanvas;
    private Canvas tempCanvas;
    private Matrix identityMatrix;
    private float xTouch, yTouch;
    private int w, h;

    private static final PathEffect dashed =  new DashPathEffect(new float [] {10.0f,20f}, 0.0f);
    private static final int Q_POINTS = 2;
    private float [] arrayX, arrayY, arrayLastX, arrayLastY;
    private boolean [] isTouchXY, isLastTouchXY;

    public SurfaceViewDrawingLine(Context context) {
        super(context);
        init();
    }

    public SurfaceViewDrawingLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SurfaceViewDrawingLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paintStroke = new Paint(Paint.ANTI_ALIAS_FLAG);
        tempCanvas  = new Canvas();
        arrayX      = new float[Q_POINTS];
        arrayY      = new float[Q_POINTS];
        arrayLastX  = new float[Q_POINTS];
        arrayLastY  = new float[Q_POINTS];
        isTouchXY     = new boolean[Q_POINTS];
        isLastTouchXY = new boolean[Q_POINTS];
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        xTouch = event.getX();
        yTouch = event.getY();
        int pointerIndex = ((event.getAction()) & MotionEvent.ACTION_POINTER_INDEX_MASK)
                >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
        int pointCounter = event.getPointerCount();
        int pointerId = event.getPointerId(pointerIndex);
        if(pointCounter <= Q_POINTS && pointerIndex < Q_POINTS) {
            for (int i = 0; i < pointCounter; i++) {
                int id = event.getPointerId(i);
                if(id < pointCounter) {
                    arrayLastX[id]    = arrayX[id];
                    arrayLastY[id]    = arrayY[id];
                    arrayX[id]        = xTouch;
                    arrayY[id]        = yTouch;
                    isLastTouchXY[id] = isTouchXY[id];
                }
            }
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    isTouchXY[pointerId] = true;
                    break;
                case MotionEvent.ACTION_MOVE:
                    isTouchXY[pointerId] = true;
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    isTouchXY[pointerId] = true;
                    break;
                default:
                    isTouchXY[pointerId] = false;
                    isLastTouchXY[pointerId] = false;
            }
        }
        return true;
    }

    @Override
    public void update() {
        if(surfaceHolder.getSurface().isValid()) {
            Canvas canvas = surfaceHolder.lockCanvas();
            synchronized (surfaceHolder) {
                if(canvas != null) {
                    if(isTouchXY[0] && isLastTouchXY[0]) {
                        paintStroke.setStyle(Paint.Style.STROKE);
                        paintStroke.setStrokeWidth(10.0f);
                        //paintStroke.setStrokeJoin(Paint.Join.MITER);
                        //paintStroke.setStrokeCap(Paint.Cap.SQUARE);
                        paintStroke.setColor(Color.RED);
                        //paintStroke.setPathEffect(dashed);
                        Log.i("SURFACE_DRAWING", String.format("LINE LP(%f, %f), CP(%f,%f)"
                                , arrayLastX[0], arrayLastY[0], arrayX[0], arrayY[1]));
                        tempCanvas.drawLine(arrayLastX[0], arrayLastY[0], arrayX[0], arrayY[1], paintStroke);
                        canvas.drawBitmap(bitmapTempCanvas, identityMatrix, null);
                        isTouchXY[0] = false;
                        isLastTouchXY[0] = false;
                    }
                }
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    @Override
    public void surfaceRedrawNeeded(SurfaceHolder holder) {
        Log.i("SURFACE_HOLDER", "SURFACE_REDRAW");
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i("SURFACE_HOLDER", "SURFACE_CREATE");
        w = getWidth();
        h = getWidth();
        bitmapTempCanvas = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        tempCanvas = new Canvas();
        tempCanvas.setBitmap(bitmapTempCanvas);
        identityMatrix = new Matrix();
        onResume();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.i("SURFACE_HOLDER", "SURFACE_CHANGED");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.i("SURFACE_HOLDER", "SURFACE_DESTROYED");
        onPause();
    }

    @Override
    public void surfaceRedrawNeededAsync(SurfaceHolder holder, Runnable drawingFinished) {
        Log.i("SURFACE_HOLDER", "SURFACE_REDRAW_ASYNC");
    }

    public void onResume() {
        thread = new InfiniteThreadUpdateSurfaceView(this);
        thread.setRunning(true);
        thread.start();
    }

    public void onPause() {
        while(true) {
            try {
                thread.join();
                thread.setRunning(false);
                break;
            } catch (InterruptedException e) {
                Log.e("SURFACE_VIEW_RDN", e.getMessage());
            }
        }
    }
}
