package br.com.xplorer.xplorerapilocation;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.os.Looper;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationResult;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import br.com.xplorer.utilsgeolocation.callback.DefaultLocationCallbackImpl;
import br.com.xplorer.utilsgeolocation.callback.OnCompleteTaskGoogleApiFusedLocation;
import br.com.xplorer.utilsgeolocation.callback.OnLocationResultListener;
import br.com.xplorer.utilsgeolocation.callback.model.Message;
import br.com.xplorer.utilsgeolocation.service.AddressResultReceiver;
import br.com.xplorer.utilsgeolocation.service.GeocoderIntentService;
import br.com.xplorer.utilsgeolocation.utils.Geolocation;

public class Main2Activity extends AppCompatActivity implements OnCompleteTaskGoogleApiFusedLocation
        , OnLocationResultListener, HandlerResultReceiverAddress.Callback<Address> {

    private Geolocation geolocation;
    private Location mLastKnowLocation;

    public static final String BUNDLE_SAVE_LOCATION = "BUNDLE_SAVE_LOCATION";
    public static final String BUNDLE_SAVE_REQUESTING_LOCATION_UPDATES = "BUNDLE_SAVE_REQUESTING_LOCATION_UPDATES";
    public static final String BUNDLE_SAVE_REQUESTING_ADDRESS_UPDATES = "BUNDLE_SAVE_REQUESTING_ADDRESS_UPDATES";

    private TextView textViewLastLocation, textViewLastAddress;

    private DefaultLocationCallbackImpl defaultLocationCallback;

    private boolean mRequestingLocationUpdates, mRequestingAddressUpdates;
    private AddressResultReceiver addressResultReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textViewLastLocation = findViewById(R.id.text_view_last_location);
        textViewLastAddress = findViewById(R.id.text_view_last_address);
        if(savedInstanceState != null) {
            mLastKnowLocation = savedInstanceState.getParcelable(BUNDLE_SAVE_REQUESTING_LOCATION_UPDATES);
            mRequestingLocationUpdates = savedInstanceState.getBoolean(BUNDLE_SAVE_LOCATION);
            if (mLastKnowLocation != null)
                updateTextViewLastLocation(mLastKnowLocation);
        }
        addressResultReceiver = new AddressResultReceiver(new HandlerResultReceiverAddress(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        geolocation = new Geolocation(this);
        geolocation.getLastKnowLocation(this);
    }

    @Override
    public void successHandler(Address address) {
        /**
         * Pegar o nome do estado e converter em Sigla
         * */
        String adminArea = address.getAdminArea();
        StringBuilder code = new StringBuilder();
        if(adminArea != null) {
            StringTokenizer tk = new StringTokenizer(adminArea, " ");
            for (int i = 0; i < 2 && tk.hasMoreElements(); i++) {
                code.append(tk.nextToken().substring(0, 1));
            }
        }
        int max = address.getMaxAddressLineIndex();
        for (int i = 0; i <=max ; i++) {
            String str = address.getAddressLine(0);
            Log.i(String.format("ADDRESS_LINE_%d", max), String.format("Line %s\nCode: %s", str, code.toString()));
        }

        String log = String.format(Locale.getDefault()
                ,"\nFeature Name: %s\nURL: %s\nLocality: %s\nLatLog: %f %f\nAdminArea: %s\nPhone: %s\nPostal Code: %s\nPremises: %s\nThroroughfare: %s\n"
                , address.getFeatureName()
                , address.getUrl()
                , address.getLocality()
                , address.getLatitude()
                , address.getLongitude()
                , adminArea
                , address.getPhone()
                , address.getPostalCode()
                , address.getPremises()
                , address.getThoroughfare()
        );

        textViewLastAddress.setText(log);
        mRequestingAddressUpdates = false;
    }

    @Override
    public void failureHandler() {

    }


    /**
     * Resposta para solicitacao de permissao de acesso a localizacao do dipositivo
     * */
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

    /**
     * Resposta para a solicitacao para ligar a localizacao no menu do dispositivo
     * */
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
            outState.putBoolean(BUNDLE_SAVE_REQUESTING_ADDRESS_UPDATES, mRequestingAddressUpdates);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            mLastKnowLocation = savedInstanceState.getParcelable(BUNDLE_SAVE_LOCATION);
            mRequestingLocationUpdates = savedInstanceState.getBoolean(BUNDLE_SAVE_REQUESTING_LOCATION_UPDATES);
            mRequestingAddressUpdates = savedInstanceState.getBoolean(BUNDLE_SAVE_REQUESTING_ADDRESS_UPDATES);
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

    private void startServiceToUpdateAddress(Location location, ResultReceiver resultReceiver) {
        mRequestingAddressUpdates = true;
        Intent intentService = new Intent(this, GeocoderIntentService.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(GeocoderIntentService.BUNDLE_LOCATION, location);
        bundle.putParcelable(GeocoderIntentService.BUNDLE_RESULT_RECEIVER, resultReceiver);
        bundle.putInt(GeocoderIntentService.BUNDLE_LIMIT_RESULT, 6);
        intentService.putExtras(bundle);
        startService(intentService);
    }

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY kk:mm:ss", Locale.getDefault());

    @Override
    public void successGetLocation(Location location) {
        if (location != null) {
            this.mLastKnowLocation = location;
            Calendar calendar = Calendar.getInstance();
            String log = String.format(Locale.getDefault()
                    , "\nLat: %f\nLon: %f\nProvedor: %s\nAltitude: %f\nBearing: %f\n\nÚltima atualização: %s\n"
                    , location.getLatitude()
                    , location.getLongitude()
                    , location.getProvider()
                    , location.getAltitude()
                    , location.getBearing()
                    , simpleDateFormat.format(calendar.getTime())
            );
            Log.i("LOG_INFO_LOCATION", log);
            /**
             * Iniciar o servico para recuperar a ultima localizacao do dispotivo
             * */
            updateTextViewLastLocation(location);
            defaultLocationCallback = new DefaultLocationCallbackImpl(this);
            mRequestingLocationUpdates = true;
            geolocation.startServiceUpdateLocation(defaultLocationCallback, Looper.getMainLooper());
            // iniciar o servico para recuperar o endereço da ultima localizacao do dispositivo
            startServiceToUpdateAddress(location, addressResultReceiver);
        }
        else {
            Log.e("EXCP_LOG_LOCATION", "Nã foi possível capturar informação sobre a localização do device.");
        }
    }

    @Override
    public void failureGetLocation(Message message) {
        String msg = message.getStringMessage();
        Log.e("FAILURE_GET_LOCATION", msg);
    }

    @Override
    public void callbackHasLocationResult(@NonNull LocationResult locationResult) {
        mLastKnowLocation = locationResult.getLastLocation();
        for (Location location : locationResult.getLocations()) {
            Log.i("LOCATIONS_CALLBACK", location.toString());
        }
        updateTextViewLastLocation(mLastKnowLocation);
    }

    private void updateTextViewLastLocation(@NonNull Location location) {
        Calendar calendar = Calendar.getInstance();
        String log = String.format(Locale.getDefault()
                , "\nLat: %f\nLon: %f\nProvedor: %s\nAltitude: %f\nBearing: %f\n\nÚltima atualização: %s\n"
                , location.getLatitude()
                , location.getLongitude()
                , location.getProvider()
                , location.getAltitude()
                , location.getBearing()
                , simpleDateFormat.format(calendar.getTime())
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
