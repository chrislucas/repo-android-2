package br.com.xplorer.bindservicewithmessegerclass;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.com.xplorer.bindservicewithmessegerclass.handler.HandlerTask;
import br.com.xplorer.bindservicewithmessegerclass.services.StartTaskGetTimestampWithMessenger;


/**
 *
 * IPC - Interprocess communication
 * https://developer.android.com/guide/components/aidl.html
 * */

public class ActivityBindServiceWithMessenger extends AppCompatActivity implements ServiceConnection, HandlerTask.Callback {

    private TextView textViewShowTime;
    private HandlerTask handlerTask;
    private Intent intentService;
    private Messenger mMessengerSender, mMessageReceiver;

    private final SimpleDateFormat SIMPLE_HOUR_FORMAT = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

    private static final String BUNDLE_INTENT = "BUNDLE_INTENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_service_with_messenger);
        textViewShowTime = findViewById(R.id.text_view_showtime);
        intentService = (savedInstanceState != null)
                ? (Intent) savedInstanceState.getParcelable(BUNDLE_INTENT)
                :  new Intent(this, StartTaskGetTimestampWithMessenger.class);
        startService(intentService);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            outState.putParcelable(BUNDLE_INTENT, intentService);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            intentService = savedInstanceState.getParcelable(BUNDLE_INTENT);
        }
    }

    public void onClickStopService(View view) {
        // unbind serviço primario
        unbindService();
        // para o serviço depois
        stopService();
    }

    private void stopService() {
        if(intentService != null)
            Toast.makeText(this
                    , stopService(intentService)
                            ? "Parando o serviçp"
                            : "Problemas ao tentar para o serviço", Toast.LENGTH_LONG).show();
    }


    private void updateTextView(long timestamp) {
        textViewShowTime.setText(SIMPLE_HOUR_FORMAT.format(new Date(timestamp)));
    }


    @Override
    protected void onResume() {
        super.onResume();
        bindService(intentService, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopService();
        unbindService();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService();
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        /**
         * Como foi configurado no arquivo AndroidManifest que o serviço {@link StartTaskGetTimestampWithMessenger}
         * vai rodar em um outro processo, o atribuo service vem como uma instanciaa de {@link android.os.BinderProxy}
         * */
        try {
           Log.i("SV_INTERFACE_DESC",  service.getInterfaceDescriptor());
        }
        catch (RemoteException e) {
            String msgException = e.getMessage() == null ? "Não foi possíve capturar o erro" : e.getMessage();
            Log.e("EX_START_TASK_ACTIVITY", msgException);
        }
        mMessengerSender = new Messenger(service);
        Message message = Message.obtain(null
                , StartTaskGetTimestampWithMessenger.FLAG_START_TASK, 0, 0);
        handlerTask = new HandlerTask(this);
        mMessageReceiver = new Messenger(handlerTask);
        message.replyTo = mMessageReceiver;
        try {
            mMessengerSender.send(message);
        }
        catch (RemoteException e) {
            String msgException = e.getMessage() == null ? "Não foi possíve capturar o erro" : e.getMessage();
            Log.e("EX_START_TASK_ACTIVITY", msgException);
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    @Override
    public void onBindingDied(ComponentName name) {

    }


    private void unbindService() {
        try {
            unbindService(this);
        }
        catch (IllegalArgumentException excp) {
            String message = excp.getMessage() == null ? "Não foi possível capturar o erro" : excp.getMessage();
            Log.e("EXCP_ON_PAUSE", message);
        }
    }

    @Override
    public void execute(Message message) {
        switch (message.what) {
            case StartTaskGetTimestampWithMessenger.FLAG_START_TASK:
                Bundle bundle = message.getData();
                if(bundle != null) {
                    long timestamp = bundle.getLong(StartTaskGetTimestampWithMessenger.BUNDLE_TIMESTAMP);
                    updateTextView(timestamp);
                }
                break;
        }
    }
}
