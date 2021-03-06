package br.com.xplorer.utilsgeolocation.service;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.xplorer.utilsgeolocation.impl.GeocoderImpl;
import br.com.xplorer.utilsgeolocation.resultreceiver.AddressResultReceiver;
import br.com.xplorer.utilsgeolocation.resultreceiver.ResultReceiverCode;

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
    public static final String BUNDLE_LIMIT_RESULT = "BUNDLE_LIMIT_RESULT";

    public GeocoderIntentService() {
        super("GeocoderIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            Bundle extras = intent.getExtras();
            if (extras != null) {
                Location location = extras.getParcelable(BUNDLE_LOCATION);
                ResultReceiver resultReceiver = extras.getParcelable(BUNDLE_RESULT_RECEIVER);
                int limitResult = extras.getInt(BUNDLE_LIMIT_RESULT, 3);
                GeocoderImpl geocoderImpl = new GeocoderImpl(this);
                List<Address> addresses = null;
                if (location != null) {
                    addresses = geocoderImpl.getListAddress(location.getLatitude()
                            , location.getLongitude(), limitResult);
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList(AddressResultReceiver.BUNDLE_ADDRESSES
                            , (ArrayList<? extends Parcelable>) addresses);
                    if (resultReceiver != null) {
                        resultReceiver.send(addresses.size() == 0
                                ? ResultReceiverCode.RESULT_FAILURE : ResultReceiverCode.RESULT_SUCCESS, bundle);
                    }
                }
                else {
                    Toast.makeText(this
                            , "Não foi possível iniciar o serviço de busca de endereço"
                            , Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
