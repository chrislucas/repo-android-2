package jobservice.xplorer.com.br.xplorerjobservice.job;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.os.Build;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class SampleJobScheduler extends JobScheduler {

    public static final String TAG = "SAMPLE_JOB_SCHEDULER";
    public static final int JOB_INFO_SAMPLE = 0x0f;

    @Override
    public int schedule(@NonNull JobInfo jobInfo) {
        return JobScheduler.RESULT_SUCCESS;
    }

    @Override
    public int enqueue(@NonNull JobInfo jobInfo, @NonNull JobWorkItem work) {
        int describeContents = work.describeContents();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int deliveryCount = work.getDeliveryCount();
        }
        Log.i("ENQUEUE_JOB", String.format("IsPeriodic: %s", jobInfo.isPeriodic()));
        return JobScheduler.RESULT_SUCCESS; // JobScheduler.RESULT_FAILURE
    }

    @Override
    public void cancel(int jobId) {
        Log.i(TAG, String.format("JobId: %d Cancelado", jobId));
    }

    @Override
    public void cancelAll() {}

    @NonNull
    @Override
    public List<JobInfo> getAllPendingJobs() {
        return new ArrayList<>();
    }

    @Nullable
    @Override
    public JobInfo getPendingJob(int jobId) {
        return null;
    }
}
