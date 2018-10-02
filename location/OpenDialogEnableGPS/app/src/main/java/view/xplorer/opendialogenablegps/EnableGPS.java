package view.xplorer.opendialogenablegps;

import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentSender;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

public class EnableGPS implements  GoogleApiClient.ConnectionCallbacks
        , GoogleApiClient.OnConnectionFailedListener
        , ResultCallback<LocationSettingsResult> {

    private LocationRequest mLocationRequest;
    private GoogleApiClient googleApiClient;

    private FragmentActivity fragmentActivity;

    public static int REQUEST_ENABLE_GPS = 0xf5;

    public EnableGPS(@NonNull FragmentActivity fragmentActivity
            , LocationRequest mLocationRequest) {
        this.mLocationRequest = mLocationRequest;
        GoogleApiClient.Builder mBuilder = new GoogleApiClient.Builder(fragmentActivity.getApplicationContext());
        mBuilder.addApi(LocationServices.API);
        mBuilder.addOnConnectionFailedListener(this);
        mBuilder.addConnectionCallbacks(this);
        this.googleApiClient = mBuilder.build();
        this.fragmentActivity = fragmentActivity;
    }


    public GoogleApiClient getGoogleApiClient() {
        return googleApiClient;
    }

    public LocationRequest getLocationRequest() {
        return mLocationRequest;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (mLocationRequest != null) {
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(mLocationRequest);
            builder.setAlwaysShow(true);
            PendingResult<LocationSettingsResult> mResult = LocationServices.SettingsApi
                    .checkLocationSettings(googleApiClient, builder.build());
            mResult.setResultCallback(this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) { }

    @Override
    public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
        Status status = locationSettingsResult.getStatus();
        switch (status.getStatusCode()) {
            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                try {
                    status.startResolutionForResult(fragmentActivity, REQUEST_ENABLE_GPS);
                }
                catch (IntentSender.SendIntentException e) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(fragmentActivity);
                    builder.setTitle("Problemas ao tentar ativar o GPS.");
                    builder.setMessage("O aplicativo não foi capaz de ativar o GPS automáticamente.\n" +
                            "Por favor, entre em contato com a equipe de T.I.");
                    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    if (!fragmentActivity.isFinishing()) {
                        builder.create().show();
                    }
                }
                break;
        }
    }
}
