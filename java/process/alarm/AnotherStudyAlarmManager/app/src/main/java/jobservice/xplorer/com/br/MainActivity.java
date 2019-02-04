package jobservice.xplorer.com.br;

import android.app.AlarmManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import jobservice.xplorer.com.br.alarmmanager.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            AlarmManager.OnAlarmListener onAlarmListener = new AlarmManager.OnAlarmListener() {
                @Override
                public void onAlarm() {

                }
            };
        }
    }
}
