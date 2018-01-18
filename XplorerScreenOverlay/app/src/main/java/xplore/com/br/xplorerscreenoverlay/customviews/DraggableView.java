package xplore.com.br.xplorerscreenoverlay.customviews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by r028367 on 18/01/2018.
 */

public class DraggableView extends View implements Draggable {

    protected final int [] originCoord;

    public DraggableView(Context context) {
        super(context);
        originCoord = new int[2];
    }

    public DraggableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        originCoord = new int[2];
    }

    public DraggableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        originCoord = new int[2];
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    public int[] getOriginCoord() {
        return originCoord;
    }
}
