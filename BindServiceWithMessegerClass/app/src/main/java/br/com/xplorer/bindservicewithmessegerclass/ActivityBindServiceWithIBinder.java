package br.com.xplorer.bindservicewithmessegerclass;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;

import br.com.xplorer.bindservicewithmessegerclass.bindservice.BindService;
import br.com.xplorer.bindservicewithmessegerclass.services.LocalService;
import br.com.xplorer.bindservicewithmessegerclass.services.UtilsService;


/**
 * Explorando a classe {@link android.os.Messenger} baseado em alguns tutoriais, textos e a documentacao da google
 * */

public class ActivityBindServiceWithIBinder extends AppCompatActivity implements ServiceConnection{

    private LocalService localService;
    private Intent intentService;
    /**
     *
     * A titulo de teste. Nao faço mais isso
     * */

    private BroadcastReceiver r = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent != null ) {
                if(intent.getAction() != null && intent.getAction().equals(LocalService.FILTER)) {
                    Bundle  bundle = intent.getExtras();
                    if(bundle != null) {
                        int p = bundle.getInt(LocalService.BUNDLE_INT_PERCENT, -1);
                        Toast.makeText(ActivityBindServiceWithIBinder.this
                                , String.format(Locale.getDefault(), "%d", p)
                                , Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        intentService = new Intent(this, LocalService.class);
        startService(intentService);
        UtilsService.isAlreadyRunning(this, LocalService.class);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(LocalService.FILTER);
        registerReceiver(r, intentFilter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        bindService(intentService, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            unbindService(this);
            unregisterReceiver(r);
        }
        catch (IllegalArgumentException excp) {
            String message = excp.getMessage() == null ? "Não foi possível capturar o erro" : excp.getMessage();
            Log.e("EXCP_ON_PAUSE", message);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            unbindService(this);
            unregisterReceiver(r);
        }
        catch (IllegalArgumentException excp) {
            String message = excp.getMessage() == null ? "Não foi possível capturar o erro" : excp.getMessage();
            Log.e("EXCP_ON_DESTROY", message);
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        BindService bindService = (BindService) service;
        localService = (LocalService) bindService.getService();
        showToast("Service Connected.");
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        localService = null;
        showToast("Service Disconnected.");
    }

    @Override
    public void onBindingDied(ComponentName name) {
        showToast("Service Died.");
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
