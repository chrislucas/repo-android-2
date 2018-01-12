package customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import actions.SimpleGestureDetectorDoubleTap;
import utils.ColorUtils;
import utils.DoublingBufferDrawing;

/**
 * Created by r028367 on 12/01/2018.
 */

public class MultiTouchCanvasDrawingPath extends View implements SimpleGestureDetectorDoubleTap.OnDoubleTapListener {

    private static final int MAX_POINTS_TOUCH_SCREEN = 3;
    private float pointsX [] = new float[MAX_POINTS_TOUCH_SCREEN];
    private float pointsY [] = new float[MAX_POINTS_TOUCH_SCREEN];
    private float pointsLastX [] = new float[MAX_POINTS_TOUCH_SCREEN];
    private float pointsLastY [] = new float[MAX_POINTS_TOUCH_SCREEN];
    private boolean isEnableToDrawing [] = new boolean[MAX_POINTS_TOUCH_SCREEN];
    private boolean isEnableToDrawingLast [] = new boolean[MAX_POINTS_TOUCH_SCREEN];

    private DoublingBufferDrawing doublingBufferDrawing;
    private Paint mPencilDrawingPath;
    private Matrix identityMatrix;
    private Path path [] = new Path[MAX_POINTS_TOUCH_SCREEN];

    private int mWidth; // largura da tela
    private int mHeight;   // altura da tela
    private int pointsTouchedOnScreen; // numero de pontos tocados na tela
    private int backgroundColor;

    public MultiTouchCanvasDrawingPath(Context context) {
        super(context);
        init();
    }

    public MultiTouchCanvasDrawingPath(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MultiTouchCanvasDrawingPath(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        initDoublingBufferDrawing(mWidth, mHeight);
    }

    private void initDoublingBufferDrawing(int mWidth, int mHeight) {
        doublingBufferDrawing = new DoublingBufferDrawing(mPencilDrawingPath, mWidth, mHeight);
    }

    private void init() {
        backgroundColor = getBackground() == null ? Color.WHITE : ((ColorDrawable) getBackground()).getColor();
        preparePencilToDrawing();
        for(int i=0; i<MAX_POINTS_TOUCH_SCREEN; i++)
            path[i] = new Path();
        identityMatrix = new Matrix();
        final GestureDetector gd = new GestureDetector(getContext()
                , new SimpleGestureDetectorDoubleTap(this));
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gd.onTouchEvent(event);
            }
        });
    }

    private void preparePencilToDrawing() {
        mPencilDrawingPath = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPencilDrawingPath.setStyle(Paint.Style.STROKE);
        mPencilDrawingPath.setStrokeWidth(10.0f);
        mPencilDrawingPath.setAntiAlias(true);
        mPencilDrawingPath.setDither(true);
        /**
         * https://developer.android.com/reference/android/graphics/Paint.Join.html
         * */
        mPencilDrawingPath.setStrokeJoin(Paint.Join.BEVEL);
        /**
         * https://developer.android.com/reference/android/graphics/Paint.Cap.html
         * */
        mPencilDrawingPath.setStrokeCap(Paint.Cap.ROUND);
    }

    private void clearCanvas() {
        mPencilDrawingPath.setStyle(Paint.Style.FILL);
        mPencilDrawingPath.setColor(backgroundColor);
        doublingBufferDrawing.getCacheCanvas()
                .drawRect(doublingBufferDrawing.getRectFBlankCanvas(), mPencilDrawingPath);
        invalidate();
        initDoublingBufferDrawing(mWidth, mHeight);
        preparePencilToDrawing();
    }

    @Override
    public void onDoubleTap(MotionEvent event) {
        Log.i("DOUBLE_TAP", String.format("CLEANING CANVAS %f %f"
                , event.getX(), event.getY()));
        clearCanvas();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(doublingBufferDrawing.getCacheBitmap(), identityMatrix, mPencilDrawingPath);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        /**
         * Numero de pontos tocados na tela
         * */
        pointsTouchedOnScreen = event.getPointerCount();
        if(verifyTouchedPoints()) {
            for(int idx = 0; idx < pointsTouchedOnScreen; idx++) {
                /**
                 * Cada ponto tocado na tela tem um ID vinculado a ele
                 * os IDS sao numerados de 0 <= id < getPointerCount()
                 * */
                int idPointer = event.getPointerId(idx);
                float cx = event.getX(idx);
                float cy = event.getY(idx);
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        pointsLastX[idPointer] = cx;
                        pointsLastY[idPointer] = cy;
                        path[idPointer].moveTo(cx, cy);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        pointsLastX[idPointer] = pointsX[idPointer];
                        pointsLastY[idPointer] = pointsY[idPointer];
                        path[idPointer].quadTo(
                            pointsLastX[idPointer]
                            , pointsLastY[idPointer]
                            , cx
                            , cy
                        );
                        break;
                }
                pointsX[idPointer] = cx;
                pointsY[idPointer] = cy;
            }
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:
                    /**
                     * TODO DRAWING ALL POINTS
                     * */
                    drawing();
                    invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    /**
                     * TODO limpar o path
                     * */
                    if(pointsTouchedOnScreen < path.length) {
                        for (int i = 0; i < pointsTouchedOnScreen; i++) {
                            path[i].reset();
                        }
                    }
                    break;
            }
        }
        return true;
    }

    private void drawing() {
        mPencilDrawingPath.setColor(ColorUtils.getRandomColor(0, 200));
        if(verifyTouchedPoints()) {
            for(int i=0; i<pointsTouchedOnScreen; i++) {
                doublingBufferDrawing.getCacheCanvas().drawPath(path[i], mPencilDrawingPath);
            }
        }
    }

    private boolean verifyTouchedPoints() {
        return pointsTouchedOnScreen <= MAX_POINTS_TOUCH_SCREEN;
    }
}
