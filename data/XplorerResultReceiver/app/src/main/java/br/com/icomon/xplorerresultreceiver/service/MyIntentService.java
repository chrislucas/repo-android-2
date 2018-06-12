package br.com.icomon.xplorerresultreceiver.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

import br.com.icomon.xplorerresultreceiver.resultreceiver.MyResultReceiver;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {

    public static int RESULT_RECEIVER_INT_SERVICE = 0x33;

    public MyIntentService() {
        super("MyIntentService");
    }

    public static final String BUNDLE_RESULT_RECEIVER = "BUNDLE_RESULT_RECEIVER";

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                ResultReceiver resultReceiver = bundle.getParcelable(BUNDLE_RESULT_RECEIVER);
                Bundle bundle1 = new Bundle();
                resultReceiver.send(RESULT_RECEIVER_INT_SERVICE, bundle1);
            }
        }
    }
}
