package jobservice.xplorer.com.br.anotherstudyaboutalarmmanager.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ServiceFilterAlarm extends IntentService {

    public static final String KEY_STRING = "KEY_STRING";

    public ServiceFilterAlarm() {
        super("ServiceFilterAlarm");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                String data = bundle.getString(KEY_STRING);
                Log.i("DATA_EVENT", data);
            }
        }
    }
}
