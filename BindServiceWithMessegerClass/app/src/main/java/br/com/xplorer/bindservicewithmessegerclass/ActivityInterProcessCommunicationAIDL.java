package br.com.xplorer.bindservicewithmessegerclass;


import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;

import br.com.xplorer.bindservicewithmessegerclass.services.RemoteServiceWithAIDLCommunication;
import br.com.xplorer.bindservicewithmessegerclass.services.iremote.ImplRemoteService;
import br.com.xplorer.bindservicewithmessegerclass.services.serviceconnection.ImplRemoteServiceConnection;


/***
 *
 * Nota da documentacao sobre quando usar BIND em um Serviço utilizando AIDL
 *
 * Note: Using AIDL is necessary only if you allow clients from different
 * applications to access your service for IPC and want to handle multithreading in your
 * service. If you do not need to perform concurrent IPC across different applications,
 * you should create your interface by implementing a Binder or, if you want to perform IPC,
 * but do not need to handle multithreading, implement your interface using a Messenger.
 * Regardless, be sure that you understand Bound Services before implementing an AIDL.
 *
 *
 * */


public class ActivityInterProcessCommunicationAIDL extends AppCompatActivity
        implements ImplRemoteServiceConnection.RemoteServiceConnection {

    private static final String TAG = ActivityInterProcessCommunicationAIDL.class.getSimpleName().toUpperCase();
    private static final String BUNDLE_INTENT = "BUNDLE_INTENT";
    private IRemoteService implRemoteService;
    private ImplRemoteServiceConnection implRemoteServiceConnection;
    private Intent intentStartRemoteService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interprocess_communication_aidl);
        implRemoteServiceConnection = new ImplRemoteServiceConnection(this);
        intentStartRemoteService = savedInstanceState != null
                ? (Intent) savedInstanceState.getParcelable(BUNDLE_INTENT)
                : new Intent(this, RemoteServiceWithAIDLCommunication.class);
        startService(intentStartRemoteService);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(outState != null) {
            outState.putParcelable(BUNDLE_INTENT, intentStartRemoteService);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null) {
            intentStartRemoteService = savedInstanceState.getParcelable(BUNDLE_INTENT);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        bindService(intentStartRemoteService
                , implRemoteServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "ON_DESTROY");
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService();
        stopService();
    }

    private void unbindService() {
        if(implRemoteServiceConnection != null) {
            try {
                unbindService(implRemoteServiceConnection);
            } catch (Exception excp) {
                String message = excp.getMessage() == null ? "Não foi possível capturar o erro" : excp.getMessage();
                Log.e("EXCP_ON_STOP", message);
            }
        }
    }

    private void stopService() {
        stopService(intentStartRemoteService);
    }

    @Override
    public void onConnected(IRemoteService remoteService) {
        this.implRemoteService = remoteService;
        try {
            int pid = implRemoteService.getPID();
            Toast.makeText(this
                    , String.format(Locale.getDefault()
                            , "RemoteServiceConnection Connected: PID %d", pid), Toast.LENGTH_LONG).show();
        }
        catch (RemoteException e) {
            String message = e.getMessage() == null ? "Não foi possível recuperar o erro" : e.getMessage();
            Log.e("CONN_SERVICE_GET_PID", message);
        }
        Log.i(TAG, "CONNECTED_REMOTE_SERVICE");

    }

    @Override
    public void onDisconnected(IRemoteService remoteService) {
        try {
            int pid = remoteService.getPID();
            Toast.makeText(this
                    , String.format(Locale.getDefault()
                            , "RemoteServiceConnection Disconnected: PID %d", pid), Toast.LENGTH_LONG).show();
        }
        catch (RemoteException e) {
            String message = e.getMessage() == null ? "Não foi possível recuperar o erro" : e.getMessage();
            Log.e("CONN_SERVICE_GET_PID", message);
        }

        Log.i(TAG, "DISCONNECTED_REMOTE_SERVICE");
    }
}
