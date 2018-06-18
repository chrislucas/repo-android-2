package br.com.xplorer.bindservicewithmessegerclass.messenger;

import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

/**
 * Created by r028367 on 29/03/2018.
 */

public class ImplMessenger {

    private final Messenger messenger;

    public ImplMessenger(Messenger messenger) {
        this.messenger = messenger;
    }

    public void sendMessage(Message message) throws RemoteException {
        this.messenger.send(message);
    }

    public IBinder getBinder() {
        return messenger.getBinder();
    }
}
