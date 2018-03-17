package br.com.xplorer.utilsgeolocation.impl;

import android.content.Context;
import android.location.LocationManager;

/**
 * Created by r028367 on 15/03/2018.
 */

public class LocationSourceImpl  {

    private static final String GPS_PROVIDER = LocationManager.GPS_PROVIDER;
    private static final String NET_PROVIDER = LocationManager.NETWORK_PROVIDER;

    public static LocationManager getLocationManager(Context context) {
        return (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    public static boolean testGPSProviderIsEnabled(Context context) {
        LocationManager locationManager = getLocationManager(context);
        return locationManager != null && locationManager.isProviderEnabled(GPS_PROVIDER);
    }

    public static boolean testNETProviderIsEnabled(Context context) {
        LocationManager locationManager = getLocationManager(context);
        return locationManager != null && locationManager.isProviderEnabled(NET_PROVIDER);
    }


}
