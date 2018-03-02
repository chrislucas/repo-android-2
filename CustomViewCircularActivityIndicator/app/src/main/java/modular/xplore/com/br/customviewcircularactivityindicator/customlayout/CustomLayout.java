package modular.xplore.com.br.customviewcircularactivityindicator.customlayout;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by r028367 on 02/03/2018.
 */

public class CustomLayout extends ViewGroup {


    public CustomLayout(Context context) {
        super(context);
    }

    public CustomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int width = l + getPaddingLeft();
        int height = t + getPaddingTop();


        Log.i("DIMENSION_LAYOUT"
                , String.format("Left: %d\nTop: %d\nRight: %d\nBottom: %d", l, t, r, b));

        int rowHeight = 0;
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            int childViewWidth = childView.getMeasuredWidth();
            int childViewHeight = childView.getMeasuredHeight();

            /**
             * O componente ainda cabe linha
             * */
            if (width + childViewWidth < r - getPaddingRight()) {
                childView.layout(width, height, width + childViewWidth, height + childViewHeight);
                width += childViewWidth;
            }
            /**
             * Senao inicie uma nova linha
             * */
            else {
                //  redefinindo a largura que sera utilizada na linha
               width = getPaddingLeft() + l;
               // adicionando ao espaco ocupado na tela a altura da linha anterior
               height += rowHeight;
               rowHeight = 0;
            }

            /**
             * Se a viu que esta no layout for maior que a maior altura corrente
             * a nova maior altura passa a ser a da View corrente
             * */
            if(childViewHeight > rowHeight)
                rowHeight = childViewHeight;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();

        for (int idx = 0; idx < count ; idx++) {
            View childView = getChildAt(idx);

        }
    }
}
