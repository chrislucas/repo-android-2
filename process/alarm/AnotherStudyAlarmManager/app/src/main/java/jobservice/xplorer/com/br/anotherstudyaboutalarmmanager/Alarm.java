package jobservice.xplorer.com.br.anotherstudyaboutalarmmanager;


import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.os.Handler;

public class Alarm {


    private AlarmManager alarmManager;


    private static Alarm instance;


    public static Alarm getInstance(Context context) {
        if (instance == null) {
            instance = new Alarm();
            instance.alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        }
        return instance;
    }


    private Alarm() {}


    public void createInexactRepeating(@AlarmType int alarmType
            , long triggerAtMillis, long intervalMillis, PendingIntent pendingIntent) {
        instance.alarmManager.setInexactRepeating(alarmType
                , triggerAtMillis, intervalMillis, pendingIntent);
    }


    @TargetApi(Build.VERSION_CODES.N)
    public void createAlarmWithListenr(@AlarmType int alarmType
            , long triggerAtMillis, String tag, AlarmManager.OnAlarmListener onAlarmListener, Handler handler) {

        instance.alarmManager.set(alarmType, triggerAtMillis, tag, onAlarmListener, handler);
    }

}
