package br.com.xplorer.bindservicewithmessegerclass.services.iremote;

import android.os.Process;
import android.os.RemoteException;

import br.com.xplorer.bindservicewithmessegerclass.IRemoteService;

/**
 * Created by r028367 on 02/04/2018.
 */

public class ImplRemoteService extends IRemoteService.Stub {
    @Override
    public int getPID() throws RemoteException {
        return Process.myPid();
    }

    @Override
    public void execute() throws RemoteException {

    }
}
