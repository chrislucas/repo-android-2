package br.com.rybyalarmmanager.entities.alarm.factory;


import android.app.AlarmManager;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;

import br.com.rybyalarmmanager.entities.alarm.AbstractAlarm;
import br.com.rybyalarmmanager.entities.alarm.AlarmType;

@RequiresApi(api = Build.VERSION_CODES.N)
public class AlarmWithListener extends AbstractAlarm {

    private AlarmManager.OnAlarmListener onAlarmListener;
    private String tag;
    private Handler handler;

    public AlarmWithListener(Context context, long triggerAtMillis
            , @AlarmType int alarmType, AlarmManager.OnAlarmListener onAlarmListener, Handler handler, String tag) {
        super(context, triggerAtMillis, alarmType);
        this.onAlarmListener = onAlarmListener;
        this.tag = tag;
        this.handler = handler;
    }

    @Override
    public void start() {
        alarmManager.set(alarmType, triggerAtMillis, tag, onAlarmListener, handler);
    }


    @Override
    public void stop() {
        alarmManager.cancel(onAlarmListener);
    }

    @Override
    public boolean isRunning() {
        return super.isRunning();
    }
}
