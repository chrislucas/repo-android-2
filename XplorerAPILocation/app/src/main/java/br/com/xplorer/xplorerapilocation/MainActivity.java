package br.com.xplorer.xplorerapilocation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.Locale;

import br.com.xplorer.utilsgeolocation.utils.AccessSettingsScreen;
import br.com.xplorer.utilsgeolocation.impl.LocationSourceImpl;


/**
 *
 *
 *
 */

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ACCESS_COARSE_PERMISSION = 0x03;
    private static final String PERMISSION_ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final String PERMISSION_ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;

    private FusedLocationProviderClient fusedLocationProviderClient;
    private TextView mTextViewLatitude, mTextViewLongitude;

    private View rootLayout;
    private Snackbar snackBarAllWarning;


    private boolean checkPermissionAccessCoarseLocation() {
        return ActivityCompat.checkSelfPermission(this
                , Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissionAccessCoarse() {
        ActivityCompat.requestPermissions(this
                , new String[]{PERMISSION_ACCESS_COARSE_LOCATION, PERMISSION_ACCESS_FINE_LOCATION}
                , REQUEST_CODE_ACCESS_COARSE_PERMISSION);
    }

    private void requestPermission() {
        boolean shouldIShowReq = shouldShowRequestPermissionRationale2(this
                , PERMISSION_ACCESS_COARSE_LOCATION) ||
                shouldShowRequestPermissionRationale2(this, PERMISSION_ACCESS_FINE_LOCATION);
        if (shouldIShowReq) {
            showMessage("Precisamos acessar a sua localizacao"
                    , getString(android.R.string.ok), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            requestPermissionAccessCoarse();
                        }
                    });
        } else {
            requestPermissionAccessCoarse();
        }
    }

    private void showMessage(String message, String stringAction, View.OnClickListener action) {
        snackBarAllWarning = Snackbar.make(rootLayout, message, Snackbar.LENGTH_INDEFINITE);
        snackBarAllWarning.setAction(stringAction, action);
        snackBarAllWarning.show();
    }


    private boolean shouldShowRequestPermissionRationale2(Activity activity, String permission) {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
    }

    private boolean checkSelfPermission2(String permission) {
        return  ActivityCompat.checkSelfPermission(this, permission)
                == PackageManager.PERMISSION_GRANTED;
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        boolean flag = checkSelfPermission2(Manifest.permission.ACCESS_FINE_LOCATION) && checkSelfPermission2(Manifest.permission.ACCESS_COARSE_LOCATION);
        if (flag) {
            if(LocationSourceImpl.testGPSProviderIsEnabled(this)) {
                Task<Location> task = fusedLocationProviderClient.getLastLocation();
                task.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        if(task.isSuccessful() && location != null) {
                            setLocation(location);
                        }
                        else {
                            showMessage("1: Não foi possível recuperar sua geolocatizacao"
                                    , getString(android.R.string.ok), new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            snackBarAllWarning.dismiss();
                                        }
                                    });
                        }
                    }
                })

                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location != null) {
                            setLocation(location);
                        }

                        else {
                            showMessage("2: Não foi possível recuperar sua geolocatizacao"
                                    , getString(android.R.string.ok), new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            snackBarAllWarning.dismiss();
                                        }
                                    });
                        }
                    }
                })

                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showMessage(e.getMessage()
                                , getString(android.R.string.ok), new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        snackBarAllWarning.dismiss();
                                    }
                                });
                    }
                });
            }

            else {
                showMessage("GPS está desabilido", getString(android.R.string.ok), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        snackBarAllWarning.dismiss();
                    }
                });
            }
        }
    }

    private void setLocation(Location location) {
        mTextViewLatitude.setText(String.format(Locale.getDefault()
                ,"Lat: %f", location.getLatitude()));

        mTextViewLongitude.setText(String.format(Locale.getDefault()
                ,"Lon: %f", location.getLongitude()));

        String log = String.format(Locale.getDefault()
                , "Lat: %f\nLon: %f\nProvedor: %s\nAltitude: %f\nBearing: %f"
                , location.getLatitude()
                , location.getLongitude()
                , location.getProvider()
                , location.getAltitude()
                , location.getBearing()
        );

        Log.i("LOG_INFO_LOCATION", log);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(!checkPermissionAccessCoarseLocation()) {
            requestPermission();
        }
        else {
            getLastLocation();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootLayout = findViewById(R.id.root_activity_main_layout);
        mTextViewLatitude = findViewById(R.id.latitude);
        mTextViewLongitude = findViewById(R.id.longitude);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions
            , @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_ACCESS_COARSE_PERMISSION:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLastLocation();
                }
                else {
                    /**
                     * Avisar o usuario que ele esta impactando na funcionalidade de buscar a geolocalizacao
                     * */
                    showMessage("Sem a permissão para acessar sua localização o aplicativo não vai funciona"
                            , getString(android.R.string.ok)
                            , new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    snackBarAllWarning.dismiss();
                                    AccessSettingsScreen.openApplicationDetailsSettings(MainActivity.this);
                                }
                            }
                        );
                }
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (snackBarAllWarning != null && snackBarAllWarning.isShown())
            snackBarAllWarning.dismiss();
    }
}
