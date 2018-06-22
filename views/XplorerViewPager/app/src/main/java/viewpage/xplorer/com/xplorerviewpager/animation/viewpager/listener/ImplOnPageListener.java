package viewpage.xplorer.com.xplorerviewpager.animation.viewpager.listener;

import android.support.v4.view.ViewPager;

import viewpage.xplorer.com.xplorerviewpager.animation.viewpager.callbacks.CallbackPageChange;

public class ImplOnPageListener implements ViewPager.OnPageChangeListener {

    private CallbackPageChange callbackPageChange;

    public ImplOnPageListener(CallbackPageChange callbackPageChange) {
        this.callbackPageChange = callbackPageChange;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        callbackPageChange.onPageScrolled(position, positionOffset, positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {
        callbackPageChange.onPageSelected(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        callbackPageChange.onPageScrollStateChanged(state);
    }
}
