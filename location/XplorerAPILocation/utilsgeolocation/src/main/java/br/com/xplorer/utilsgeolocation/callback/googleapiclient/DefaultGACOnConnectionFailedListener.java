package br.com.xplorer.utilsgeolocation.callback.googleapiclient;

import android.support.annotation.NonNull;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by C_Luc on 18/03/2018.
 */

public class DefaultGACOnConnectionFailedListener implements GoogleApiClient.OnConnectionFailedListener {
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {}
}
