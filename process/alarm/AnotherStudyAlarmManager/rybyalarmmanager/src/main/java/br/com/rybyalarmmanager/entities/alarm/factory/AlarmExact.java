package br.com.rybyalarmmanager.entities.alarm.factory;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.os.Handler;


import br.com.rybyalarmmanager.entities.alarm.AbstractAlarm;


public class AlarmExact extends AbstractAlarm {

    private PendingIntent pendingIntent;
    private AlarmManager.OnAlarmListener onAlarmListener;
    private Handler handler;
    private String tag;

    public AlarmExact(Context context, long triggerAtMillis, int alarmType, PendingIntent pendingIntent) {
        super(context, triggerAtMillis, alarmType);
        this.pendingIntent = pendingIntent;
    }

    public AlarmExact(Context context, long triggerAtMillis, int alarmType
            , String tag, AlarmManager.OnAlarmListener onAlarmListener, Handler handler) {
        super(context, triggerAtMillis, alarmType);
        this.tag = tag;
        this.onAlarmListener = onAlarmListener;
        this.handler = handler;
    }



    @Override
    protected void start() {
        if (onAlarmListener != null && handler != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            alarmManager.setExact(alarmType, triggerAtMillis, tag,  onAlarmListener, handler);
        }

        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(alarmType, triggerAtMillis, pendingIntent);
        }
    }

    @Override
    protected void stop() {
        if (pendingIntent != null)
            alarmManager.cancel(pendingIntent);
        else if (onAlarmListener != null) {
            alarmManager.cancel(onAlarmListener);
        }
    }
}
