package br.com.telecom.xplore.xplorecustomview.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by r028367 on 21/12/2017.
 */

public class CustomTextView extends AppCompatTextView {

    private Paint backgroundPaint;

    public CustomTextView(Context context) {
        super(context);
        initBackgroundPaint();
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initBackgroundPaint();
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initBackgroundPaint();
    }

    private void initBackgroundPaint() {
        backgroundPaint = new Paint();
        backgroundPaint.setColor(0xffff0000);
        backgroundPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0,0,getWidth(),getHeight(), backgroundPaint);
        super.onDraw(canvas);
    }
}
