package slidingpanellayout.xplore.com.br.xploredevice.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SignalStrength;
import android.util.Log;
import android.widget.Toast;

import java.util.Set;

public class BroadcastReceiverPhoneStateChange extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        // an Intent broadcast.
        Bundle extras = intent.getExtras();
        String action = intent.getAction();
        Toast.makeText(context, action, Toast.LENGTH_LONG).show();
        if(extras != null) {
            logBundle(extras);
        }
    }

    private void logBundle(Bundle bundle) {
        Set<String> keys = bundle.keySet();
        if(keys != null && keys.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for(String key : keys)
                sb.append(String.format("%s ", key));
            Log.i("KEYS_BUNDLE", String.format("Keys: %s", sb.toString()));
        }
    }
}
