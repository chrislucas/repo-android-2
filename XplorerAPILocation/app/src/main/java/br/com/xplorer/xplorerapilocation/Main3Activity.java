package br.com.xplorer.xplorerapilocation;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;

import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import br.com.xplorer.utilsgeolocation.callback.DefaultLocationCallbackImpl;
import br.com.xplorer.utilsgeolocation.callback.OnCompleteTaskGoogleApiFusedLocation;
import br.com.xplorer.utilsgeolocation.callback.OnLocationResultListener;
import br.com.xplorer.utilsgeolocation.callback.model.Message;
import br.com.xplorer.utilsgeolocation.resultreceiver.AddressResultReceiver;
import br.com.xplorer.utilsgeolocation.service.GeocoderIntentService;
import br.com.xplorer.utilsgeolocation.handler.HandlerResultReceiver;
import br.com.xplorer.utilsgeolocation.utils.ConfigLocationRequest;
import br.com.xplorer.utilsgeolocation.utils.Geolocation;

/**
 * Activity para testar o resultado final da lib de geolocalizacao
 * */
public class Main3Activity extends AppCompatActivity implements HandlerResultReceiver.Callback
        , OnCompleteTaskGoogleApiFusedLocation, OnLocationResultListener {

    private TextView textViewLocation, textViewAddress;

    private Geolocation geolocation;
    private Location mLastKnowLocation;
    private Address mLastAddress;
    private AddressResultReceiver addressResultReceiver;
    private DefaultLocationCallbackImpl defaultLocationCallback;

    public static final String BUNDLE_SAVE_LOCATION = "BUNDLE_SAVE_LOCATION";
    public static final String BUNDLE_SAVE_ADDRESS = "BUNDLE_SAVE_ADDRESS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        textViewLocation = findViewById(R.id.text_view_location);
        textViewAddress = findViewById(R.id.text_view_address);
        if(savedInstanceState != null) {
            mLastKnowLocation = savedInstanceState.getParcelable(BUNDLE_SAVE_LOCATION);
            mLastAddress = savedInstanceState.getParcelable(BUNDLE_SAVE_ADDRESS);
        }

        if (mLastKnowLocation != null)
            updateLastLocation();
        if(mLastAddress != null)
            updateLastAddress();

        addressResultReceiver = new AddressResultReceiver(new HandlerResultReceiver(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        ConfigLocationRequest configLocationRequest = new ConfigLocationRequest(30000
                , 30000, LocationRequest.PRIORITY_HIGH_ACCURACY);
        geolocation = new Geolocation(this, configLocationRequest);
        geolocation.getLastKnowLocation(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(defaultLocationCallback != null) {
            geolocation.removeLocationUpdates(defaultLocationCallback);
            defaultLocationCallback = null;
        }
    }

    /**
     * {@link HandlerResultReceiver.Callback}
     * */
    @Override
    public void successHandler(List<Address> data) {
        if(data != null && data.size() > 0) {
            mLastAddress = data.get(0);
            if(mLastAddress != null)
                updateLastAddress();
        }
        else {
            Toast.makeText(this
                    , "Não foi possível recuperar o endereço", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * {@link HandlerResultReceiver.Callback}
     * */
    @Override
    public void failureHandler() {
        Toast.makeText(this
                , "Erro ao tentar recuperar o endereço", Toast.LENGTH_LONG).show();
    }

    /**
     * {@link OnCompleteTaskGoogleApiFusedLocation}
     * */
    @Override
    public void successGetLocation(Location location) {
        if (location != null) {
            mLastKnowLocation = location;
            updateLastLocation();
            /**
             * Agora eu posso ativar a atualizacao de localizacao
             * */
            defaultLocationCallback = new DefaultLocationCallbackImpl(this);
            geolocation.startServiceUpdateLocation(defaultLocationCallback, Looper.getMainLooper());

            if(Geocoder.isPresent()) {
                /**
                 * Tendo a ultima geoposicao posso ativar o serviço que me traz o endereco
                 * */
                Intent intent = new Intent(this, GeocoderIntentService.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(GeocoderIntentService.BUNDLE_LOCATION, mLastKnowLocation);
                bundle.putParcelable(GeocoderIntentService.BUNDLE_RESULT_RECEIVER, addressResultReceiver);
                bundle.putInt(GeocoderIntentService.BUNDLE_LIMIT_RESULT, 6);
                intent.putExtras(bundle);
                startService(intent);
            }
        }
        else {
            Toast.makeText(this, "Dados da geoposicao nulos.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * {@link OnCompleteTaskGoogleApiFusedLocation}
     * */
    @Override
    public void failureGetLocation(Message message) {
        Toast.makeText(this, String.format("Falha: %s", message.getStringMessage()), Toast.LENGTH_SHORT).show();
    }

    /**
     * {@link OnLocationResultListener}
     * */
    @Override
    public void callbackHasLocationResult(@NonNull LocationResult locationResult) {
        mLastKnowLocation = locationResult.getLastLocation();
        if(mLastKnowLocation != null)
            updateLastLocation();
    }

    /**
     * {@link OnLocationResultListener}
     * */
    @Override
    public void callbackHasNotLocationResult(Message message) {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            outState.putParcelable(BUNDLE_SAVE_LOCATION, mLastKnowLocation);
            outState.putParcelable(BUNDLE_SAVE_ADDRESS, mLastAddress);
        }
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            mLastKnowLocation = savedInstanceState.getParcelable(BUNDLE_SAVE_LOCATION);
            mLastAddress = savedInstanceState.getParcelable(BUNDLE_SAVE_ADDRESS);
        }
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
        switch (requestCode) {
            case Geolocation.REQUEST_ENABLE_GPS:
                if (resultCode == Activity.RESULT_OK) {
                    geolocation.getLastKnowLocation(this);
                }
                break;
        }
    }

    private void updateLastAddress() {
        /**
         * Pegar o nome do estado e converter em Sigla
         * */
        String adminArea = mLastAddress.getAdminArea();
        StringBuilder code = new StringBuilder();
        if(adminArea != null) {
            StringTokenizer tk = new StringTokenizer(adminArea, " ");
            for (int i = 0; i < 2 && tk.hasMoreElements(); i++) {
                code.append(tk.nextToken().substring(0, 1));
            }
        }

        String log = String.format(Locale.getDefault()
                ,"\nFeature Name: %s\nURL: %s\nLocality: %s\nLatLog: %f %f\nAdminArea: %s\nPhone: %s\nPostal Code: %s\nPremises: %s\nThroroughfare: %s\n"
                , mLastAddress.getFeatureName()
                , mLastAddress.getUrl()
                , mLastAddress.getLocality()
                , mLastAddress.getLatitude()
                , mLastAddress.getLongitude()
                , code
                , mLastAddress.getPhone()
                , mLastAddress.getPostalCode()
                , mLastAddress.getPremises()
                , mLastAddress.getThoroughfare()
        );

        textViewAddress.setText(log);
    }

    private void updateLastLocation() {
        /**
         * Atualizar o texto
         * */
        textViewLocation.setText(String.format(Locale.getDefault()
                ,"LatLon: (%f, %f)\nAltitude: %f\nPrecisao: %f\nProvedor: %s\n"
                , mLastKnowLocation.getLatitude()
                , mLastKnowLocation.getLongitude()
                , mLastKnowLocation.getAltitude()
                , mLastKnowLocation.getAccuracy()
                , mLastKnowLocation.getProvider()
                )
        );
    }
}
