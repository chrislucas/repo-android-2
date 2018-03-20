package br.com.xplorer.xplorerapilocation;

import android.content.Intent;
import android.location.Address;
import android.location.Location;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import br.com.xplorer.utilsgeolocation.callback.DefaultLocationCallbackImpl;
import br.com.xplorer.utilsgeolocation.callback.OnCompleteTaskGoogleApiFusedLocation;
import br.com.xplorer.utilsgeolocation.callback.model.Message;
import br.com.xplorer.utilsgeolocation.service.AddressResultReceiver;
import br.com.xplorer.utilsgeolocation.service.HandlerResultReceiver;
import br.com.xplorer.utilsgeolocation.utils.Geolocation;

/**
 * Activity para testar o resultado final da lib de geolocalizacao
 * */
public class Main3Activity extends AppCompatActivity implements HandlerResultReceiver.Callback, OnCompleteTaskGoogleApiFusedLocation {

    private TextView textViewLocation, textViewAddress;

    private Geolocation geolocation;
    private Location mLastKnowLocation;
    private DefaultLocationCallbackImpl defaultLocationCallback;
    private AddressResultReceiver addressResultReceiver;

    private boolean mRequestingLocationUpdates, mRequestingAddressUpdates;

    public static final String BUNDLE_SAVE_LOCATION = "BUNDLE_SAVE_LOCATION";
    public static final String BUNDLE_SAVE_REQUESTING_LOCATION_UPDATES = "BUNDLE_SAVE_REQUESTING_LOCATION_UPDATES";
    public static final String BUNDLE_SAVE_REQUESTING_ADDRESS_UPDATES = "BUNDLE_SAVE_REQUESTING_ADDRESS_UPDATES";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        textViewLocation = findViewById(R.id.text_view_location);
        textViewAddress = findViewById(R.id.text_view_address);


        addressResultReceiver = new AddressResultReceiver(new HandlerResultReceiver(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        geolocation = new Geolocation(this);
        geolocation.getLastKnowLocation(this);
    }

    @Override
    public void successHandler(List<Address> data) {

    }

    @Override
    public void failureHandler() {

    }

    @Override
    public void successGetLocation(Location location) {

    }

    @Override
    public void failureGetLocation(Message message) {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }

    /**
     * Resposta para solicitacao de permissao de acesso a localizacao do dipositivo
     * */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {

        }
    }

    /**
     * Resposta para a solicitacao para ligar a localizacao no menu do dispositivo
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

        }
    }
}
