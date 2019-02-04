package br.com.icomon.xplorerresultreceiver.handlers;

import android.os.Handler;
import android.os.Message;

/**
 * Created by r028367 on 29/03/2018.
 */

public class MyHandler extends Handler {

    public interface Callback extends Handler.Callback {
        void doSomething(Message message);
    }

    private Callback callback;

    public MyHandler(Callback callback) {
        super(callback);
        this.callback = callback;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        callback.doSomething(msg);
    }

    @Override
    public void dispatchMessage(Message msg) {
        super.dispatchMessage(msg);
    }

    @Override
    public String getMessageName(Message message) {
        return super.getMessageName(message);
    }

    @Override
    public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
        return super.sendMessageAtTime(msg, uptimeMillis);
    }
}
