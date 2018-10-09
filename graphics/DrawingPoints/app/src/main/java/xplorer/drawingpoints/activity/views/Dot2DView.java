package xplorer.drawingpoints.activity.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;

import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.Locale;
import java.util.Random;

import xplorer.drawingpoints.activity.helpers.ColorUtils;
import xplorer.drawingpoints.activity.helpers.ImplDoublingBufferDrawing;
import xplorer.drawingpoints.activity.helpers.MapMotionEvents;

public class Dot2DView extends View implements View.OnTouchListener {

    private final int BACKGROUND_COLOR_DEFAULT = getBackground() == null
            ? Color.WHITE : ((ColorDrawable) getBackground()).getColor();

    private Paint mPaint;
    private int mWidth, mHeight;

    private ImplDoublingBufferDrawing implDoublingBufferDrawing;

    private GestureDetector gestureDetector;

    private static final int OFFSET_DRAW_RECT_PX = 30;

    public Dot2DView(Context context) {
        super(context);
        init();
    }

    public Dot2DView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        initPencil();
        configureGestureListener();
    }

    private void initPencil() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }

    private void configureGestureListener() {
        gestureDetector = new GestureDetector(getContext()
                , new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) { }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                Log.i("DOUBLE_TAP_EVENT", MapMotionEvents.EVENTS.get(e.getActionMasked()));
                switch (e.getActionMasked()) {
                    case MotionEvent.ACTION_UP:
                        clearCanvas();

                        break;
                }
                return true;
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) { return true; }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {}

            /**
             * {@link GestureDetector.SimpleOnGestureListener#onFling(MotionEvent, MotionEvent, float, float)}
             *
             * Notifica um evento de movimento do dedo sobre a tela de forma rápida (Exemplo quando vamos passar uma ViewPager)
             * Esse evento calcula a velocidade de execução do gesto sabendo a distance entre o Ponto inicial tocado S(x,y)
             * e o ponto final E(x, y) ao longo dos eixos x e y por segundo
             *
             * */

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

                int actionMaskedEvent1 = e1.getActionMasked();
                int actionMaskedEvent2 = e2.getActionMasked();
                Log.i("ON_FLING"
                        , String.format("Event 1: %s Event 2: %s.\nVelocity X/Y: %f/%f"
                                , MapMotionEvents.EVENTS.get(actionMaskedEvent1)
                                , MapMotionEvents.EVENTS.get(actionMaskedEvent2)
                                , velocityX
                                , velocityY
                        )
                );
                return true;
            }
        });


        setOnTouchListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(implDoublingBufferDrawing.getBitmapCache()
                , implDoublingBufferDrawing.getIdentity(), mPaint);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    // Desenha primeiro no canvas de cache para repassar para o canvas normal
    private void drawDot(Canvas canvas, float left, float top, float right, float bottom) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(17.0f);
        int color = ColorUtils.getRandomColorRGB255(127, 255);
        mPaint.setColor(color);
        RectF dot = new RectF(left, top, right, bottom);
        Log.i("DRAW_DOT", String.format("%f, %f COR: %d", left, bottom, color));
        canvas.drawRect(dot, mPaint);

        // preparando a caneta
        preparePencilToDrawPositionDot();
        mPaint.setStrokeWidth(10.0f);
        // formatando o texto que colocara a posicao do ponto ao lado do ponto
        String text = String.format(Locale.getDefault(), "P(%f, %f)", left, bottom);
        // desenhando a posicao na View
        writePositionDot(canvas, text, left + OFFSET_DRAW_RECT_PX, bottom + OFFSET_DRAW_RECT_PX);

    }

    public void drawDot() {
        if (implDoublingBufferDrawing != null) {
            // Preparar o atributo paint para desenhar um ponto no canvas
            preparePencilToDrawDot();
            // escolher uma posicao aleatoria
            Random random = new Random();
            float left  = random.nextInt(mWidth - 1);
            float top   = random.nextInt(mHeight - 1);
            float right = left + OFFSET_DRAW_RECT_PX;
            float bottom = top + OFFSET_DRAW_RECT_PX;
            // desenhar o ponto no Canvas
            drawDot(implDoublingBufferDrawing.getCanvasCache(), left, top,right, bottom);

        }
    }

    private void preparePencilDefault() {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(15.0f);
        /**
         * Paint.Join enum que indica como segmentos de linhas sao juntados
         * quando desenhamos um Path. Paint.Join.BEVEL indica que o tratamento
         * sera juntar as segmentos usando retas.
         * */
        mPaint.setStrokeJoin(Paint.Join.BEVEL);
        /**
         * Paint.Cap e um enum que especifca como as extremidades de um segmento
         * de linha serao desenhados
         *
         * */
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    private void preparePencilToDrawDot() {
        preparePencilDefault();
    }

    private void preparePencilToDrawPositionDot() {
        preparePencilDefault();
    }

    private void preparePencilToCleanCanvas() {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(BACKGROUND_COLOR_DEFAULT);
    }

    private void writePositionDot(Canvas canvas, String text, float x, float y) {
        canvas.drawText(text, x, y, mPaint);
    }

    private void clearCanvas() {
        if (implDoublingBufferDrawing != null) {
            Log.i("CLEAR_SURFACE_VIEW", "CLEAR");
            preparePencilToCleanCanvas();
            implDoublingBufferDrawing
                    .getCanvasCache()
                    .drawRect(implDoublingBufferDrawing.getRectCanvas(), mPaint);
            invalidate();
        }
        else {
            Log.e("EXCEPTION_DRAW_PATH", "NOT PREPARED TO DRAW");
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidth = w;
        this.mHeight = h;
        if (implDoublingBufferDrawing == null) {
            implDoublingBufferDrawing = new ImplDoublingBufferDrawing(w, h);
        }
        else {
            implDoublingBufferDrawing.setHeight(h);
            implDoublingBufferDrawing.setWidth(w);
        }
    }
}
