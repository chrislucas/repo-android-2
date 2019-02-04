package actions;

import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by r028367 on 12/01/2018.
 */

public class SimpleGestureDetectorDoubleTap extends GestureDetector.SimpleOnGestureListener {

    public interface OnDoubleTapListener {
        void onDoubleTap(MotionEvent event);
    }

    private OnDoubleTapListener onDoubleTapListener;

    public SimpleGestureDetectorDoubleTap(OnDoubleTapListener onDoubleTapListener) {
        this.onDoubleTapListener = onDoubleTapListener;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        onDoubleTapListener.onDoubleTap(e);
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return super.onFling(e1, e2, velocityX, velocityY);
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return super.onSingleTapUp(e);
    }
}
