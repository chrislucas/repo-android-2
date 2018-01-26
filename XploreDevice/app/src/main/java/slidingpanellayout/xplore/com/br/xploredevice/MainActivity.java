package slidingpanellayout.xplore.com.br.xploredevice;

import android.content.DialogInterface;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;

import java.util.Locale;

import slidingpanellayout.xplore.com.br.xploredevice.broadcastreceivers.BroadcastReceiverPhoneStateChange;
import slidingpanellayout.xplore.com.br.xploredevice.utils.SimpleImplPhoneStateListener;
import slidingpanellayout.xplore.com.br.xploredevice.utils.SettingSecureUtils;
import slidingpanellayout.xplore.com.br.xploredevice.utils.TelephonyUtils;

public class MainActivity extends AppCompatActivity {


    private StringBuilder message = new StringBuilder();;
    private static final DialogInterface.OnClickListener listenerGetPermissionSatePhone = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String IMEI = TelephonyUtils.getIMEIDevice(this, listenerGetPermissionSatePhone);
        if(IMEI != null) {
            message.append(String.format(Locale.getDefault()
                    , "DEVICE ID %s\n", IMEI));
        }

        message.append(String.format("Android ID: %s\n", SettingSecureUtils.androidId(this)));
        message.append(String.format("AllowdGeolocationOrigin ID: %s\n"
                , SettingSecureUtils.allowedGeolocationOrigin(this)));

        SettingSecureUtils.loggingUriPermissionInstance(this);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED);
        registerReceiver(new BroadcastReceiverPhoneStateChange(), intentFilter);

        TelephonyUtils.addPhoneStateListener(this
                , new SimpleImplPhoneStateListener(this));

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case TelephonyUtils.REQUEST_CODE_READ_PHONE_STATE:
                if(grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    String IMEI = TelephonyUtils.getIMEIDevice(this, listenerGetPermissionSatePhone);
                    if(IMEI != null) {
                        message.append(String.format(Locale.getDefault()
                                , "DEVICE ID %s", IMEI));
                    }
                }
                break;
        }
    }
}
