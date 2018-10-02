package view.xplorer.opendialogenablegps.activities;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.location.LocationRequest;

import view.xplorer.opendialogenablegps.EnableGPS;
import view.xplorer.opendialogenablegps.R;

public class BaseAppCompatActivity extends AppCompatActivity {

    public static final int REQUEST_PERMISSION_ACCESS_LOCATION = 0xff;

    /**
     * Para fazer com que o editor aceite o metodo a baixo como
     * um 'testador' de permissoes o nome do metodo deve comecar
     * com check e terminar com per
     * */

    protected boolean checkHasToAccessGeoLocationPermission() {
        int a = ActivityCompat.checkSelfPermission(this
                , Manifest.permission.ACCESS_FINE_LOCATION);
        int b = ActivityCompat.checkSelfPermission(this
                , Manifest.permission.ACCESS_COARSE_LOCATION);
        return a == PackageManager.PERMISSION_GRANTED && b == PackageManager.PERMISSION_GRANTED;
    }

    protected void askPermissionToAccessGeoLocation() {
        /**
         * Pedir permissao para o usuario
         **/
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            String permissions [] = {
                    Manifest.permission.ACCESS_FINE_LOCATION
                    ,Manifest.permission.ACCESS_COARSE_LOCATION
            };
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        ActivityCompat.requestPermissions(BaseAppCompatActivity.this
                                , permissions, REQUEST_PERMISSION_ACCESS_LOCATION);
                        break;
                }
                dialog.dismiss();
            }
        };
        builder.setPositiveButton(R.string.yes, onClickListener);
        builder.setNegativeButton(R.string.no, onClickListener);
        builder.setTitle("Permissão para acessar sua Geolocalização.");
        builder.setMessage("Para o funcionamento desse aplicativo é " +
                "necessário a permissão para acessar sua geolocalização.");
        AlertDialog alertDialog = builder.create();
        if (!isFinishing() && ! alertDialog.isShowing()) {
            alertDialog.show();
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (checkHasToAccessGeoLocationPermission()) {
            configureLocationRequest();
        }
        else {
            askPermissionToAccessGeoLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_ACCESS_LOCATION) {
            if (grantResults.length > 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                configureLocationRequest();
            }
            else {
                Toast.makeText(this, "Acesso a GPS negado", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EnableGPS.REQUEST_ENABLE_GPS) {
            Toast.makeText(this
                    , String.format("%s", resultCode == RESULT_OK ? "GPS Ligado" : "GPS Cancelado")
                    , Toast.LENGTH_LONG).show();
        }
    }

    protected void configureLocationRequest() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null && ! locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            LocationRequest mLocationRequest = LocationRequest.create();
            mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
            mLocationRequest.setInterval(30000);
            mLocationRequest.setFastestInterval(50000);
            EnableGPS enableGPS = new EnableGPS(this, mLocationRequest);
            enableGPS.getGoogleApiClient().connect();
        }
    }
}
