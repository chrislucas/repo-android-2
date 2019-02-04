package xplorer.restrictionbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiverTest extends BroadcastReceiver {

    public static final String ACTION_TEST_1 = "xplorer.restrictionbroadcastreceiver;Test1";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent != null) {

            String fmt = String.format("Action: %s\nURI: %s"
                    , intent.getAction()
                    , intent.toUri(Intent.URI_INTENT_SCHEME)
            );

            Toast.makeText(context, fmt, Toast.LENGTH_LONG).show();
        }
    }
}
