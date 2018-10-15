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

    private static final int OFFSET_DRAW_RECT_PX = 35;

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
                Log.i("DOUBLE_TAP_EVENT"
                        , MapMotionEvents.EVENTS.get(e.getActionMasked()));
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
    private void drawSquare(Canvas canvas, float left, float top, float right, float bottom, int color) {
        RectF dot = new RectF(left, top, right, bottom);
        canvas.drawRect(dot, mPaint);
        // preparando a caneta
        preparePencilToWritePositionOfSquare();
        // mesma cor do quadrado
        mPaint.setColor(color);
        // formatando o texto que colocara a posicao do ponto ao lado do ponto
        String text = String.format(Locale.getDefault(), "P(%.2f, %.2f)", left, bottom);
        Log.i("DRAW_DOT", String.format("%f, %f, %f, %f ON %s", left, top, right, bottom, text));
        // desenhando a posicao na View
        writePositionDot(canvas, text, left + OFFSET_DRAW_RECT_PX * 2.0f, (bottom + top) / 2);
    }

    public void drawSquare() {
        if (implDoublingBufferDrawing != null) {
            // Preparar o atributo paint para desenhar um ponto no canvas
            int color = preparePencilToDrawSquare();
            // escolher uma posicao aleatoria
            Random random = new Random();
            float left  = random.nextInt(mWidth - 1);
            float top   = random.nextInt(mHeight - 1);
            float right = left + OFFSET_DRAW_RECT_PX * 1.5f;
            float bottom = top + OFFSET_DRAW_RECT_PX * 1.5f;
            // desenhar o ponto no Canvas
            drawSquare(implDoublingBufferDrawing.getCanvasCache(), left, top, right, bottom, color);
            invalidate();
        }
    }

    private void preparePencilDefault() {
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

    private int preparePencilToDrawSquare() {
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setStrokeWidth(17.0f);
        int color = ColorUtils.getRandomColorRGB255(70, 150);
        this.mPaint.setColor(color);
        return color;
    }

    private void preparePencilToWritePositionOfSquare() {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5.0f);
        mPaint.setTextSize(28.0f);
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
