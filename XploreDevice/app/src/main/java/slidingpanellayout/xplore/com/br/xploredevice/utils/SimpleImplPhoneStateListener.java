package slidingpanellayout.xplore.com.br.xploredevice.utils;

import android.content.Context;
import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.util.Log;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

/**
 * Created by r028367 on 26/01/2018.
 */

public class SimpleImplPhoneStateListener extends PhoneStateListener {


    private Context context;

    public SimpleImplPhoneStateListener(Context context) {
        super();
        this.context = context;
    }

    @Override
    public void onServiceStateChanged(ServiceState serviceState) {
        super.onServiceStateChanged(serviceState);
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
    }

    @Override
    public void onDataActivity(int direction) {
        super.onDataActivity(direction);
    }

    @Override
    public void onSignalStrengthsChanged(SignalStrength signalStrength) {
        super.onSignalStrengthsChanged(signalStrength);
        StringBuilder sb = new StringBuilder();

        sb.append(String.format(Locale.getDefault()
                , "CdmaDbm: %d.\n", signalStrength.getCdmaDbm())
        );

        sb.append(String.format(Locale.getDefault()
                , "GsmSignalStrength: %d\n", signalStrength.getGsmSignalStrength()));

        sb.append(String.format(Locale.getDefault()
                , "GsmSignalStrength 1: %d\n"
                , signalStrength.getGsmSignalStrength() * 2 - signalStrength.getCdmaDbm()
        ));

        sb.append(String.format(Locale.getDefault(), "signal to noise ratio: %d\n", signalStrength.getEvdoSnr()));
        sb.append(String.format(Locale.getDefault(), "GSM bit error rate: %d\n", signalStrength.getGsmBitErrorRate()));

        Log.i("SIGNAL_STRENGTHS", sb.toString());

        Toast.makeText(context, sb.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCellInfoChanged(List<CellInfo> cellInfo) {
        super.onCellInfoChanged(cellInfo);
    }
}
