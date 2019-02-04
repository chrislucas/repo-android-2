package br.com.xplorer.bindservicewithmessegerclass.services;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import java.util.List;

/**
 * Created by r028367 on 02/04/2018.
 */

public class UtilsService {

    public static boolean isAlreadyRunning(Context context, Class<?> service) {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningServiceInfo> servicesInfo = null;
            if (activityManager != null) {
                servicesInfo = activityManager.getRunningServices(1000);
                if(servicesInfo != null) {
                    for(ActivityManager.RunningServiceInfo r : servicesInfo) {
                        Log.i("SERVICES_NAME", r.getClass().getName());
                        if (r.service.getClassName().equals(service.getName()))
                            return true;
                    }
                }
            }
        }
        return false;
    }

}
