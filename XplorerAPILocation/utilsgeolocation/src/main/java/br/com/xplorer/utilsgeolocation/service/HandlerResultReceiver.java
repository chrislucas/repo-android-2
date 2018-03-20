package br.com.xplorer.utilsgeolocation.service;

import android.location.Address;
import android.os.Bundle;
import android.os.Handler;

import java.util.List;

/**
 * Created by r028367 on 20/03/2018.
 */

public class HandlerResultReceiver extends Handler {
    public interface Callback {
        void successHandler(List<Address> data);
        void failureHandler();
    }

    private Callback callback;

    public HandlerResultReceiver(Callback callback) {
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
                        callback.successHandler(addressList);
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
