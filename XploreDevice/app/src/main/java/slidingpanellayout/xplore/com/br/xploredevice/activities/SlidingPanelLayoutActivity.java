package slidingpanellayout.xplore.com.br.xploredevice.activities;

import android.content.DialogInterface;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.CellLocation;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

import slidingpanellayout.xplore.com.br.xploredevice.R;
import slidingpanellayout.xplore.com.br.xploredevice.broadcastreceivers.BroadcastReceiverPhoneStateChange;
import slidingpanellayout.xplore.com.br.xploredevice.utils.phone.statelistener.SimplePhoneStateListener;
import slidingpanellayout.xplore.com.br.xploredevice.utils.settings.SettingSecureUtils;
import slidingpanellayout.xplore.com.br.xploredevice.utils.telephony.TelephonyUtils;

public class SlidingPanelLayoutActivity extends AppCompatActivity  implements SimplePhoneStateListener.CallbackListenerCellLocation {


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
                , new SimplePhoneStateListener(getApplicationContext(), this));
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
                        , "GSM.\nCID (Cell Tower ID): %d.\nLAC (Localtion Area Code): %d.\n(Primary Scrambling Code) PSC: %d.\n"
                        , cid, lac, psc));
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
            Method[] methods = SignalStrength.class.getMethods();
            String lteSignalMessage = "";
            for(Method method : methods) {
                try {
                    if( ( method.getName().equals("getLteSignalStrength")  )) {
                        int lte = (int) method.invoke(signalStrength);
                        lteSignalMessage += String.format(Locale.getDefault(), "LTE Signal Strength %d.\n", lte);
                    }

                    else if( ( method.getName().equals("getLteRsrp")  )) {
                        int lte = (int) method.invoke(signalStrength);
                        lteSignalMessage += String.format(Locale.getDefault(), "LTE Rsrp %d.\n", lte);
                    }

                    else if( ( method.getName().equals("getLteRsrq")  )) {
                        int lte = (int) method.invoke(signalStrength);
                        lteSignalMessage += String.format(Locale.getDefault(), "LTE Rsrq %d.\n", lte);
                    }

                    else if( ( method.getName().equals("getLteRssnr")  )) {
                        int lte = (int) method.invoke(signalStrength);
                        lteSignalMessage += String.format(Locale.getDefault(), "LTE Rssnr %d.\n", lte);
                    }

                    else if( ( method.getName().equals("getLteCqi")  )) {
                        int lte = (int) method.invoke(signalStrength);
                        lteSignalMessage += String.format(Locale.getDefault(), "LTE Cqi %d.\n", lte);
                    }

                    else if( ( method.getName().equals("getLteRsrpBoost")  )) {
                        int lte = (int) method.invoke(signalStrength);
                        lteSignalMessage += String.format(Locale.getDefault(), "LTE Rsrp Boost %d.\n", lte);
                    }
                }
                catch (IllegalAccessException | InvocationTargetException e) {
                    Log.e("EXCP_REFLECTION", e.getMessage());
                }
            }
            String sb =
                    String.format(Locale.getDefault(), "Signal LTE:\n%s\n", lteSignalMessage) +
                    String.format(Locale.getDefault()
                    , "CdmaDbm (Decibel por Milliwatt): %d.\n", signalStrength.getCdmaDbm()) +
                    String.format(Locale.getDefault()
                            , "GsmSignalStrength: %d.\n", signalStrength.getGsmSignalStrength()) +
                    String.format(Locale.getDefault()
                            , "Cdma Dbm - SignalStrength  * 2: %d.\n"
                            , signalStrength.getGsmSignalStrength() * 2 - signalStrength.getCdmaDbm()
                    ) +
                    String.format(Locale.getDefault(), "Signal to noise ratio: %d.\n", signalStrength.getEvdoSnr()) +
                    String.format(Locale.getDefault(), "DBM: %d.\n", signalStrength.getEvdoDbm()) +
                    String.format(Locale.getDefault(), "CdmaEcio: %d.\n", signalStrength.getCdmaEcio()) +
                    String.format(Locale.getDefault(), "EvdoEcio: %d.\n", signalStrength.getEvdoEcio()) +
                    String.format(Locale.getDefault(), "GSM bit error rate: %d.\n", signalStrength.getGsmBitErrorRate());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
               sb +=  String.format(Locale.getDefault(), "Nivel: %d.\n", signalStrength.getLevel());
            }
            ((TextView) findViewById(R.id.info_signal_strengths)).setText(sb);
        }
    }
}
