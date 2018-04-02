package br.com.xplorer.bindservicewithmessegerclass.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import br.com.xplorer.bindservicewithmessegerclass.services.iremote.ImplRemoteServiceAIDL;

public class RemoteService extends Service {

    private ImplRemoteServiceAIDL implRemoteServiceAIDL;

    private static final String TAG = RemoteService.class.getSimpleName().toUpperCase();

    public RemoteService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        implRemoteServiceAIDL = new ImplRemoteServiceAIDL();
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
        return implRemoteServiceAIDL;
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
