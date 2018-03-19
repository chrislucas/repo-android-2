package br.com.xplorer.xplorerapilocation;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;

import java.util.Locale;

import br.com.xplorer.utilsgeolocation.callback.DefaultLocationCallbackImpl;
import br.com.xplorer.utilsgeolocation.callback.OnCompleteTaskGoogleApiFusedLocation;
import br.com.xplorer.utilsgeolocation.callback.OnLocationResultListener;
import br.com.xplorer.utilsgeolocation.callback.model.Message;
import br.com.xplorer.utilsgeolocation.utils.Geolocation;

public class Main2Activity extends AppCompatActivity implements OnCompleteTaskGoogleApiFusedLocation, OnLocationResultListener {

    private Geolocation geolocation;
    private Location mLastKnowLocation;

    public static final String BUNDLE_SAVE_LOCATION = "BUNDLE_SAVE_LOCATION";
    public static final String BUNDLE_SAVE_REQUESTING_LOCATION_UPDATES = "BUNDLE_SAVE_REQUESTING_LOCATION_UPDATES";

    private TextView textViewLastLocation;

    private DefaultLocationCallbackImpl defaultLocationCallback;

    private boolean mRequestingLocationUpdates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textViewLastLocation = findViewById(R.id.text_view_last_location);
    }

    @Override
    protected void onStart() {
        super.onStart();
        geolocation = new Geolocation(this);
        geolocation.getLastKnowLocation(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Geolocation.REQUEST_PERMISSION_ACCESS_LOCATION:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    geolocation.getLastKnowLocation(this);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case Geolocation.REQUEST_ENABLE_GPS:
                if (requestCode == Activity.RESULT_OK) {
                    geolocation.getLastKnowLocation(this);
                }
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(outState != null) {
            outState.putParcelable(BUNDLE_SAVE_LOCATION, mLastKnowLocation);
            outState.putBoolean(BUNDLE_SAVE_REQUESTING_LOCATION_UPDATES, mRequestingLocationUpdates);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            mLastKnowLocation = savedInstanceState.getParcelable(BUNDLE_SAVE_LOCATION);
            mRequestingLocationUpdates = savedInstanceState.getBoolean(BUNDLE_SAVE_REQUESTING_LOCATION_UPDATES);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        stopUpdateLocation();
    }

    private void stopUpdateLocation() {
        if(mRequestingLocationUpdates) {
            geolocation.removeLocationUpdates(defaultLocationCallback);
            mRequestingLocationUpdates = false;
        }
    }


    @Override
    public void successGetLocation(Location location) {
        if (location != null) {
            this.mLastKnowLocation = location;
            String log = String.format(Locale.getDefault()
                    , "Lat: %f\nLon: %f\nProvedor: %s\nAltitude: %f\nBearing: %f"
                    , location.getLatitude()
                    , location.getLongitude()
                    , location.getProvider()
                    , location.getAltitude()
                    , location.getBearing()
            );
            Log.i("LOG_INFO_LOCATION", log);
            updateTextViewLastLocation(location);
            defaultLocationCallback = new DefaultLocationCallbackImpl(this);
            mRequestingLocationUpdates = true;
            geolocation.startServiceUpdateLocation(defaultLocationCallback, Looper.getMainLooper());
        }
        else {}
    }

    @Override
    public void failureGetLocation(Message message) {
        String msg = message.getStringMessage();
        Log.e("FAILURE_GET_LOCATION", msg);
    }

    @Override
    public void callbackHasLocationResult(@NonNull LocationResult locationResult) {
        mLastKnowLocation = locationResult.getLastLocation();
        updateTextViewLastLocation(mLastKnowLocation);
    }

    private void updateTextViewLastLocation(@NonNull Location location) {
        String log = String.format(Locale.getDefault()
                , "Lat: %f\nLon: %f\nProvedor: %s\nAltitude: %f\nBearing: %f"
                , location.getLatitude()
                , location.getLongitude()
                , location.getProvider()
                , location.getAltitude()
                , location.getBearing()
        );
        String message = String.format(getString(R.string.format_last_location), log);
        textViewLastLocation.setText(message);
    }


    @Override
    public void callbackHasNotLocationResult(Message message) {
        String str =message.getStringMessage();
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        mRequestingLocationUpdates = false;
    }
}
