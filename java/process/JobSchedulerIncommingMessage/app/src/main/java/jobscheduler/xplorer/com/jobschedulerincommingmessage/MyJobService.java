package jobscheduler.xplorer.com.jobschedulerincommingmessage;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.RequiresApi;
import android.util.Log;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyJobService extends JobService {

    private Messenger mMessenger;


    public static final String KEY_MESSENGER = "KEY_MESSENGER";
    public static final String KEY_DURATION_JOB = "KEY_DURATION_JOB";


    public static final int MSG_START = 0x01;
    public static final int MSG_STOP = 0x02;

    public MyJobService() {
    }

    @Override
    public boolean onStartJob(final JobParameters params) {
        sendMessage(MSG_START, params.getJobId());
        long duration = params.getExtras().getLong(KEY_DURATION_JOB);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sendMessage(MSG_STOP, params.getJobId());
                jobFinished(params, false);
            }
        }, duration);

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        sendMessage(MSG_STOP, params.getJobId());
        return false;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mMessenger = intent.getParcelableExtra(KEY_MESSENGER);
        return START_NOT_STICKY;
    }


    private void sendMessage(int messageId, Object data) {
        if (mMessenger != null) {
            Message message = Message.obtain();
            try {
                message.what = messageId;
                message.obj = data;
                mMessenger.send(message);
            }
            catch (RemoteException e) {
                Log.e("TAG", e.getMessage());
            }
        }
        // TODO poderia gerar um log :)

    }
}
