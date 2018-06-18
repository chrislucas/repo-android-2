package br.com.xplorer.bindservicewithmessegerclass.handler;

import android.app.Service;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by r028367 on 02/04/2018.
 */

public class HandlerService extends HandlerTask {

    public HandlerService(HandlerTask.Callback callback) {
        super(callback);
    }

    @Override
    public void handleMessage(Message msg) {
        this.callback.execute(msg);
    }
}
