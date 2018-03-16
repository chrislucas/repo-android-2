package br.com.xplorer.utilsgeolocation.utils;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import br.com.xplorer.utilsgeolocation.impl.LocationSourceImpl;

/**
 * Created by r028367 on 16/03/2018.
 */

public class Geolocation {


    public static void enableGPS(Context context
            , GoogleApiClient.ConnectionCallbacks connectionCallbacks
            , GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(connectionCallbacks)
                .addOnConnectionFailedListener(onConnectionFailedListener)
                .build();
    }


    public static void oldMethodGetLocation(Context context) {
        LocationManager locationManager = LocationSourceImpl.getLocationManager(context);
        if(locationManager != null) {

        }
    }

}
