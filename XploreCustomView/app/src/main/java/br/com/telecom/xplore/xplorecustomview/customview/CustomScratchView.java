package br.com.telecom.xplore.xplorecustomview.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import br.com.telecom.xplore.xplorecustomview.R;

/**
 * Created by r028367 on 22/12/2017.
 */

public class CustomScratchView extends View {

    Paint paintStroke, paintFill;

    public CustomScratchView(Context context) {
        super(context);
        initPaint();
    }

    public CustomScratchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public CustomScratchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        paintStroke = new Paint();
        paintFill = new Paint();

        paintFill.setStyle(Paint.Style.FILL);
        paintFill.setColor(ContextCompat.getColor(getContext(), R.color.background_scratch));

        paintStroke.setStyle(Paint.Style.STROKE);
        paintStroke.setColor(0xff001299);
        paintStroke.setStrokeWidth(3.0f);
        paintStroke.setStrokeCap(Paint.Cap.SQUARE);
        paintStroke.setStrokeJoin(Paint.Join.MITER);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                Log.i("ON_TOUCH_EVENT_DOWN", String.format("%f %f", x, y));
                break;
            case MotionEvent.ACTION_UP:
                Log.i("ON_TOUCH_EVENT_UP", String.format("%f %f", x, y));
                break;
        }
        return true; //super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0,0,getWidth(), getHeight(), paintFill);
        canvas.drawRect(0,0,getWidth(), getHeight(), paintStroke);
        super.onDraw(canvas);
    }
}
