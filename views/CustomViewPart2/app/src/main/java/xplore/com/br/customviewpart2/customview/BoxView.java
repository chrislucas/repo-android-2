package xplore.com.br.customviewpart2.customview;

import android.content.Context;
import android.icu.util.MeasureUnit;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Locale;

/**
 * Created by r028367 on 10/01/2018.
 */

public class BoxView extends View {

    public BoxView(Context context) {
        super(context);
    }

    public BoxView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BoxView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i("BOX_VIEW",  String.format(Locale.getDefault()
                , "%d %d.\nW: %s\nH: %s"
                , widthMeasureSpec, heightMeasureSpec
                , MeasureSpec.toString(widthMeasureSpec)
                , MeasureSpec.toString(heightMeasureSpec)
        ));

        Log.i("BOX_VIEW", String.format(Locale.getDefault()
            ,"DP -> Pixel\nDP(W: %d H: %d)\nPixels(W: %d H: %d)\nDensity %f."
            , MeasureSpec.getSize(widthMeasureSpec)
            , MeasureSpec.getSize(heightMeasureSpec)
            , MeasureUtils.dpToPixels(getContext(), MeasureSpec.getSize(widthMeasureSpec))
            , MeasureUtils.dpToPixels(getContext(), MeasureSpec.getSize(heightMeasureSpec))
            , MeasureUtils.density(getContext())
        ));

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
