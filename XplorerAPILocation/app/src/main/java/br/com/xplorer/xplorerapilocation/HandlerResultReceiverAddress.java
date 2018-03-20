package br.com.xplorer.xplorerapilocation;

import android.location.Address;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import br.com.xplorer.utilsgeolocation.service.AddressResultReceiver;

/**
 * Created by r028367 on 20/03/2018.
 */

public class HandlerResultReceiverAddress extends Handler {

    public interface Callback<T> {
        void successHandler(T data);
        void failureHandler();
    }

    private Callback<Address> callback;

    HandlerResultReceiverAddress(Callback<Address> callback) {
        super();
        this.callback = callback;
    }

    @Override
    public void handleMessage(android.os.Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case AddressResultReceiver.MESSAGE_WHAT:
                Bundle bundle = msg.getData();
                if(bundle != null) {
                    List<Address> addressList = bundle.getParcelableArrayList(AddressResultReceiver.BUNDLE_ADDRESSES);
                    if(addressList != null && addressList.size() > 0) {
                        Address address1 = addressList.get(0);
                        callback.successHandler(address1);
                        for(Address address : addressList) {
                            String adminArea = address.getAdminArea();
                            String log = String.format(Locale.getDefault()
                                    ,"Feature Name: %s\nURL: %s\nLocality: %s\nLatLog: %f %f\nToString: %s\nAdminArea: %s\n"
                                    , address.getFeatureName()
                                    , address.getUrl()
                                    , address.getLocality()
                                    , address.getLatitude()
                                    , address.getLongitude()
                                    , address.toString()
                                    , adminArea
                            );
                            Log.i("ADDRESS", log);
                        }
                    }
                    else {
                        callback.failureHandler();
                    }
                }
                else {
                    callback.failureHandler();
                }
                break;
                default:
                    callback.failureHandler();
        }
    }

    @Override
    public void dispatchMessage(android.os.Message msg) {
        super.dispatchMessage(msg);
    }

    @Override
    public String getMessageName(android.os.Message message) {
        return super.getMessageName(message);
    }

    @Override
    public boolean sendMessageAtTime(android.os.Message msg, long uptimeMillis) {
        return super.sendMessageAtTime(msg, uptimeMillis);
    }
}
