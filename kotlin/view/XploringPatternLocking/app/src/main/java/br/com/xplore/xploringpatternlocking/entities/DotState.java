package br.com.xplore.xploringpatternlocking.entities;

import android.animation.ValueAnimator;

/**
 * Created by C_Luc on 14/01/2018.
 */

public class DotState {
    private float mSize;
    private ValueAnimator mLineAnimator;

    public static final float mScale = 1.0f;
    public static final float mTranslateY = 0.0f;
    public static final float mAlpha = 1.0f;
    public static final float mLineEndX = Float.MIN_VALUE;
    public static final float mLineEndY = Float.MIN_VALUE;

    public float getmSize() {
        return mSize;
    }

    public void setmSize(float mSize) {
        this.mSize = mSize;
    }

    public ValueAnimator getmLineAnimator() {
        return mLineAnimator;
    }

    public void setmLineAnimator(ValueAnimator mLineAnimator) {
        this.mLineAnimator = mLineAnimator;
    }
}
