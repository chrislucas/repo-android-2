package br.com.xplorer.bindservicewithmessegerclass.services;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;

import br.com.xplorer.bindservicewithmessegerclass.bindservice.BindService;


/**
 *
 * Recordar e viver
 * https://developer.android.com/guide/components/services.html?hl=pt-br
 *
 * */

public class LocalService extends Service {

    private IBinder iBinder = new BindService(this);

    public static final String FILTER = "FILTER_TEST";
    public static final String BUNDLE_INT_PERCENT = "BUNDLE_INT_PERCENT";

    public LocalService() {}

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return iBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * Enviando um valor qualquer para um filtro que esteja preparado dentro dessa aplicacao
         * */
        Intent intent = new Intent(FILTER);
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_INT_PERCENT, 1);
        intent.putExtras(bundle);
        sendBroadcast(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_NOT_STICKY;
    }


    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
