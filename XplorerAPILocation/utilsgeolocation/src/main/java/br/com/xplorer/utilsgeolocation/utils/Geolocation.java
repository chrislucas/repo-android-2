package br.com.xplorer.utilsgeolocation.utils;

import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import br.com.xplorer.utilsgeolocation.impl.LocationSourceImpl;

/**
 * Created by r028367 on 16/03/2018.
 */

public class Geolocation {

    private static final long DEFAULT_INTERVAL_SET_LOCATION = 30*1000;
    private static final long DEFAULT_FAST_INTERVAL_SET_LOCATION = 30*1000;

    public static final int REQUEST_LOCATION_SETTINGS = 0x13;

    public static void enableGPS(Activity activity
            , GoogleApiClient.ConnectionCallbacks connectionCallbacks
            , GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(activity)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(connectionCallbacks)
                .addOnConnectionFailedListener(onConnectionFailedListener)
                .build();

        googleApiClient.connect();
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        locationRequest.setInterval(DEFAULT_INTERVAL_SET_LOCATION);
        locationRequest.setFastestInterval(DEFAULT_FAST_INTERVAL_SET_LOCATION);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        builder.setAlwaysShow(true);
        /**
         * @deprecated
         * LocationServices.SettingsApi
         * https://developers.google.com/android/reference/com/google/android/gms/location/SettingsApi
         * Usar agora SettingsClient
         * https://developers.google.com/android/reference/com/google/android/gms/location/SettingsClient
         * */


        setTask(activity, builder.build());

        //setPendingResult(activity, googleApiClient, builder.build());
    }

    private static void setTask(Activity activity, LocationSettingsRequest locationSettingsRequest) {
        Task<LocationSettingsResponse> taskResponse = LocationServices.getSettingsClient(activity).checkLocationSettings(locationSettingsRequest);

        taskResponse
                .addOnSuccessListener(activity, new OnSuccessListener<LocationSettingsResponse>() {
                    @Override
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {

                    }
                })
                .addOnFailureListener(activity, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    /**
     * Esse metodo utiliza recursos obsoletos da API
     * */
    private static void setPendingResult(final Activity activity
            , GoogleApiClient googleApiClient, LocationSettingsRequest locationSettingsRequest) {
        PendingResult<LocationSettingsResult> pendingResult = LocationServices.SettingsApi
                .checkLocationSettings(googleApiClient, locationSettingsRequest);
        pendingResult.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult result) {
                final Status status = result.getStatus();
                //final LocationSettingsStates state = result.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        break;
                    case LocationSettingsStatusCodes.CANCELED:
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            status.startResolutionForResult(activity, REQUEST_LOCATION_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            String msg = e.getMessage() == null ? "Não foi possível recuperar a mensagem de erro"
                                    : e.getMessage();
                            Log.e("SEND_INTENT_EXCP", msg);
                        }
                        break;
                }
            }
        });
    }


    public static void oldMethodGetLocation(Context context) {
        LocationManager locationManager = LocationSourceImpl.getLocationManager(context);
        if(locationManager != null) {

        }
    }

}
