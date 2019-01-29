package jobservice.xplorer.com.br.xplorerjobservice.activities;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobWorkItem;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;

import java.util.List;

import jobservice.xplorer.com.br.xplorerjobservice.R;
import jobservice.xplorer.com.br.xplorerjobservice.job.SampleJobScheduler;
import jobservice.xplorer.com.br.xplorerjobservice.job.SampleJobService;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        /**
         * API {@link JobScheduler}
         * api para agendar varios tipos de Jobs que serao executados no processo dapropria aplicacao
         * */

        // ou
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //
            final JobScheduler jobScheduler = SampleJobService.schedulePeriodicJobDefault(this);
            if (jobScheduler != null) {
                findViewById(R.id.cancel_job).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        List<JobInfo> allPendingJobs = jobScheduler.getAllPendingJobs();
                        for(JobInfo jobInfo : allPendingJobs) {
                            Log.i("CANCEL_JOB", jobInfo.toString());
                            jobScheduler.cancel(jobInfo.getId());
                        }
                    }
                });
            }
        }

        else {
            final SampleJobScheduler jobScheduler = ContextCompat.getSystemService(this, SampleJobScheduler.class);
        }
    }
}
