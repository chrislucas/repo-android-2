package jobservice.xplorer.com.br.xplorerjobservice.job;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.ComponentName;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;


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

    @Override
    public boolean onStartJob(final JobParameters params) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int jobId = params.getJobId();
                Toast.makeText(
                        getApplicationContext()
                        , String.format(Locale.getDefault(), "LOG_JOB_PARAMS_START JobID: %d,", jobId)
                        , Toast.LENGTH_LONG
                ).show();
            }
        });

        thread.start();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {

        int jobId = params.getJobId();
        Toast.makeText(
                getApplicationContext()
                , String.format(Locale.getDefault(), "LOG_JOB_PARAMS_STOP JobID: %d,", jobId)
                , Toast.LENGTH_LONG
        ).show();
        jobFinished(params, false);

        return true;
    }
}
