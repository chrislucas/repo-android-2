package jobservice.xplorer.com.br.alarmmanager.models.alarms;


import android.app.AlarmManager;
import android.content.Context;



public abstract class AbstractAlarm<Response, Params> {

    protected AlarmManager alarmManager;
    private boolean isRunning;

    @AlarmType
    protected int alarmType;
    protected long triggerAtMillis;


    public AbstractAlarm(Context context, long triggerAtMillis,  @AlarmType int alarmType){
        this.triggerAtMillis = triggerAtMillis;
        this.alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void activeAlarm() {
        this.isRunning = true;
        start();
    }

    public void deactiveAlarm() {
        this.isRunning = false;
        stop();
    }

    public void setAlarmType(int alarmType) {
        this.alarmType = alarmType;
    }

    protected abstract void start();
    protected abstract void stop();
}
