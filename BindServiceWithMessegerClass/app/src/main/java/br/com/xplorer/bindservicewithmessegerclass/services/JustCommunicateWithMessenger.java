package br.com.xplorer.bindservicewithmessegerclass.services;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class JustCommunicateWithMessenger extends Service {

    public static final String BUNDLE_MESSENGER = "BUNDLE_MESSENGER";

    public JustCommunicateWithMessenger() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent != null) {
            Bundle bundle = intent.getExtras();
            if(bundle != null) {
                Messenger messenger = bundle.getParcelable(BUNDLE_MESSENGER);
                if (messenger != null) {
                    try {
                        Message message = new Message();
                        messenger.send(message);
                    } catch (RemoteException e) {
                        Log.e("EXCP", e.getMessage());
                    }
                }
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
}
