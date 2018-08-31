package jobscheduler.xplorer.com.jobschedulerincommingmessage;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Message;
import android.os.Messenger;
import android.os.PersistableBundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity implements DelegateMessage {


    private ComponentName mComponentJobService;

    private HandlerMessage mHandlerMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mComponentJobService = new ComponentName(this, MyJobService.class);
        mHandlerMessage = new HandlerMessage(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Intent intentStartJobService = new Intent(this, MyJobService.class);
            intentStartJobService.putExtra(MyJobService.KEY_MESSENGER, new Messenger(mHandlerMessage));
            startService(intentStartJobService);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            JobInfo.Builder builder = new JobInfo.Builder(1, mComponentJobService);
            builder.setMinimumLatency(1000);
            //builder.setOverrideDeadline(1000);

            PersistableBundle mBundle = new PersistableBundle();
            mBundle.putLong(MyJobService.KEY_DURATION_JOB, 5000);
            builder.setExtras(mBundle);

            JobInfo jobInfo = builder.build();

            JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
            if (jobScheduler != null) {
                List<JobInfo> jobInfoList = jobScheduler.getAllPendingJobs();
                if (!jobInfoList.contains(jobInfo) )
                    jobScheduler.schedule(jobInfo);
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        boolean serviceStopped = stopService(new Intent(this, MyJobService.class));
        Log.i("STOP_SERVICE", String.valueOf(serviceStopped));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void processMessage(Message message) {
        switch (message.what) {
            case MyJobService.MSG_START:
                Log.i("MSG_START", message.obj.toString());
                break;
            case MyJobService.MSG_STOP:
                Log.i("MSG_STOP", message.obj.toString());
                break;
        }
    }

    public void cancelJobService(View view) {
        JobScheduler jobScheduler = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
            if (jobScheduler != null) {
                for (JobInfo jobInfo : jobScheduler.getAllPendingJobs()) {
                    jobScheduler.cancel(jobInfo.getId());
                }
            }
        }
    }
}
