package xplore.com.br.xplorerscreenoverlay.customviews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.support.v7.widget.AppCompatButton;

/**
 * Created by r028367 on 18/01/2018.
 */

public class DraggableButton extends AppCompatButton {

    private ImplDefaultDraggableView implDefaultDraggableView;

    public DraggableButton(Context context) {
        super(context);
    }

    public DraggableButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DraggableButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {
        implDefaultDraggableView = new ImplDefaultDraggableView(this, context, attributeSet);

    }
}
