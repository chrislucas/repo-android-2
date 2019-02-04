package view.xplorer.opendialogenablegps.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.GnssStatus;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Locale;

import view.xplorer.opendialogenablegps.R;

public class ActivityGPSListener extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_listener);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    GnssStatus.Callback  c = new GnssStatus.Callback() {

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
                        }

                        @SuppressLint("NewApi")
                        @Override
                        public void onSatelliteStatusChanged(GnssStatus status) {
                            super.onSatelliteStatusChanged(status);
                            int satelliteCount = 0;
                            satelliteCount = status.getSatelliteCount();
                            String fmt = "";
                            for (int idx = 0; idx < satelliteCount ; idx++) {
                                fmt = String.format(Locale.getDefault(),
                                        "Graus Azimu%f"
                                        , status.getAzimuthDegrees(idx)
                                );
                            }

                            TextView info = findViewById(R.id.text_info);
                            info.setText(fmt);
                        }
                    };
                    locationManager.registerGnssStatusCallback(c);
                }
            }
        }
    }
}
