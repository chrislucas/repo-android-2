package br.com.xplorer.utilsgeolocation.callback;

import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;

import br.com.xplorer.utilsgeolocation.callback.model.DefaultMessage;

/**
 * Created by r028367 on 19/03/2018.
 */

public class DefaultLocationCallbackImpl extends LocationCallback {

    private OnLocationResultListener onLocationResultListener;

    public DefaultLocationCallbackImpl(OnLocationResultListener onLocationResultListener) {
        this.onLocationResultListener = onLocationResultListener;
    }

    @Override
    public void onLocationResult(LocationResult locationResult) {
        super.onLocationResult(locationResult);
        if(locationResult != null) {
            onLocationResultListener.callbackHasLocationResult(locationResult);
        }
        else {
            DefaultMessage defaultMessage = new DefaultMessage("Não foi possível recuperar as " +
                    "últimas informações da localização do dispositivo.");
            onLocationResultListener.callbackHasNotLocationResult(defaultMessage);
        }
    }

    @Override
    public void onLocationAvailability(LocationAvailability locationAvailability) {
        super.onLocationAvailability(locationAvailability);
    }
}
