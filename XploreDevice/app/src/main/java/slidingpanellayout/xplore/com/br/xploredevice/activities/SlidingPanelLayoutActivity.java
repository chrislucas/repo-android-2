package slidingpanellayout.xplore.com.br.xploredevice.activities;

import android.content.DialogInterface;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.CellLocation;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;

import slidingpanellayout.xplore.com.br.xploredevice.R;
import slidingpanellayout.xplore.com.br.xploredevice.broadcastreceivers.BroadcastReceiverPhoneStateChange;
import slidingpanellayout.xplore.com.br.xploredevice.utils.SimpleImplPhoneStateListener;
import slidingpanellayout.xplore.com.br.xploredevice.utils.settings.SettingSecureUtils;
import slidingpanellayout.xplore.com.br.xploredevice.utils.TelephonyUtils;

public class SlidingPanelLayoutActivity extends AppCompatActivity  implements SimpleImplPhoneStateListener.CallbackListenerCellLocation {


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

        addPhoneStateListeners();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case TelephonyUtils.REQUEST_CODE_READ_PHONE_STATE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    String IMEI = TelephonyUtils.getIMEIDevice(this, listenerGetPermissionSatePhone);
                    if(IMEI != null) {
                        message.append(String.format(Locale.getDefault()
                                , "DEVICE ID %s", IMEI));
                    }
                }
                break;
            case TelephonyUtils.REQUEST_CODE_ACCESS_COARSE_LOCATION:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    addPhoneStateListeners();
                break;
        }
    }

    private void addPhoneStateListeners() {
        TelephonyUtils.addPhoneStateListeners(this
                , new SimpleImplPhoneStateListener(getApplicationContext(), this));
    }

    @Override
    public void updateCellLocation(CellLocation cellLocation) {
        if(cellLocation != null) {
            if(cellLocation instanceof GsmCellLocation) {
                GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                int cid = gsmCellLocation.getCid();
                int lac = gsmCellLocation.getLac();
                int psc = gsmCellLocation.getPsc();
                ((TextView) findViewById(R.id.info_cell_location)).setText(String.format(Locale.getDefault()
                        , "GSM.\nCID: %d.\nLAC: %d.\nPSC: %d.\n", cid, lac, psc));
            }
            else if(cellLocation instanceof CdmaCellLocation) {
                CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
                String message = String.format(Locale.getDefault(),"CDMA.\nBase Station ID: %d.\n" +
                                "Get Base Station Lat: %d.\nGet Base Station Lon: %d.\n" +
                                "Network ID: %d.\nSystem ID: %d.\n"
                        ,cdmaCellLocation.getBaseStationId()
                        ,cdmaCellLocation.getBaseStationLatitude()
                        ,cdmaCellLocation.getBaseStationLongitude()
                        ,cdmaCellLocation.getNetworkId()
                        ,cdmaCellLocation.getSystemId()
                );
                ((TextView) findViewById(R.id.info_cell_location)).setText(message);
            }
        }
    }

    @Override
    public void updateSignalStrengths(SignalStrength signalStrength) {
        if(signalStrength != null) {
            String sb = String.format(Locale.getDefault()
                    , "CdmaDbm: %d.\n", signalStrength.getCdmaDbm()) +
                    String.format(Locale.getDefault()
                            , "GsmSignalStrength 1: %d\n", signalStrength.getGsmSignalStrength()) +
                    String.format(Locale.getDefault()
                            , "Cdma Dbm - SignalStrength  * 2: %d\n"
                            , signalStrength.getGsmSignalStrength() * 2 - signalStrength.getCdmaDbm()
                    ) +
                    String.format(Locale.getDefault(), "Signal to noise ratio: %d\n", signalStrength.getEvdoSnr()) +
                    String.format(Locale.getDefault(), "GSM bit error rate: %d\n", signalStrength.getGsmBitErrorRate());

            ((TextView) findViewById(R.id.info_signal_strengths)).setText(sb);
        }
    }
}
