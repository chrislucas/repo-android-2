package br.com.xplorer.utilsgeolocation.service;

import android.location.Address;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.ResultReceiver;

import java.util.List;

/**
 * Created by r028367 on 19/03/2018.
 */

public class AddressResultReceiver extends ResultReceiver {


    private List<Address> addressList;

    public static final String BUNDLE_ADDRESSES = "BUNDLE_ADDRESSES";

    public static final int RESULT_SUCCESS = 0x13;
    public static final int RESULT_FAILURE = 0x12;


    public AddressResultReceiver(Handler handler) {
        super(handler);
    }

    @Override
    public void send(int resultCode, Bundle resultData) {
        super.send(resultCode, resultData);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        super.onReceiveResult(resultCode, resultData);
        addressList = resultData.getParcelableArrayList(BUNDLE_ADDRESSES);
    }

    @Override
    public int describeContents() {
        return super.describeContents();
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
    }
}
