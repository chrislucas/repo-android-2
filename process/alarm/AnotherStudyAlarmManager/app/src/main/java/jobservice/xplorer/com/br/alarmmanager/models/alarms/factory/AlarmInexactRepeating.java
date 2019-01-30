package jobservice.xplorer.com.br.alarmmanager.models.alarms.factory;


import android.app.PendingIntent;
import android.content.Context;


import jobservice.xplorer.com.br.alarmmanager.models.alarms.AbstractAlarm;
import jobservice.xplorer.com.br.alarmmanager.models.alarms.AlarmType;


public class AlarmInexactRepeating extends AbstractAlarm {


    private long intervalMillis;
    private PendingIntent pendingIntent;

    public AlarmInexactRepeating(Context context, long triggerAtMillis
            , @AlarmType int alarmType, long intervalMillis, PendingIntent pendingIntent) {
        super(context, triggerAtMillis, alarmType);
        this.intervalMillis = intervalMillis;
        this.pendingIntent = pendingIntent;
    }


    @Override
    protected void start() {
        alarmManager.setInexactRepeating(alarmType, triggerAtMillis, intervalMillis, pendingIntent);
    }

    @Override
    protected void stop() {
        alarmManager.cancel(pendingIntent);
    }

    @Override
    public boolean isRunning() {
        return super.isRunning();
    }
}
