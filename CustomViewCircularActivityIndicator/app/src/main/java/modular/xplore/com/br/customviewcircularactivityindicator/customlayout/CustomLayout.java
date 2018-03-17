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
        int rowHeight = 0, maxWidth = 0, maxHeight = 0, left = 0, top = 0;

        Log.i("DIMENSION_ON_MEASURE", String.format("Dimension(%d, %d)", getWidth(), getHeight()));
        Log.i("DIMENSION_ON_MEASURE", String.format("Dimension SPEC(%d, %d)", widthMeasureSpec, heightMeasureSpec));

        for (int idx = 0; idx < count ; idx++) {
            View childView = getChildAt(idx);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);

            /**
             * Largura bruta do componente, valor resultante de uma operacao de bit usando uma mascara
             * {@link ViewGroup#MEASURED_SIZE_MASK
             * */

            int childWidth = childView.getMeasuredWidth();
            /**
             * Altura bruta do componente ...
             * */
            int childHeight = childView.getMeasuredHeight();

            /**
             * Se a View atual mais a largura do que ja foi ocupado
             * for menor do que a largura total, adicione o elemento
             * na linha e contabilize o espaco ocupado
             * */
            if(left + childWidth < getWidth()) {
                left += childWidth;
            }
            /**
             * Do contrario, ponha a view na linha abaixo
             * */
            else {
                if(left > maxWidth)
                    maxWidth = left;
                // zere o contador de largura
                left = 0;
                top += rowHeight;
                rowHeight = 0;
            }
            // pegando a altura do maior componente
            if (childHeight > rowHeight)
                rowHeight = childHeight;
        }

        if (left > maxWidth)
            maxWidth = left;
        maxHeight = top + rowHeight;
        /**
         *
         * */
        setMeasuredDimension(getMeasure(widthMeasureSpec, maxWidth), getMeasure(heightMeasureSpec, maxHeight));
    }


    private int getMeasure(int spec, int desired) {
        /***
         *
         *
         * */
        int sizeSpec = MeasureSpec.getSize(spec);
        switch (MeasureSpec.getMode(spec)) {
            case MeasureSpec.EXACTLY:
                return sizeSpec;

            case MeasureSpec.AT_MOST:
                return Math.min(sizeSpec, desired);

            case MeasureSpec.UNSPECIFIED:
            default:
                    return desired;
        }
    }
}
