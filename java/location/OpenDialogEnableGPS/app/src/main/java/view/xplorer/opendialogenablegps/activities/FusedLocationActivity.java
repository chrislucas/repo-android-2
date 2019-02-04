package view.xplorer.opendialogenablegps.activities;


import android.location.Location;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import view.xplorer.opendialogenablegps.R;

public class FusedLocationActivity extends BaseAppCompatActivity {

    FusedLocationProviderClient mFusedLocationActivity;

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY kk:mm:ss", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fused_location);

        mFusedLocationActivity = LocationServices.getFusedLocationProviderClient(this);

        if (checkHasToAccessGeoLocationPermission()) {
            Task<Location> task = mFusedLocationActivity.getLastLocation();
            //addOnCompleteListenerToTask(task);
            addOnSuccessListenerToTask(task);
            addOnFailureListenerToTask(task);
        }
    }

    private void addOnCompleteListenerToTask(Task<Location> task) {
        task.addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {}
        });
    }

    private void addOnSuccessListenerToTask(Task<Location> task) {
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                setTextIntoLocation(location);
            }
        });
    }

    private void addOnFailureListenerToTask(Task<Location> task) {
        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(FusedLocationActivity.this
                        , e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setTextIntoLocation(Location location) {
        String fmt = String.format(Locale.getDefault()
                , "Lat: %f.\nLng: %f.\nH offset %f.\nAlt: %f,\nAccuracy: %f\nRealTime: %s.\nTime: %s.\nSpped: %f.\nProvider: %s"
                , location.getLatitude()
                , location.getLongitude()
                , location.getBearing()
                //, location.getBearingAccuracyDegrees()
                , location.getAltitude()
                , location.getAccuracy()
                , simpleDateFormat.format(new Date(location.getElapsedRealtimeNanos() / 1000))
                , simpleDateFormat.format(new Date(location.getTime()))
                , location.getSpeed()
                , location.getProvider());

        TextView textinfo = findViewById(R.id.text_info_location_2);
        textinfo.setText(fmt);
    }
}
