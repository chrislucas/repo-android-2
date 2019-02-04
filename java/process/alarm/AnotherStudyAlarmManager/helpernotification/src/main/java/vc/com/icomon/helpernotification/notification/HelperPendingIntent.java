package vc.com.icomon.helpernotification.notification;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;

public class HelperPendingIntent {

    private static HelperPendingIntent instance = null;
    private HelperPendingIntent() {}

    public synchronized static HelperPendingIntent getInstance() {
        if (instance == null) {
            instance = new HelperPendingIntent();
        }
        return instance;
    }

    /**
     *
     * @param flags
     * Veja as flags no fonte {@link PendingIntent}
     * */

    public PendingIntent getPendingIntentActivity(Context context, int requestCode, Intent intent
            , int flags) {
        return PendingIntent.getActivity(context, requestCode, intent, flags);
    }

    public PendingIntent getPendingIntentBroadcast(Context context, int requestCode, Intent intent
            , int flags) {
        return PendingIntent.getBroadcast(context, requestCode, intent, flags);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public PendingIntent getPendingIntentForegroundService(Context context, int requestCode, Intent intent
            , int flags) {
        return PendingIntent.getForegroundService(context, requestCode, intent, flags);
    }

    public PendingIntent getPendingIntentService(Context context, int requestCode, Intent intent
            , int flags) {
        return PendingIntent.getService(context, requestCode, intent, flags);
    }

    public PendingIntent getPendingIntentFromTaskStackBuilder(Context context, int requestCode, Intent intent
            , int flags) {
        HelperTaskStackBuilder instance = HelperTaskStackBuilder.getInstance();
        return instance.getTaskStackBuilder(context, requestCode, intent, flags).getPendingIntent(requestCode, flags);
    }
}
