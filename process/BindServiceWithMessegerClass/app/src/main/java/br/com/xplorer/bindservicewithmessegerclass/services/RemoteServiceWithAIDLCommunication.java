package br.com.xplorer.bindservicewithmessegerclass.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import br.com.xplorer.bindservicewithmessegerclass.IRemoteService;
import br.com.xplorer.bindservicewithmessegerclass.services.iremote.ImplRemoteService;

public class RemoteServiceWithAIDLCommunication extends Service {

    private ImplRemoteService implRemoteService;

    private static final String TAG = RemoteServiceWithAIDLCommunication.class.getSimpleName().toUpperCase();

    public RemoteServiceWithAIDLCommunication() {}

    @Override
    public void onCreate() {
        super.onCreate();
        implRemoteService = new ImplRemoteService();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "ON_DESTROY_REMOTE_SERVICE");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "ON_BIND_REMOTE_SERVICE");
        return implRemoteService;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i(TAG, "ON_REBIND_REMOTE_SERVICE");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "ON_UNBIND_REMOTE_SERVICE");
        return true;
    }
}
