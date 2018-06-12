package br.com.xplorer.bindservicewithmessegerclass.handler;

import android.os.Handler;
import android.os.Message;


/**
 * Created by r028367 on 02/04/2018.
 */

public class HandlerTask extends Handler {

    protected Callback callback;

    public interface Callback {
       void execute(Message message);
    }

    public HandlerTask(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void handleMessage(Message msg) {
        this.callback.execute(msg);
    }
}
