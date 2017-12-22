package br.com.telecom.xplore.xplorecustomview.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

import br.com.telecom.xplore.xplorecustomview.R;
import br.com.telecom.xplore.xplorecustomview.background.InfiniteThreadUpdateSurfaceView;

/**
 * Created by r028367 on 22/12/2017.
 */
public class SurfaceViewRandomBackground extends SurfaceView implements
        InfiniteThreadUpdateSurfaceView.UpdateSurfaceView, SurfaceHolder.Callback2 {

    private InfiniteThreadUpdateSurfaceView thread;
    private SurfaceHolder surfaceHolder;
    private boolean isTouched;
    private float xTouch, yTouch;

    private static final float SIZE_PIXEL = 6.0f;

    private Paint paintStroke, paintFill; //, paintDrawingDot, paintDrawingRandomDot;
    private Random random;
    private Bitmap bitmapTempCanvas;
    private Canvas tempCanvas;
    private Matrix identityMatrix;
    private int w, h, dots = 0;

    public SurfaceViewRandomBackground(Context context) {
        super(context);
        init();
    }

    public SurfaceViewRandomBackground(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SurfaceViewRandomBackground(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paintStroke = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintStroke.setStyle(Paint.Style.STROKE);
        paintStroke.setStrokeWidth(3.0f);
        paintFill = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintFill.setStyle(Paint.Style.FILL);
        paintFill.setColor(ContextCompat.getColor(getContext(), R.color.background_scratch));
/*
        paintDrawingDot = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintDrawingDot.setStrokeWidth(10.0f);
        paintDrawingDot.setStyle(Paint.Style.STROKE);
        paintDrawingDot.setColor(BACKGROUND_SURFACE);

        paintDrawingRandomDot = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintDrawingRandomDot.setStyle(Paint.Style.STROKE);
        paintDrawingRandomDot.setStrokeWidth(10.0f);
        paintDrawingRandomDot.setStyle(Paint.Style.STROKE);
*/
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        random = new Random();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //canvas.drawRect(0,0, getWidth(), getHeight(), paintStroke);
        //canvas.drawRect(0,0, getWidth(), getHeight(), paintFill);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        xTouch = event.getX();
        yTouch = event.getY();
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                isTouched = true;
                break;
            case MotionEvent.ACTION_MOVE:
                isTouched = true;
                break;
            default:
                isTouched = false;
        }
        return true;
    }

    @Override
    public void update() {
        if(surfaceHolder.getSurface().isValid()) {
            /**
             * Bloquea o canvas para modificacoes
             * */
            Canvas canvas = surfaceHolder.lockCanvas();
            synchronized (surfaceHolder) {
                if(canvas != null) {
                    //w = canvas.getWidth();
                    //h = canvas.getHeight();
                    paintStroke.setStrokeWidth(SIZE_PIXEL);
                    /**
                     * Desenhando pontos aleatorios na tela
                     * */
                    if (dots < 5000) {
                        int x = random.nextInt(w-1);
                        int y = random.nextInt(h-1);
                        int r = random.nextInt(255);
                        int g = random.nextInt(255);
                        int b = random.nextInt(255);
                        paintStroke.setColor(0xff000000 + (r << 16) + (g << 8) + b);
                        tempCanvas.drawPoint(x, y, paintStroke);
                        //Log.i("SURFACE_VIEW_RDN", String.format("Dot %d in %d: %d", dots, x, y));
                        dots++;
                    }
                    /**
                     *
                     * */
                    if(isTouched) {
                        paintStroke.setStrokeWidth(SIZE_PIXEL);
                        paintStroke.setColor(Color.WHITE);
                        tempCanvas.drawPoint(xTouch, yTouch, paintStroke);
                        dots--;
                        Log.i("SURFACE_VIEW_RDN", String.format("TOUCH in %f: %f\nDots: %s", xTouch, yTouch, dots));
                        isTouched = false;
                    }
                    canvas.drawBitmap(bitmapTempCanvas, identityMatrix, null);
                }
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    public void onResume() {
        thread = new InfiniteThreadUpdateSurfaceView(this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceRedrawNeeded(SurfaceHolder holder) {
        Log.i("SURFACE_HOLDER", "SURFACE_REDRAW");
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i("SURFACE_HOLDER", "SURFACE_CREATED");
        //setBackgroundColor(ContextCompat.getColor(getContext(), R.color.background_scratch));
        w = getWidth();
        h = getHeight();
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
