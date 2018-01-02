package br.com.telecom.xplore.xplorecustomview.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.Random;

import br.com.telecom.xplore.xplorecustomview.background.InfiniteThreadUpdateSurfaceView;
import br.com.telecom.xplore.xplorecustomview.gesture.DetectDoubleTap;

/**
 * Created by r028367 on 22/12/2017.
 */

public class SurfaceViewDrawingLine extends SurfaceView
        implements InfiniteThreadUpdateSurfaceView.UpdateSurfaceView, SurfaceHolder.Callback2, DetectDoubleTap.OnDoubleTap {

    private InfiniteThreadUpdateSurfaceView thread;
    private SurfaceHolder surfaceHolder;
    private Paint paintStroke;
    private Bitmap bitmapTempCanvas;
    private Canvas tempCanvas;
    private Matrix identityMatrix;
    private float xTouch, yTouch;
    private int w, h, pointerId, pointCounter;
    private static final PathEffect dashed = new DashPathEffect(new float [] {10.0f,20f}, 0.0f);
    private static final int Q_POINTS = 2;
    private float [] arrayX, arrayY, arrayLastX, arrayLastY;
    //private float matrixXY [][];
    private boolean [] isEnableToTouchXY, isLastEnableToTouchXY;
    private Random random;

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
        isEnableToTouchXY = new boolean[Q_POINTS];
        isLastEnableToTouchXY = new boolean[Q_POINTS];
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
        /**
         *
         * */
        //surfaceHolder.setFormat(PixelFormat.TRANSLUCENT);
    }

    @Override
    public void doSomeThing() {
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
        pointerId = event.getPointerId(pointerIndex);
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
        if(pointCounter <= Q_POINTS && pointerIndex < Q_POINTS) {
            for (int i = 0; i < pointCounter; i++) {
                int id = event.getPointerId(i);
                if(id < pointCounter) {
                    arrayLastX[id]    = arrayX[id];
                    arrayLastY[id]    = arrayY[id];
                    arrayX[id]        = event.getX(event.findPointerIndex(id));
                    arrayY[id]        = event.getY(event.findPointerIndex(id));
                    String strAction = "UNDEFINED";
                    switch (action) {
                        case MotionEvent.ACTION_DOWN:
                            strAction = "ACTION_DOWN";
                            break;
                        case MotionEvent.ACTION_UP:
                            strAction = "ACTION_UP";
                            break;
                        case MotionEvent.ACTION_MOVE:
                            strAction = "ACTION_MOVE";
                            break;
                        case MotionEvent.ACTION_POINTER_DOWN:
                            strAction = "ACTION_POINTER_DOWN";
                            break;
                        case MotionEvent.ACTION_POINTER_UP:
                            strAction = "ACTION_POINTER_UP";
                            break;
                    }
                    Log.i("SURFACE_DRAWING", String.format("MotionEvent - Action %s. ID %d, Last (%f, %f) Current(%f, %f)"
                            , strAction, id, arrayLastX[id], arrayLastY[id], arrayX[id], arrayY[id]));
                    isLastEnableToTouchXY[id] = isEnableToTouchXY[id];
                }
            }
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    if(pointerId < Q_POINTS) {
                        for (int i=0; i<=pointerId; i++)
                            isEnableToTouchXY[i] = true;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                        break;
            }
        }
        return true;
    }

    private final int getRandomColor() {
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return 0xff000000 + (r << 16) + (g << 8) + b;
    }

    @Override
    public void update() {
        if(surfaceHolder.getSurface().isValid()) {
            Canvas originalCanvas = null;
            try {
                originalCanvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    if(originalCanvas != null) {
                        for(int i=0; pointCounter < Q_POINTS && i<pointCounter; i++) {
                            if(isEnableToTouchXY[i] && isLastEnableToTouchXY[i]) {
                                paintStroke.setStyle(Paint.Style.STROKE);
                                paintStroke.setStrokeWidth(10.0f);
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
                                Log.i("SURFACE_DRAWING", String.format("DRAWING - ID %d. LAST POINT(%f, %f), CURRENT POINT(%f,%f).\n"
                                        , i, arrayLastX[i], arrayLastY[i], arrayX[i], arrayY[i]));
                                tempCanvas.drawLine(arrayLastX[i], arrayLastY[i], arrayX[i], arrayY[i], paintStroke);
                                originalCanvas.drawBitmap(bitmapTempCanvas, identityMatrix, paintStroke);
                                isEnableToTouchXY[i] = false;
                                isLastEnableToTouchXY[i] = false;
                            }
                        }
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

    /**
     * Inicia a Thread que eh responsavel por atualizar o SurfaceView
     * */
    public void onResume() {
        if(thread == null) {
            thread = new InfiniteThreadUpdateSurfaceView(this);
            thread.setRunning(true);
            thread.start();
        }
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
