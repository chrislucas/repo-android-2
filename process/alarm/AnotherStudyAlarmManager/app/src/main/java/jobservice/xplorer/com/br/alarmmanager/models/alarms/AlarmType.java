package jobservice.xplorer.com.br.alarmmanager.models.alarms;

import android.app.AlarmManager;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;



@Retention(RetentionPolicy.SOURCE)
@IntDef({AlarmType.RTC_WAKEUP, AlarmType.RTC, AlarmType.ELAPSED_REAL_TIME, AlarmType.ELAPSED_REAL_TIME_WAKEUP})
public @interface AlarmType {
    int RTC_WAKEUP = AlarmManager.RTC_WAKEUP;
    int RTC=  AlarmManager.RTC;
    int ELAPSED_REAL_TIME =  AlarmManager.ELAPSED_REALTIME;
    int ELAPSED_REAL_TIME_WAKEUP =  AlarmManager.ELAPSED_REALTIME_WAKEUP;
}
