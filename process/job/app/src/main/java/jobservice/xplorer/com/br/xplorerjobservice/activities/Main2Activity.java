package jobservice.xplorer.com.br.xplorerjobservice.activities;

import android.app.Activity;
import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentProvider;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //
        }
        // ou
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            final JobScheduler jobScheduler = (JobScheduler)
                    getSystemService(Context.JOB_SCHEDULER_SERVICE);
            //final JobScheduler jobScheduler = (JobScheduler) getSystemService(JobScheduler.class);
            //final SampleJobScheduler jobScheduler = getSystemService(SampleJobScheduler.class);


            /**
             * https://developer.android.com/reference/android/content/ComponentName
             * Identiticador de um componente de aplicacao especifico.
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
            String packageName = getPackageName();
            String className = SampleJobService.class.getName();
            ComponentName componentJobService = new ComponentName(packageName, className);
            //ComponentName componentJobService = new ComponentName(this, SampleJobService.class);
            JobInfo.Builder builder = new JobInfo
                    .Builder(SampleJobService.JOB_ID_SAMPLE, componentJobService)
                    // intervalo de repeticao
                    // O dispositivo nao precisa estar carregando
                    .setRequiresCharging(false)
                    //
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);

            /**
             * Discussao sobre JobScheduler no android Nougat
             * https://stackoverflow.com/questions/38344220/job-scheduler-not-running-on-android-n
             * */

            long m = builder.build().getMinLatencyMillis();
            Log.i("MIN_LATENCY_MILLIS", String.valueOf(m));


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                //builder.setMinimumLatency(1500);
                /**
                 * A partir do android Nougat
                 * */
                //builder.setPeriodic(1500, 3000);
            }
            else {
                builder.setPeriodic(3000);
            }

            //builder.setMinimumLatency(1);       // atrasa o inicio do Job no minimo por N milissegundos
            //builder.setOverrideDeadline(1);     // atrasa o fim do Job no minimo por N milissegundos

            if (jobScheduler != null) {
                JobInfo jobInfo = builder.build();
                int result = jobScheduler.schedule(jobInfo);
                Log.i(SampleJobScheduler.TAG
                        , result == JobScheduler.RESULT_SUCCESS ? "SUCCESS" : "FAILURE");
                findViewById(R.id.cancel_job).setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
    }
}
