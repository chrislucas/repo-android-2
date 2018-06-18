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

    public interface RemoteServiceConnection {
        /**
         * Ao conectar dum serviço passar uma instancia de {@link IRemoteService}
         * utilizando o metodo {@link IRemoteService.Stub#asInterface(IBinder)}
         * */
        void onConnected(IRemoteService remoteService);

        /**
         * Ao desconectar dum serviço passar uma instancia de {@link IRemoteService}
         * utilizando o metodo {@link IRemoteService.Stub#asInterface(IBinder)}
         * */
        void onDisconnected(IRemoteService remoteService);
    }

    private RemoteServiceConnection mRemoteServiceConnectionCallback;

    public ImplRemoteServiceConnection(RemoteServiceConnection mRemoteServiceConnectionCallback) {
        this.mRemoteServiceConnectionCallback = mRemoteServiceConnectionCallback;
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        mRemoteService = IRemoteService.Stub.asInterface(service);
        this.mRemoteServiceConnectionCallback.onConnected(mRemoteService);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        this.mRemoteServiceConnectionCallback.onDisconnected(mRemoteService);
    }

    @Override
    public void onBindingDied(ComponentName name) {

    }
}
