package xplore.com.br.customviewpart2.customview;

import android.content.Context;

/**
 * Created by r028367 on 10/01/2018.
 */

public class MeasureUtils {

    public static double density(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static double densityDpi(Context context) {
        return context.getResources().getDisplayMetrics().densityDpi;
    }

    public static double scaledDensity(Context context) {
        return context.getResources().getDisplayMetrics().scaledDensity;
    }


    public static double widthPixels(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static double heightPixels(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int dpToPixels(Context context, int dp) {
        return (int) (dp * density(context) + .5f);
    }

    public static int pixelsToDp(Context context, int pixels) {
        return (int) (pixels / density(context) + .5f);
    }

}
