package jobservice.xplorer.com.br.xplorerjobservice.job;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
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
    public static final int JOB_ID_SAMPLE = 1;
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
                /**
                 * A chamada ao metodo jobFinished() indica ao sistema que o serviço acabou
                 * e a tarefa nao precisa mais ser reagendada
                 * */
                jobFinished(params, false);
            }
        });
        thread.start();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        int jobId = params.getJobId();
        Log.i("ON_STOP_JOB"
                , String.format(Locale.getDefault()
                        , "LOG_JOB_PARAMS_STOP JobID: %d,", jobId));
        jobFinished(params, false);
        if (thread != null) {
            try {
                thread.join();
            }
            catch (InterruptedException e) {
                Log.e("INTERRUPTED_EXCEPTION", e.getMessage());
            }
        }
        return true;
    }
}
