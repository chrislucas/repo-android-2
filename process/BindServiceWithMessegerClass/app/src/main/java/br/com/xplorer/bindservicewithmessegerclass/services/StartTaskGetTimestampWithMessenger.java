package br.com.xplorer.bindservicewithmessegerclass.services;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import br.com.xplorer.bindservicewithmessegerclass.handler.ExecuteLoopTask;
import br.com.xplorer.bindservicewithmessegerclass.handler.HandlerTask;

public class StartTaskGetTimestampWithMessenger extends Service implements ExecuteLoopTask.Callback, HandlerTask.Callback {

    public static final int FLAG_START_TASK = 0xff;
    public static final String BUNDLE_TIMESTAMP = "BUNDLE_TIMESTAMP";
    private ExecuteLoopTask executeLoopTask;
    private Messenger mReplyTo, mMessenger = new Messenger(new HandlerTask(this));


    public static final String TAG = StartTaskGetTimestampWithMessenger.class.getSimpleName().toUpperCase();

    public StartTaskGetTimestampWithMessenger() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "STAST_TASK_ON_CREATE");
        executeLoopTask = new ExecuteLoopTask(this, 1000);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_NOT_STICKY;
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mMessenger.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "ON_UNBIND");
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i(TAG, "ON_REBIND");
    }

    /**
     * {@link ExecuteLoopTask.Callback#executeTask()}
     * Metodo que e executado infinitamente dentro de um intervalo de tempo
     * definido quandp a classe {@link ExecuteLoopTask} é instanciada
     * */
    @Override
    public void executeTask() {
        long timestamp = System.currentTimeMillis();
        Bundle bundle = new Bundle();
        bundle.putLong(BUNDLE_TIMESTAMP, timestamp);
        Message reply = Message.obtain(null, FLAG_START_TASK);
        reply.setData(bundle);
        try {
            mReplyTo.send(reply);
        } catch (RemoteException e) {
            String msgException = e.getMessage() == null ? "Não foi possíve capturar o erro" : e.getMessage();
            Log.e("EX_RESP_MESSAGE_SERVICE", msgException);
        }
    }

    /**
     * {@link HandlerTask.Callback#execute(Message)}
     * */
    @Override
    public void execute(Message message) {
        switch (message.what) {
            case FLAG_START_TASK:
                mReplyTo = message.replyTo;
                executeLoopTask.start();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "ON_DESTROY");
        executeLoopTask.stop();
        Toast.makeText(this, "Tarefa encerrada", Toast.LENGTH_LONG).show();
    }
}
