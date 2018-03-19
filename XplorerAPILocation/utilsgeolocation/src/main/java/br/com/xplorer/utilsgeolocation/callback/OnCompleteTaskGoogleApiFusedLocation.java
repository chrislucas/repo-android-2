package br.com.xplorer.utilsgeolocation.callback;

import android.location.Location;

import br.com.xplorer.utilsgeolocation.callback.model.Message;

/**
 * Created by r028367 on 19/03/2018.
 */

public interface OnCompleteTaskGoogleApiFusedLocation {
    void successGetLocation(Location location);
    void failureGetLocation(Message message);
}
