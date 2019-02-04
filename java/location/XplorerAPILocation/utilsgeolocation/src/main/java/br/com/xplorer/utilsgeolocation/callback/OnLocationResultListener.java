package br.com.xplorer.utilsgeolocation.callback;

import android.support.annotation.NonNull;

import com.google.android.gms.location.LocationResult;

import br.com.xplorer.utilsgeolocation.callback.model.Message;

/**
 * Created by r028367 on 19/03/2018.
 */

public interface OnLocationResultListener {
    void callbackHasLocationResult(@NonNull LocationResult locationResult);
    void callbackHasNotLocationResult(Message message);
}
