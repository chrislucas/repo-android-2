package customviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;

import actions.SimpleGestureDetectorDoubleTap;

/**
 * Created by r028367 on 12/01/2018.
 */

public class CanvasDrawingPath extends ViewGroup
        implements SimpleGestureDetectorDoubleTap.OnDoubleTapListener {

    private static final int MAX_INT_RGB = 255;
    private static final int MIN_INT_RGB = 100;

    private int getRandomColor() {
        int r = random.nextInt(MAX_INT_RGB - MIN_INT_RGB) + MIN_INT_RGB;
        int g = random.nextInt(MAX_INT_RGB - MIN_INT_RGB) + MIN_INT_RGB;
        int b = random.nextInt(MAX_INT_RGB - MIN_INT_RGB) + MIN_INT_RGB;
        return 0xff000000 + (r << 16) + (g << 8) + b;
    }

    private Path path;
    private Paint pencilDrawer;
    private int w, h;
    private final Random random = new Random();
    private RectF rectF;
    private float lastX, lastY;
    private Matrix identityMatrix;

    /**
     * Usando um bitmap e um canvas adicional conseguimos usar a tecnica de
     * doubling buffer, desenhando no bitmap de cache
     * */
    private Bitmap cacheBitmap;
    private Canvas cacheCanvas;

    public CanvasDrawingPath(Context context) {
        super(context);
        init();
    }

    public CanvasDrawingPath(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CanvasDrawingPath(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        preparePencilToDrawing();
        cacheCanvas = new Canvas();
        path = new Path();
        identityMatrix = new Matrix();
        // adicionar listener para eventos de toques na tela para observarmos duplos toques
        final GestureDetector gd = new GestureDetector(getContext()
                , new SimpleGestureDetectorDoubleTap(CanvasDrawingPath.this));
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gd.onTouchEvent(event);
            }
        });
    }

    private void initCache(int w, int h) {
        cacheBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        cacheCanvas.setBitmap(cacheBitmap);
        rectF = new RectF(0,0, w, h);
    }

    private void cleanCanvas() {
        pencilDrawer.setStyle(Paint.Style.FILL);
        pencilDrawer.setColor(Color.BLACK);
        cacheCanvas.drawRect(rectF, pencilDrawer);
        invalidate();
        preparePencilToDrawing();
    }

    private void preparePencilToDrawing() {
        pencilDrawer = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        pencilDrawer.setStyle(Paint.Style.STROKE);
        pencilDrawer.setStrokeWidth(10.0f);
        pencilDrawer.setAntiAlias(true);
        pencilDrawer.setDither(true);
        /**
         * https://developer.android.com/reference/android/graphics/Paint.Join.html
         * */
        pencilDrawer.setStrokeJoin(Paint.Join.BEVEL);
        /**
         * https://developer.android.com/reference/android/graphics/Paint.Cap.html
         * */
        pencilDrawer.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(cacheBitmap, identityMatrix, pencilDrawer);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        w = MeasureSpec.getSize(widthMeasureSpec);
        h = MeasureSpec.getSize(heightMeasureSpec);
        initCache(w, h);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventMasked = event.getActionMasked();
        float x = event.getX();
        float y = event.getY();
        switch (eventMasked) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                path.moveTo(x, y);
                drawingPath();
                break;
            case MotionEvent.ACTION_MOVE:
                path.quadTo(lastX, lastY, x, y);
                lastX = x;
                lastY = y;
                drawingPath();
                break;
            case MotionEvent.ACTION_UP:
                path.reset();
                preparePencilToDrawing();
                break;
        }
        Log.i("ON_TOUCH", String.format("%f %f -> %f %f", lastX, lastY, x, y));
        invalidate();
        return true;
    }

    private void drawingPath() {
        pencilDrawer.setColor(getRandomColor());
        cacheCanvas.drawPath(path, pencilDrawer);
    }

    @Override
    public boolean onFilterTouchEventForSecurity(MotionEvent event) {
        return super.onFilterTouchEventForSecurity(event);
    }

    @Override
    public void onDoubleTap(MotionEvent e) {
        Log.i("DOUBLE_TAP", String.format("CLEANING CANVAS %f %f"
                , e.getX(), e.getY()));
        cleanCanvas();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
