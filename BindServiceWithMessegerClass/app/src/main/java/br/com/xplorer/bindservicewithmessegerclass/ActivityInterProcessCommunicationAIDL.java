package br.com.xplorer.bindservicewithmessegerclass;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import br.com.xplorer.bindservicewithmessegerclass.services.RemoteService;
import br.com.xplorer.bindservicewithmessegerclass.services.serviceconnection.ImplRemoteServiceConnection;

public class ActivityInterProcessCommunicationAIDL extends AppCompatActivity implements ImplRemoteServiceConnection.Service  {

    private IRemoteService iRemoteService;
    private ImplRemoteServiceConnection implRemoteServiceConnection;
    private static final String TAG = ActivityInterProcessCommunicationAIDL.class.getSimpleName().toUpperCase();

    private static final String BUNDLE_INTENT = "BUNDLE_INTENT";

    private Intent intentStartRemoteService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interprocess_communication_aidl);
        implRemoteServiceConnection = new ImplRemoteServiceConnection(this);
        intentStartRemoteService = savedInstanceState != null
                ? (Intent) savedInstanceState.getParcelable(BUNDLE_INTENT)
                : new Intent(this, RemoteService.class);
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
        if(implRemoteServiceConnection != null) {
            try {
                unbindService(implRemoteServiceConnection);
            } catch (Exception excp) {
                String message = excp.getMessage() == null ? "Não foi possível capturar o erro" : excp.getMessage();
                Log.e("EXCP_ON_STOP", message);
            }
        }
    }

    @Override
    public void onConnected(IRemoteService remoteService) {
        this.iRemoteService = remoteService;
        Log.i(TAG, "CONNECTED_REMOTE_SERVICE");
    }

    @Override
    public void onDisconnected(IRemoteService remoteService) {
        this.iRemoteService = remoteService;
        Log.i(TAG, "DISCONNECTED_REMOTE_SERVICE");
    }
}
