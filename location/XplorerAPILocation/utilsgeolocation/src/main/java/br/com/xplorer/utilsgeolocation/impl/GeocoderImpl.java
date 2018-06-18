package br.com.xplorer.utilsgeolocation.impl;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by r028367 on 19/03/2018.
 */

public class GeocoderImpl {

    private final Geocoder geocoder;

    public GeocoderImpl(Context context) {
        geocoder = new Geocoder(context);
    }

    public GeocoderImpl(Context context, Locale locale) {
        geocoder = new Geocoder(context, locale);
    }

    public List<Address> getListAddress(double latitude, double longitude, int limitResultAddress) {
        List<Address> addressList = new ArrayList<>();
        try {
            addressList = geocoder.getFromLocation(latitude, longitude, limitResultAddress);
        } catch (IOException e) {
            String message = e.getMessage() == null ? "Não foi possível capturar o erro" : e.getMessage();
            Log.e("EXCP_LIST_ADDRESS", message);
        }
        return addressList;
    }

    public boolean isImplFromLocationAndFromLocationName() {
        return Geocoder.isPresent();
    }
}
