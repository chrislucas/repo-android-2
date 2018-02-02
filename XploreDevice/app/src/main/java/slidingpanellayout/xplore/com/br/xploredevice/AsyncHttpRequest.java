package slidingpanellayout.xplore.com.br.xploredevice;

import android.os.AsyncTask;

/**
 * Created by r028367 on 30/01/2018.
 */

public class AsyncHttpRequest<Params, Result> extends AsyncTask<Params, Integer, Result> {


    private final String url;
    private final CallbackPostExecute<Result> callbackPostExecute;

    public interface CallbackPostExecute<Result> {
        void execute(Result result);
    }

    public AsyncHttpRequest(String url, CallbackPostExecute<Result> callbackPostExecute) {
        this.url = url;
        this.callbackPostExecute = callbackPostExecute;
    }

    @Override
    protected Result doInBackground(Params[] params) {
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Result result) {
        super.onPostExecute(result);
        callbackPostExecute.execute(result);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(Result result) {
        super.onCancelled(result);
        callbackPostExecute.execute(result);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}
