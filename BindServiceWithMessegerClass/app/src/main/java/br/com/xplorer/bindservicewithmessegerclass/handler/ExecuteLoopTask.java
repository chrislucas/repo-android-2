package br.com.xplorer.bindservicewithmessegerclass.handler;

import android.os.Handler;

/**
 * Created by r028367 on 02/04/2018.
 */

public class ExecuteLoopTask {

    public interface Callback {
        void executeTask();
    }

    private Handler handler;
    private Runnable runnable;

    public ExecuteLoopTask(final Callback callback, final long interval) {
        this.handler = new Handler();
        init(callback, interval);
    }

    public ExecuteLoopTask(final Handler handler, final Callback callback, final  long interval) {
        this.handler = handler;
        init(callback, interval);
    }

    private void init(final Callback callback, final long interval) {
        this.handler.removeCallbacks(this.runnable);
        this.runnable = new Runnable() {
            @Override
            public void run() {
                callback.executeTask();
                handler.postDelayed(this, interval);
            }
        };
    }

    public void start() {
        this.runnable.run();
    }

    public void stop() {
        this.handler.removeCallbacks(this.runnable);
    }

}
