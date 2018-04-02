package br.com.xplorer.bindservicewithmessegerclass.services.serviceconnection;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

import br.com.xplorer.bindservicewithmessegerclass.IRemoteService;

/**
 * Created by r028367 on 02/04/2018.
 */

public class ImplRemoteServiceConnection implements ServiceConnection {

    private IRemoteService mRemoteService;

    public interface Service {
        void onConnected(IRemoteService remoteService);
        void onDisconnected(IRemoteService remoteService);
    }

    private Service mServiceCallback;

    public ImplRemoteServiceConnection(Service mServiceCallback) {
        this.mServiceCallback = mServiceCallback;
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        mRemoteService = IRemoteService.Stub.asInterface(service);
        this.mServiceCallback.onConnected(mRemoteService);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        this.mServiceCallback.onDisconnected(mRemoteService);
    }

    @Override
    public void onBindingDied(ComponentName name) {

    }
}
