package clipboard.xplorer.com.sample.phonestate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyPhoneCallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        if (intent.getAction() != null && intent.getAction().equals("android.intent.action.NEW_OUTGOING_CA")) {
            if (intent.getExtras() != null) {
                String number = intent.getExtras().getString("android.intent.extra.PHONE_NUMBER");
                Toast.makeText(context, number, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
