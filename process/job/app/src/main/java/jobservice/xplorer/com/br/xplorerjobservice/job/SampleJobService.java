package jobservice.xplorer.com.br.xplorerjobservice.job;

import android.app.Activity;
import android.app.Notification;
import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentProvider;
import android.content.Context;
import android.media.RingtoneManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    public static final int JOB_ID = 1;

    public static final String CHANNEL = "CHANNEL_SAMPLE_JOB_SERVICE";
    public static final int ID_NOTIFICATION = 0x33;

    private Thread thread;

    private static final SimpleDateFormat simpledateFormat =
            new SimpleDateFormat("kk:mm:ss", Locale.getDefault());

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
                Date now = new Date(Calendar.getInstance().getTimeInMillis());
                HelperNotification helperNotification =  HelperNotification.getInstance();
                NotificationCompat.Builder builder = helperNotification
                        .getNotificationCompatBuilder(getApplicationContext()
                            , "Exemplo de notificação"
                            , String.format("Exemplo de JobScheduler %s"
                                        , simpledateFormat.format(now))
                            , CHANNEL
                        );
/*
                NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle()
                        .bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.bigpic))
                        .bigLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.big_ic_notification_sample));
*/
                Notification n = builder
                        .setAutoCancel(true)
                        //.setStyle(bigPictureStyle)
                        .setSmallIcon(R.drawable.ic_notifications)
                        .setVibrate(new long[] {100, 100, 100})
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                        .setDefaults(NotificationCompat.DEFAULT_LIGHTS | NotificationCompat.DEFAULT_SOUND)
                        .build();
                n.flags |= NotificationCompat.FLAG_AUTO_CANCEL | NotificationCompat.FLAG_SHOW_LIGHTS;

                helperNotification.show(getApplicationContext(), n, ID_NOTIFICATION);

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
        JobInfo.Builder mBuilder = new JobInfo.Builder(JOB_ID, mComponentName);
        mBuilder.setMinimumLatency(1000);
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
        return false;
    }

    private void killThread(JobParameters params) {
        jobFinished(params, false);
        if (thread != null) {
            try {
                thread.join();
            }
            catch (InterruptedException e) {
                Log.e("INTERRUPTED_EXCEPTION", e.getMessage());
            }
        }
    }

    public static JobScheduler schedulePeriodicJobDefault(Context context) {
        final JobScheduler jobScheduler = (JobScheduler)
                context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        if (jobScheduler != null) {
            int result = jobScheduler.schedule(createPeriodicJobInfoDefault(context));
            Log.i(SampleJobScheduler.TAG
                    , result == JobScheduler.RESULT_SUCCESS ? "SUCCESS" : "FAILURE");
        }
        return jobScheduler;
    }


    public static JobInfo createPeriodicJobInfoDefault(Context context) {
        /**
         * https://developer.android.com/reference/android/content/ComponentName
         * Identificador de um componente de aplicacao especifico.
         *
         * Os componentes de aplicacao sao
         *
         * {@link Activity}
         * {@link Service}
         * {@link BroadcastReceiver}
         * {@link ContentProvider}
         *
         * Para identificar um componente precisamos de 2 informacoes
         *
         * Uma string que informe o pacote da aplicacao: com.example.qualquercoisa
         * O nome da classe do componente: Classe.class.getName() que retornar o nome
         * completo (com o pacote)
         *
         * Exemplos de uso
         * https://www.codota.com/code/java/classes/android.content.ComponentName
         *
         * intent.setComponent(new ComponentName("com.example", "com.example.MyExampleActivity"));
         * */

        ComponentName componentJobService = new ComponentName(context
                , SampleJobService.class.getName());
        //ComponentName componentJobService = new ComponentName(this, SampleJobService.class);
        JobInfo.Builder builder = new JobInfo
                .Builder(JOB_ID, componentJobService)
                // O dispositivo nao precisa estar carregando
                .setRequiresCharging(false)
                //
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);

        /**
         *
         * */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // nao eh possivel chamar o metodo abaixo para um Job periodico
            // builder.setMinimumLatency(1500);
            /**
             * A partir do android Nougat
             * */
            builder.setPeriodic(JobInfo.getMinPeriodMillis()/*, JobInfo.getMinFlexMillis()*/);
        }
        else {
            builder.setPeriodic(90000);
        }


        // builder.setMinimumLatency(1);       // atrasa o inicio do Job no minimo por N milissegundos
        // builder.setOverrideDeadline(1);     // atrasa o fim do Job no minimo por N milissegundos

        /**
         * Discussao sobre JobScheduler no android Nougat
         * https://stackoverflow.com/questions/38344220/job-scheduler-not-running-on-android-n
         * */
        return builder.build();
    }
}
