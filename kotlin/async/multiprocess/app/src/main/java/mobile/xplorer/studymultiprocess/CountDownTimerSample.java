package mobile.xplorer.studymultiprocess;

import android.os.CountDownTimer;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class CountDownTimerSample extends AppCompatActivity {

    CountDownTimer c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down_timer_sample);
        c = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                ((TextView) findViewById(R.id.count_down)).setText(String.valueOf(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {}
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        c.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        c.cancel();
    }
}
