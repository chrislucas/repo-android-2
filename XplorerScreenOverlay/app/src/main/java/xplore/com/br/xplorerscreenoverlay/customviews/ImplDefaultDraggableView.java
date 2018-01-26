package xplore.com.br.xplorerscreenoverlay.customviews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import xplore.com.br.xplorerscreenoverlay.R;
import xplore.com.br.xplorerscreenoverlay.listeners.ImplDefaultDraggable;

/**
 * Created by r028367 on 19/01/2018.
 */

public class ImplDefaultDraggableView {
    private final int [] position = new int[2];
    private boolean draggable;
    private View view;

    private ImplDefaultDraggable implDefaultDraggable;

    public ImplDefaultDraggableView(View view, Context context, AttributeSet attributeSet) {
        @SuppressLint("Recycle")
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.DraggableView);
        for (int i = 0; i < typedArray.getIndexCount() ; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.DraggableView_draggable:
                    draggable = typedArray.getBoolean(i, false);
                    break;
            }
        }
    }



    private void toggleOnDraggable() {
        draggable = ! draggable;
    }
}
