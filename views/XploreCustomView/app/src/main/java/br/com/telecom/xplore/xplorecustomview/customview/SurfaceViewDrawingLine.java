package br.com.telecom.xplore.xplorecustomview.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.Random;

import br.com.telecom.xplore.xplorecustomview.gesture.DetectDoubleTap;

/**
 * Created by r028367 on 22/12/2017.
 */

public class SurfaceViewDrawingLine extends SurfaceView implements SurfaceHolder.Callback2
        , DetectDoubleTap.OnDoubleTapListener {

    //private static final PathEffect dashed = new DashPathEffect(new float [] {10.0f,20f}, 0.0f);
    private static final int MAX_POINTS_ON_SCREEN = 2;
    private SurfaceHolder surfaceHolder;
    private Paint paintStroke;
    private Bitmap bitmapTempCanvas;
    private Canvas tempCanvas;
    private Matrix identityMatrix;
    private int w;
    private int h;
    private int pointCounter;
    private float [] arrayX, arrayY, arrayLastX, arrayLastY;
    private boolean [] isEnableToTouchXY, isLastEnableToTouchXY;
    private Random random;
    private Path path;
    private static final int MAX_INT_RGB = 255;
    private static final int MIN_INT_RGB = 127;

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
        arrayX      = new float[MAX_POINTS_ON_SCREEN];
        arrayY      = new float[MAX_POINTS_ON_SCREEN];
        arrayLastX  = new float[MAX_POINTS_ON_SCREEN];
        arrayLastY  = new float[MAX_POINTS_ON_SCREEN];
        isEnableToTouchXY = new boolean[MAX_POINTS_ON_SCREEN];
        isLastEnableToTouchXY = new boolean[MAX_POINTS_ON_SCREEN];
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        random = new Random();

        final GestureDetector gd = new GestureDetector(getContext(), new DetectDoubleTap(this));
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gd.onTouchEvent(event);
            }
        });
        //setZOrderOnTop(true);
        //surfaceHolder.setFormat(PixelFormat.TRANSLUCENT);
    }

    @Override
    public void onDoubleTap() {
        Log.i("LISTENER_DOUBLE_TAP", "DOUBLE_TAP CLEAN CANVAS");
        final Canvas originalCanvas = surfaceHolder.lockCanvas();
        synchronized (originalCanvas) {
            //Paint clearPaint = new Paint();
            /**
             * {@link android.graphics.PorterDuff}
             * https://developer.android.com/reference/android/graphics/PorterDuff.html
             * */
            //clearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            //originalCanvas.drawColor(Color.BLACK, PorterDuff.Mode.CLEAR);
            //originalCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.MULTIPLY);
            paintStroke.setStyle(Paint.Style.FILL);
            paintStroke.setColor(Color.BLACK);
            tempCanvas.drawRect(new RectF(0.0f, 0.0f, w, h), paintStroke);
            originalCanvas.drawBitmap(bitmapTempCanvas, identityMatrix, paintStroke);
            surfaceHolder.unlockCanvasAndPost(originalCanvas);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /**
         * PointerIndex:
         * Cada ponto na tela que foi tocado pelo usuario é considerado pelo sistema como um 'ponteiro'.
         * Cada ponteiro de um ID, uma posicao X e Y na tela e um indice. Atraves do indice a classe MotionEvent
         * e capaz de informar ao programador dados sobre o ponto que foi tocado na tela. O valor do indice
         * varia de 0 ate o valor retornado pelo metodo {@link MotionEvent#getPointerCount()} -1
         * */
        int pointerIndex = ((event.getAction()) & MotionEvent.ACTION_POINTER_INDEX_MASK)
                >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
        /**
         * PointerCounter:
         * https://developer.android.com/reference/android/view/MotionEvent.html
         * Número de pontos que foram tocados na tela ao mesmo tempo.
         *
         * Tela que dão suporte a multitouch conseguem rastrear um movimento para cada dedo que toca na tela.
         * Cada elemento que toca na tela que gera um evento de movimento é referenciado como um ponteiro
         * */
        pointCounter = event.getPointerCount();
        /**
         * PointerID:
         * https://developer.android.com/training/gestures/multi.html
         *
         * Cada ponto tocado na tela tem um ID unico que eh definido com a tela eh tocada (ACTION_DOWN || ACTION_POINTER_DOWN))
         * o ID continua válido até que o usuário retire o dedo da tela (ACTION_UP || ACTION_POINTER_UP)
         * */
        int pointerId = event.getPointerId(pointerIndex);
        /*
            xTouch = event.getX(event.findPointerIndex(pointerId));
            yTouch = event.getY(event.findPointerIndex(pointerId));
            Log.i("SURFACE_DRAWING", String.format("Touched in (%f %f).\nPointCounter %d.\nPointerId: %d.\n"
                    , xTouch, yTouch, pointCounter, pointerId));
        */
        MotionEvent.PointerProperties properties = new MotionEvent.PointerProperties();
        event.getPointerProperties(pointerIndex, properties);
        /**
         * {@link MotionEvent#TOOL_TYPE_FINGER}
         * */
        //Log.i("SURFACE_DRAWING", String.format("Propriedades:\nTootType: %d", properties.toolType));
        int action = event.getActionMasked();
        if(pointCounter <= MAX_POINTS_ON_SCREEN && pointerIndex < MAX_POINTS_ON_SCREEN) {
            for (int i = 0; i < pointCounter; i++) {
                int id = event.getPointerId(i);
                if(id < pointCounter) {
                    switch (action) {
                        case MotionEvent.ACTION_DOWN:
                            arrayLastX[id] = event.getX(i);
                            arrayLastY[id] = event.getY(i);
                            isLastEnableToTouchXY[id] = isEnableToTouchXY[id] = true;
                            path = new Path();
                            path.moveTo(event.getX(i), event.getY(i));
                            break;
                        case MotionEvent.ACTION_UP:
                            break;
                        case MotionEvent.ACTION_MOVE:
                            arrayLastX[id] = arrayX[id];
                            arrayLastY[id] = arrayY[id];
                            isLastEnableToTouchXY[id] = isEnableToTouchXY[id] = true;
                            path.quadTo(arrayLastX[id]
                                , arrayLastY[id]
                                , event.getX(i) //(arrayLastX[id]+event.getX(i))/2
                                , event.getY(i) //(arrayLastY[id]+event.getY(i))/2
                            );
                            break;
                        case MotionEvent.ACTION_POINTER_DOWN:
                            break;
                        case MotionEvent.ACTION_POINTER_UP:
                            path = new Path();
                            break;
                    }
                    arrayX[id] = event.getX(i);
                    arrayY[id] = event.getY(i);
                }
            }
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:
                    tryLockCanvas();
                    break;
            }
        }
        return true;
    }

    private int getRandomColor() {
        int r = random.nextInt(MAX_INT_RGB - MIN_INT_RGB) + MIN_INT_RGB;
        int g = random.nextInt(MAX_INT_RGB - MIN_INT_RGB) + MIN_INT_RGB;
        int b = random.nextInt(MAX_INT_RGB - MIN_INT_RGB) + MIN_INT_RGB;
        return 0xff000000 + (r << 16) + (g << 8) + b;
    }

    private void tryLockCanvas() {
        if(surfaceHolder.getSurface().isValid()) {
            Canvas originalCanvas = null;
            try {
                originalCanvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    if(originalCanvas != null) {
                        //drawingColoredPath(originalCanvas);
                        drawingColoredLine(originalCanvas);
                    }
                }
            }
            finally {
                if(originalCanvas != null) {
                    surfaceHolder.unlockCanvasAndPost(originalCanvas);
                }
            }
        }
    }

    private void drawingColoredLine(Canvas originalCanvas) {
        for(int i = 0; pointCounter < MAX_POINTS_ON_SCREEN && i<pointCounter; i++) {
            if(isEnableToTouchXY[i] && isLastEnableToTouchXY[i]) {
                paintStroke.setStyle(Paint.Style.STROKE);
                paintStroke.setStrokeWidth(10.0f);
                paintStroke.setDither(true);
                /**
                 * https://developer.android.com/reference/android/graphics/Paint.Join.html
                 * */
                paintStroke.setStrokeJoin(Paint.Join.BEVEL);
                /**
                 * https://developer.android.com/reference/android/graphics/Paint.Cap.html
                 * */
                paintStroke.setStrokeCap(Paint.Cap.ROUND);
                //paintStroke.setPathEffect(dashed);
                paintStroke.setColor(getRandomColor());
                Log.i("SURFACE_DRAWING"
                        , String.format("DRAWING - ID %d. LAST POINT(%d, %d), CURRENT POINT(%d,%d).\n"
                        , i, Math.round(arrayLastX[i]), Math.round(arrayLastY[i]), Math.round(arrayX[i]), Math.round(arrayY[i]))
                );
                tempCanvas.drawLine(Math.round(arrayLastX[i]), Math.round(arrayLastY[i]), Math.round(arrayX[i]), Math.round(arrayY[i]), paintStroke);
                originalCanvas.drawBitmap(bitmapTempCanvas, identityMatrix, paintStroke);
                isEnableToTouchXY[i] = false;
                isLastEnableToTouchXY[i] = false;
            }
        }
    }

    private void drawingColoredPath(Canvas originalCanvas) {
        for(int i = 0; pointCounter < MAX_POINTS_ON_SCREEN && i<pointCounter; i++) {
            if (isEnableToTouchXY[i] && isLastEnableToTouchXY[i]) {
                paintStroke.setStyle(Paint.Style.STROKE);
                paintStroke.setStrokeWidth(10.0f);
                paintStroke.setDither(true);
                /**
                 * https://developer.android.com/reference/android/graphics/Paint.Join.html
                 * */
                paintStroke.setStrokeJoin(Paint.Join.BEVEL);
                /**
                 * https://developer.android.com/reference/android/graphics/Paint.Cap.html
                 * */
                paintStroke.setStrokeCap(Paint.Cap.ROUND);
                //paintStroke.setPathEffect(dashed);
                paintStroke.setColor(getRandomColor());
                tempCanvas.drawPath(path, paintStroke);
                originalCanvas.drawBitmap(bitmapTempCanvas, identityMatrix, paintStroke);
                isEnableToTouchXY[i] = false;
                isLastEnableToTouchXY[i] = false;
            }
        }
    }

    @Override
    public void surfaceRedrawNeeded(SurfaceHolder holder) {
        Log.i("SURFACE_HOLDER", "SURFACE_REDRAW");
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        w = getWidth();
        h = getHeight();
        Log.i("SURFACE_HOLDER"
                , String.format("SURFACE_CREATE.\nDimension(%d, %d),", w, h));
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

    }

    public void onPause() {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(wSize, hSize);
    }
}
