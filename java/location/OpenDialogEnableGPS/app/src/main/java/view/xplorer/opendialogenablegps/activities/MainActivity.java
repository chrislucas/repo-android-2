package view.xplorer.opendialogenablegps.activities;

import android.location.Criteria;
import android.location.GnssStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.location.LocationRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import view.xplorer.opendialogenablegps.EnableGPS;
import view.xplorer.opendialogenablegps.R;


public class MainActivity extends BaseAppCompatActivity implements LocationListener {

    private TextView textInfoLocation;

    private LocationManager locationManager;

    private static final SimpleDateFormat simpleDateFormat =
            new SimpleDateFormat("dd/MM/YYYY kk:mm:ss", Locale.getDefault());

    private static ArrayMap<Integer, String> mapConstallation = new ArrayMap<>();

    static {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mapConstallation.put(GnssStatus.CONSTELLATION_UNKNOWN, "CONSTELLATION_UNKNOWN");
            mapConstallation.put(GnssStatus.CONSTELLATION_GPS, "CONSTELLATION_UNKNOWN");
            mapConstallation.put(GnssStatus.CONSTELLATION_BEIDOU, "CONSTELLATION_BEIDOU");
            mapConstallation.put(GnssStatus.CONSTELLATION_GALILEO, "CONSTELLATION_GALILEO");
            mapConstallation.put(GnssStatus.CONSTELLATION_GLONASS, "CONSTELLATION_GLONASS");
            mapConstallation.put(GnssStatus.CONSTELLATION_QZSS, "CONSTELLATION_QZSS");
            mapConstallation.put(GnssStatus.CONSTELLATION_SBAS, "CONSTELLATION_SBAS");
        }
    }

    private static final GnssStatus.Callback gnssStatusCallback = new GnssStatus.Callback() {

        @Override
        public void onStarted() {
            super.onStarted();
        }

        @Override
        public void onStopped() {
            super.onStopped();
        }

        @Override
        public void onFirstFix(int ttffMillis) {
            super.onFirstFix(ttffMillis);
            Log.i("TIME_TO_FIRST_FIX", String.valueOf(ttffMillis));
        }

        @Override
        public void onSatelliteStatusChanged(GnssStatus status) {
            super.onSatelliteStatusChanged(status);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                //List<String> listInfoSatellite = new ArrayList<>();
                for (int i = 0; i < status.getSatelliteCount() ; i++) {
                    int constellationType = status.getConstellationType(i);
                    String fmt = String.format(
                            Locale.getDefault()
                            , "Satelite ID %d.\nAzimute Degrees: %f.\nCnd0DbHz: %f." +
                                    "\nConstellation Type: %d - %s.\nElevation Degrees: %f.\nSvid: %d"
                            , i
                            , status.getAzimuthDegrees(i)
                            , status.getCn0DbHz(i)
                            , constellationType
                            , mapConstallation.get(constellationType)
                            , status.getElevationDegrees(i)
                            , status.getSvid(i)
                    );
                    Log.i("SATELLITE_S_CHANGED", fmt);
                    //listInfoSatellite.add(fmt);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        textInfoLocation = findViewById(R.id.text_info_location);

        if (checkHasToAccessGeoLocationPermission()) {
            configureLocationRequest();
        }
        else {
            askPermissionToAccessGeoLocation();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Location location = getBestLocation();
        if (location != null) { updateInfoLocation(location); }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && checkHasToAccessGeoLocationPermission()) {
            locationManager.unregisterGnssStatusCallback(gnssStatusCallback);
        }
    }

    private String configureCriteriaForGetBestProvider() {
        Criteria criteria = new Criteria();
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setSpeedRequired(true);
        criteria.setSpeedAccuracy(Criteria.ACCURACY_COARSE);
        criteria.setAltitudeRequired(true);
        criteria.setBearingAccuracy(Criteria.ACCURACY_FINE);
        criteria.setHorizontalAccuracy(Criteria.ACCURACY_FINE);
        criteria.setCostAllowed(true);
        if (locationManager != null) {
            // retorna somente provedores que estao disponiveis
            return locationManager.getBestProvider(criteria, true);
        }
        return "";
    }

    private Location getBestLocation() {
        if (checkHasToAccessGeoLocationPermission()) {
            if (locationManager != null) {
                String provider = configureCriteriaForGetBestProvider();
                if (!provider.equals("")) {
                    TextView infoGpsProvider = findViewById(R.id.info_gps_provider);
                    infoGpsProvider.setText(String.format("Provedor de GPS: %s", provider));
                    if (checkHasToAccessGeoLocationPermission()) {
                        locationManager.requestLocationUpdates(provider
                                , 500L, 1f, this);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && checkHasToAccessGeoLocationPermission()) {
                            locationManager.registerGnssStatusCallback(gnssStatusCallback);
                        }
                        return locationManager.getLastKnownLocation(provider);
                    }
                }
            }
        }
        return null;
    }


    @Override
    protected void configureLocationRequest() {
        if (locationManager != null && ! locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            LocationRequest mLocationRequest = LocationRequest.create();
            mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
            mLocationRequest.setInterval(5000);
            mLocationRequest.setFastestInterval(6000);
            EnableGPS enableGPS = new EnableGPS(this, mLocationRequest);
            enableGPS.getGoogleApiClient().connect();
        }
    }

    private String updateInfoLocation(Location location) {
        if (location != null) {
            String fmt = String.format(Locale.getDefault()
                    , "Lat: %f.\nLng: %f.\nH offset %f.\nAlt: %f.\nAccuracy: %f\nRealTime: %s.\nTime: %s.\nSpped: %f.\nProvider: %s"
                    , location.getLatitude()
                    , location.getLongitude()
                    , location.getBearing()
                    //, location.getBearingAccuracyDegrees()
                    , location.getAltitude()
                    , location.getAccuracy()
                    , simpleDateFormat.format(new Date(location.getElapsedRealtimeNanos() / 1000))
                    , simpleDateFormat.format(new Date(location.getTime()))
                    , location.getSpeed()
                    , location.getProvider()
            );
            textInfoLocation.setText(fmt);
            return fmt;
        }
        return "";
    }

    @Override
    public void onLocationChanged(Location location) {
        updateInfoLocation(location);
        Log.i("ON_LOCATION_CHANGED", updateInfoLocation(location));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        if (extras != null) {
            for (String key : extras.keySet()) {
                Log.i("ON_STATUS_CHANGE"
                        , String.format("Key: %s Value: %s", key, extras.get(key).toString()));
            }
        }
    }

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onProviderDisabled(String provider) {}
}
