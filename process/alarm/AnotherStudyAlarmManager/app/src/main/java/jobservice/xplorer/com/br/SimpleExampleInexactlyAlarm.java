package jobservice.xplorer.com.br;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;

import jobservice.xplorer.com.br.alarmmanager.R;
import br.com.rybyalarmmanager.entities.alarm.AlarmType;
import jobservice.xplorer.com.br.alarmmanager.services.ServiceFilterAlarm;

public class SimpleExampleInexactlyAlarm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_example_inexactly_alarm);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        long now = Calendar.getInstance().getTimeInMillis();
        long interval = 1000;

        if (alarmManager != null) {
            /**
             * Alarme baseado em {@link android.os.SystemClock#elapsedRealtime}
             * O tempo eh contado a partir dos milissegundos a partir do BOOT do S.O
             * Esse alarme nao desperta o aparelho
             * */
            Intent intentElapsedRealTime = new Intent(this, ServiceFilterAlarm.class);
            Bundle bundle = new Bundle();
            bundle.putString(ServiceFilterAlarm.KEY_STRING, "ELAPSED_REAL_TIME");
            intentElapsedRealTime.putExtras(bundle);
            alarmManager.setInexactRepeating(AlarmType.ELAPSED_REAL_TIME, now, interval,
                    PendingIntent.getService(this
                            , 0xf1, intentElapsedRealTime, 0));
            /**
             * Alarme baseado em {@link android.os.SystemClock#elapsedRealtime}
             *  O tempo eh contado a partir dos milissegundos a partir do BOOT do S.O
             *  Esse alarme desperta o aparelho
             **/
            Intent intentElapsedRealTimeWakeUp = new Intent(this, ServiceFilterAlarm.class);
            bundle = new Bundle();
            bundle.putString(ServiceFilterAlarm.KEY_STRING, "ELAPSED_REAL_TIME_WAKEUP");
            intentElapsedRealTimeWakeUp.putExtras(bundle);
            alarmManager.setInexactRepeating(AlarmType.ELAPSED_REAL_TIME_WAKEUP, now, interval * 3
                    ,PendingIntent.getService(this, 0xf2, intentElapsedRealTimeWakeUp, 0));

            /**
             * Tempo baseado no metodo {@link System#currentTimeMillis System.currentTimeMillis()}
             *
             * Esse alarme nao desperta o dispositivo e nao sera executado enquanto o celular
             * estiver 'acordado'
             *
             * */
            Intent intentRTC = new Intent(this, ServiceFilterAlarm.class);
            bundle = new Bundle();
            bundle.putString(ServiceFilterAlarm.KEY_STRING, "RTC");
            intentRTC.putExtras(bundle);
            alarmManager.setInexactRepeating(AlarmType.RTC, now, interval * 6
                    , PendingIntent.getService(this, 0xf3, intentRTC, 0));


            /**
             * Tempo baseado no metodo {@link System#currentTimeMillis System.currentTimeMillis()}
             * Alarme que desperta o dispositivo se bloqueado
             * */
            Intent intentRTCWakeUp = new Intent(this, ServiceFilterAlarm.class);
            bundle = new Bundle();
            bundle.putString(ServiceFilterAlarm.KEY_STRING, "RTC_WAKEUP");
            intentRTCWakeUp.putExtras(bundle);
            alarmManager.setInexactRepeating(AlarmType.RTC_WAKEUP, now, interval * 9
                    , PendingIntent.getService(this, 0xf4, intentRTCWakeUp, 0));
        }
    }
}
