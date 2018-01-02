package br.com.telecom.xplore.xplorecustomview.gesture;

import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by r028367 on 02/01/2018.
 */

public class DetectDoubleTap extends GestureDetector.SimpleOnGestureListener {

    public interface OnDoubleTap {
        void doSomeThing();
    }

    private OnDoubleTap onDoubleTap;

    public DetectDoubleTap(OnDoubleTap onDoubleTap) {
        this.onDoubleTap = onDoubleTap;
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return super.onSingleTapUp(e);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return super.onFling(e1, e2, velocityX, velocityY);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return super.onDown(e);
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        onDoubleTap.doSomeThing();
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return super.onDoubleTapEvent(e);
    }
}
