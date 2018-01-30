package slidingpanellayout.xplore.com.br.xploredevice.utils;

import android.content.Context;
import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

/**
 * Created by r028367 on 26/01/2018.
 */

public class SimpleImplPhoneStateListener extends PhoneStateListener {

    private Context context;
    private CallbackListenerCellLocation callbackListenerCellLocation;

    public interface CallbackListenerCellLocation {
        void updateCellLocation(CellLocation cellLocation);
        void updateSignalStrengths(SignalStrength signalStrength);
    }

    public SimpleImplPhoneStateListener(Context context, CallbackListenerCellLocation callbackListenerCellLocation) {
        super();
        this.context = context;
        this.callbackListenerCellLocation = callbackListenerCellLocation;
    }

    @Override
    public void onServiceStateChanged(ServiceState serviceState) {
        super.onServiceStateChanged(serviceState);
        String message = "";
        switch (serviceState.getState()) {
            case ServiceState.STATE_EMERGENCY_ONLY:
                message = "ServiceState.STATE_EMERGENCY_ONLY";
                break;
            case ServiceState.STATE_IN_SERVICE:
                message = "ServiceState.STATE_IN_SERVICE";
                break;
            case ServiceState.STATE_OUT_OF_SERVICE:
                message = "ServiceState.STATE_OUT_OF_SERVICE";
                break;
            case ServiceState.STATE_POWER_OFF:
                message = "ServiceState.STATE_POWER_OFF";
                break;
        }

        Log.i("TEL_SRV_STATE_CHANGED", message);
        Log.i("TEL_SRV_STATE_CHANGED",
            String.format(Locale.getDefault()
                , "OperatorAlphaLong: %s.\nOperatorNumeric: %s.\nOperatorAlphaShort: %s.\n"
                , serviceState.getOperatorAlphaLong()
                , serviceState.getOperatorNumeric()
                , serviceState.getOperatorAlphaShort()
            )
        );
    }

    @Override
    public void onSignalStrengthChanged(int asu) {
        super.onSignalStrengthChanged(asu);
    }

    @Override
    public void onMessageWaitingIndicatorChanged(boolean mwi) {
        super.onMessageWaitingIndicatorChanged(mwi);
    }

    @Override
    public void onCallForwardingIndicatorChanged(boolean cfi) {
        super.onCallForwardingIndicatorChanged(cfi);
    }

    @Override
    public void onCellLocationChanged(CellLocation location) {
        super.onCellLocationChanged(location);
        CellLocation.requestLocationUpdate();
        if(location != null) {

            if(location instanceof  GsmCellLocation) {
                GsmCellLocation gsmCellLocation = (GsmCellLocation) location;
                int cid = gsmCellLocation.getCid();
                int lac = gsmCellLocation.getLac();
                int psc = gsmCellLocation.getPsc();
                Log.i("GSM_CELL_LOCATION", String.format("CID: %d.\nLAC: %d.\nPSC: %d.\n", cid, lac, psc));
            }
            else if (location instanceof CdmaCellLocation) {
                CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) location;
                Log.i("CDMA_CELL_LOCATION", String.format("Base Station ID: %d.\n" +
                                "Get Base Station Lat: %d.\nGet Base Station Lon: %d.\n" +
                                "Network ID: %d.\nSystem ID: %d.\n"
                        ,cdmaCellLocation.getBaseStationId()
                        ,cdmaCellLocation.getBaseStationLatitude()
                        ,cdmaCellLocation.getBaseStationLongitude()
                        ,cdmaCellLocation.getNetworkId()
                        ,cdmaCellLocation.getSystemId()
                    )
                );
            }
        }



        callbackListenerCellLocation.updateCellLocation(location);
    }

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);
    }

    @Override
    public void onDataConnectionStateChanged(int state) {
        super.onDataConnectionStateChanged(state);
    }

    @Override
    public void onDataConnectionStateChanged(int state, int networkType) {
        super.onDataConnectionStateChanged(state, networkType);
        String message = "";
        switch (state) {
            case TelephonyManager.DATA_DISCONNECTED:
                message = "TelephonyManager.DATA_DISCONNECTED";
                break;
            case TelephonyManager.DATA_CONNECTED:
                message = "TelephonyManager.DATA_CONNECTED";
                break;
            case TelephonyManager.DATA_CONNECTING:
                message = "TelephonyManager.DATA_CONNECTING";
                break;
            case TelephonyManager.DATA_SUSPENDED:
                message = "TelephonyManager.DATA_SUSPENDED";
                break;
        }
        Log.i("TEL_CONN_STATE_CHANGED", message);
    }

    @Override
    public void onDataActivity(int direction) {
        super.onDataActivity(direction);
    }

    @Override
    public void onSignalStrengthsChanged(SignalStrength signalStrength) {
        super.onSignalStrengthsChanged(signalStrength);

        String sb = String.format(Locale.getDefault()
                , "CdmaDbm: %d.\n", signalStrength.getCdmaDbm()) +
                String.format(Locale.getDefault()
                        , "GsmSignalStrength: %d\n", signalStrength.getGsmSignalStrength()) +
                String.format(Locale.getDefault()
                        , "GsmSignalStrength 1: %d\n"
                        , signalStrength.getGsmSignalStrength() * 2 - signalStrength.getCdmaDbm()
                ) +
                String.format(Locale.getDefault(), "signal to noise ratio: %d\n", signalStrength.getEvdoSnr()) +
                String.format(Locale.getDefault(), "GSM bit error rate: %d\n", signalStrength.getGsmBitErrorRate());

        Log.i("SIGNAL_STRENGTHS", sb);

        callbackListenerCellLocation.updateSignalStrengths(signalStrength);
    }

    @Override
    public void onCellInfoChanged(List<CellInfo> cellInfo) {
        super.onCellInfoChanged(cellInfo);
    }
}
