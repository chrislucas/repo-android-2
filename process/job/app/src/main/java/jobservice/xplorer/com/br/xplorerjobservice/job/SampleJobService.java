package jobservice.xplorer.com.br.xplorerjobservice.job;

import android.app.Notification;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;

import jobservice.xplorer.com.br.xplorerjobservice.R;
import jobservice.xplorer.com.br.xplorerjobservice.helpers.notification.HelperNotification;


/**
 *
 * Enpoint para um callback de {@link android.app.job.JobScheduler}
 *
 * Esse serviiço executa cada Job num {@link android.os.Handler} executado
 * na Main Thread. isso siginifica que a implementação do Job deve ser feita
 * dentro de uma {@link Thread}/{@link android.os.AsyncTask}/{@link android.os.Handler}.
 *
 * Se isso nao for feito, corremos o risco de bloquear o uso da camada visual do usuario e
 * chamadas callcabks futuras para JobManager, especialmene a chamada do metodo onStartJob
 *
 * */

/**
 * Denota que tal recurso so deve ser chamado se a API do celular que esta executando deve ser >=
 * a da anotacao.
 * */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class SampleJobService extends JobService {
    public static final int JOB_ID_SAMPLE = 1;

    public static final String CHANNEL = "CHANNEL_SAMPLE_JOB_SERVICE";
    public static final int JOB_ID_NOTIFICATION = 0x33;

    private Thread thread;

    @Override
    public boolean onStartJob(final JobParameters params) {
        //final Context context = this;
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int jobId = params.getJobId();
                Log.i("ON_START_JOB"
                        , String.format(Locale.getDefault()
                                , "LOG_JOB_PARAMS_START JobID: %d,", jobId));

                HelperNotification helperNotification =  HelperNotification.getInstance();
                NotificationCompat.Builder builder = helperNotification
                        .getNotificationCompatBuilder(getApplicationContext()
                            , "Exemplo de notificação"
                            , "Essa notificação foi criada por um Exemplo de JobScheduler"
                            , CHANNEL
                        );


                NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle()
                        .bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.bigpic))
                        .bigLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.big_ic_notification_sample));

                Notification n = builder
                        .setAutoCancel(true)
                        //.setStyle(bigPictureStyle)
                        .setSmallIcon(R.drawable.ic_notifications)
                        .build();



                helperNotification.show(getApplicationContext(), n, JOB_ID_NOTIFICATION);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    refreshJobScheduler();
                }
                /**
                 * A chamada ao metodo jobFinished() indica ao sistema que o serviço acabou
                 * e a tarefa nao precisa mais ser reagendada
                 * */
                //jobFinished(params, false);
            }
        });
        thread.start();
        return true;
    }

    private void refreshJobScheduler() {
        JobScheduler mJobScheduler = (JobScheduler) getApplicationContext().getSystemService(JOB_SCHEDULER_SERVICE);
        ComponentName mComponentName = new ComponentName(getPackageName(), getClass().getName());
        JobInfo.Builder mBuilder = new JobInfo.Builder(JOB_ID_SAMPLE, mComponentName);

        mBuilder.setMinimumLatency(2000);
        if (mJobScheduler != null) {
            mJobScheduler.schedule(mBuilder.build());
        }
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        int jobId = params.getJobId();
        Log.i("ON_STOP_JOB"
                , String.format(Locale.getDefault()
                        , "LOG_JOB_PARAMS_STOP JobID: %d,", jobId));
        /*
        jobFinished(params, false);
        if (thread != null) {
            try {
                thread.join();
            }
            catch (InterruptedException e) {
                Log.e("INTERRUPTED_EXCEPTION", e.getMessage());
            }
        }
        */
        return false;
    }
}
