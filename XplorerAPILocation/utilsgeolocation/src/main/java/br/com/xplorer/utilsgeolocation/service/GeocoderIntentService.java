package br.com.xplorer.utilsgeolocation.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.ResultReceiver;

import java.util.ArrayList;
import java.util.List;

import br.com.xplorer.utilsgeolocation.impl.GeocoderImpl;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class GeocoderIntentService extends IntentService {

    public static final String BUNDLE_LOCATION = "BUNDLE_LOCATION";
    public static final String BUNDLE_RESULT_RECEIVER = "BUNDLE_RESULT_RECEIVER";

    public GeocoderIntentService() {
        super("GeocoderIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Location location = bundle.getParcelable(BUNDLE_LOCATION);
                ResultReceiver resultReceiver = bundle.getParcelable(BUNDLE_RESULT_RECEIVER);
                GeocoderImpl geocoderImpl = new GeocoderImpl(this);
                List<Address> addresses =geocoderImpl.getListAddress(location.getLatitude()
                        , location.getLongitude(), 3);
                Bundle b = new Bundle();
                b.putParcelableArrayList(AddressResultReceiver.BUNDLE_ADDRESSES, (ArrayList<? extends Parcelable>) addresses);
                resultReceiver.send(addresses.size() == 0 ? AddressResultReceiver.RESULT_FAILURE : AddressResultReceiver.RESULT_SUCCESS, b);
            }
        }
    }
}
