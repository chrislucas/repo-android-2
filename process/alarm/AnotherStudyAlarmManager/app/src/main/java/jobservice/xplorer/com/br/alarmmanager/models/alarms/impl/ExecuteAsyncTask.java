package jobservice.xplorer.com.br.alarmmanager.models.alarms.impl;

import android.os.AsyncTask;

public class ExecuteAsyncTask<T> extends AsyncTask<Void, Void, T> {

    @Override
    protected T doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(T t) {
        super.onPostExecute(t);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(T t) {
        super.onCancelled(t);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}
