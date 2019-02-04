package viewpage.xplorer.com.xplorerviewpager.animation.viewpager.transformer;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by r028367 on 11/01/2017.
 */

public class ZoomOutPageTransforner implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.2f;

    @Override
    public void transformPage(@NonNull View page, float position) {
        int width = page.getWidth();
        int height = page.getHeight();

        if(position < -1) {
            page.setAlpha(0.0f);
        }
        else if(position <= 1) {
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            float vertMargin  = height * (1 - scaleFactor) / 2;
            float horzMargin  = width * (1 - scaleFactor) / 2;
            if(position < 0) {
                page.setTranslationX(horzMargin - vertMargin / 2);
            } else {
                page.setTranslationX(-horzMargin + vertMargin / 2);
            }
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            page.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE)/(1-MIN_SCALE)*(1-MIN_ALPHA));
        }
        else {
            page.setAlpha(0.0f);
        }
    }
}


