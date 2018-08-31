package jobservice.xplorer.com.br.anotherstudyaboutalarmmanager;

import android.app.AlarmManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Alarm alarm = Alarm.getInstance(this);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            AlarmManager.OnAlarmListener onAlarmListener = new AlarmManager.OnAlarmListener() {
                @Override
                public void onAlarm() {

                }
            };

        }

    }
}
