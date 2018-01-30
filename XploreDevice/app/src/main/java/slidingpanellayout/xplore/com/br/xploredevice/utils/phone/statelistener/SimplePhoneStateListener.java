package slidingpanellayout.xplore.com.br.xploredevice.utils.phone.statelistener;

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

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Locale;

/**
 * Created by r028367 on 26/01/2018.
 */

public class SimplePhoneStateListener extends PhoneStateListener {

    private Context context;
    private CallbackListenerCellLocation callbackListenerCellLocation;

    public interface CallbackListenerCellLocation {
        void updateCellLocation(CellLocation cellLocation);
        void updateSignalStrengths(SignalStrength signalStrength);
    }

    public SimplePhoneStateListener(Context context, CallbackListenerCellLocation callbackListenerCellLocation) {
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
                Log.i("GSM_CELL_LOCATION", String.format("CID (Cell Tower Id): %d.\nLAC (Location Area Code): %d.\nPSC: %d.\n", cid, lac, psc));
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
        String messageState = "";
        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:
                messageState = "TelephonyManager.CALL_STATE_IDLE";
                break;
            case TelephonyManager.CALL_STATE_RINGING:
                messageState = "TelephonyManager.CALL_STATE_RINGING";
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                messageState = "TelephonyManager.CALL_STATE_OFFHOOK";
                break;
        }
        Log.i("CALL_STATE_CHANGED", String.format("Call State: %s", messageState));
        Log.i("CALL_STATE_CHANGED", String.format("Número ligando: %s", incomingNumber));

    }

    @Override
    public void onDataConnectionStateChanged(int state) {
        super.onDataConnectionStateChanged(state);
        Log.i("STATE_TEL_DATA_STATE", getTelephonyDataState(state));
    }

    private String getTelephonyDataState(int state) {
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
        return message;
    }

    @Override
    public void onDataConnectionStateChanged(int state, int networkType) {
        super.onDataConnectionStateChanged(state, networkType);
        Log.i("STATE_TEL_DATA_STATE", getTelephonyDataState(state));
        String message = "";
        Field [] fields =  TelephonyManager.class.getFields();
        if(fields != null) {
            for(Field field : fields) {
                int modifiers = field.getModifiers();
                if( Modifier.isFinal(modifiers) && Modifier.isPublic(modifiers) && field.getName().startsWith("NETWORK_TYPE") ) {
                    try {
                        if(field.getInt(null) == networkType) {
                            message = field.getName();
                            break;
                        }
                    }
                    catch (IllegalAccessException e) {
                       Log.e("ILLEGAL_ACCESS", e.getLocalizedMessage());
                    }
                }
            }
        }
        Log.i("STATE_TEL_DATA_STATE", String.format("Tipo de Rede: %s", message));
    }

    @Override
    public void onDataActivity(int direction) {
        super.onDataActivity(direction);
        String message = "???";
        switch (direction) {
            case TelephonyManager.DATA_ACTIVITY_DORMANT:
                message = "TelephonyManager.DATA_ACTIVITY_DORMANT";
                break;
            case TelephonyManager.DATA_ACTIVITY_IN:
                message = "TelephonyManager.DATA_ACTIVITY_IN";
                break;
            case TelephonyManager.DATA_ACTIVITY_INOUT:
                message = "TelephonyManager.DATA_ACTIVITY_INOUT";
                break;
            case TelephonyManager.DATA_ACTIVITY_NONE:
                message = "TelephonyManager.DATA_ACTIVITY_NONE";
                break;
            case TelephonyManager.DATA_ACTIVITY_OUT:
                message = "TelephonyManager.DATA_ACTIVITY_OUT";
                break;
        }
        Log.i("DATA_ACTIVITY", message);
    }

    @Override
    public void onSignalStrengthsChanged(SignalStrength signalStrength) {
        super.onSignalStrengthsChanged(signalStrength);
        Log.i("SIGNAL_STRENGTHS", signalStrength.toString());
        callbackListenerCellLocation.updateSignalStrengths(signalStrength);
    }

    @Override
    public void onCellInfoChanged(List<CellInfo> cellInfo) {
        super.onCellInfoChanged(cellInfo);
    }
}
