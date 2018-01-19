package xplore.com.br.xplorerscreenoverlay.utils;

import android.util.DisplayMetrics;
import android.util.Log;

/**
 * Created by r028367 on 19/01/2018.
 */

public class DisplayMetricsUtils {

    public static void log(DisplayMetrics displayMetrics) {
        StringBuilder fmt = new StringBuilder();
        fmt.append("Quantidade de pixels na largura %d.\n");
        fmt.append("Quantidade de pixels na altura %d.\n");
        fmt.append("Densidade %f.\n");
        fmt.append("Densidade DPI %d.\n");
        fmt.append("Escala de densidade %f.\n");
        fmt.append("XDPI %f.\n");
        fmt.append("YDPI %f.\n");
        String message = String.format(fmt.toString()
                , displayMetrics.widthPixels
                , displayMetrics.heightPixels
                , displayMetrics.density
                , displayMetrics.densityDpi
                , displayMetrics.scaledDensity
                , displayMetrics.xdpi
                , displayMetrics.ydpi
        );
        Log.i("DISPLAY_METRICS", message);
    }

}
