package br.com.xplorer.utilsgeolocation.service;

import android.location.Address;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by r028367 on 19/03/2018.
 */

public class AddressResultReceiver extends ResultReceiver {

    private List<Address> addressList;
    public static final String BUNDLE_ADDRESSES = "BUNDLE_ADDRESSES";
    public static final int RESULT_SUCCESS = 0x13;
    public static final int RESULT_FAILURE = 0x12;
    public static final int MESSAGE_WHAT = 0x255;


    private Handler handler;

    public AddressResultReceiver(Handler handler) {
        super(handler);
        this.handler = handler;
    }

    @Override
    public void send(int resultCode, Bundle resultData) {
        super.send(resultCode, resultData);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        super.onReceiveResult(resultCode, resultData);
        addressList = resultData.getParcelableArrayList(BUNDLE_ADDRESSES);
        Message message = new Message();
        message.what = MESSAGE_WHAT;
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(BUNDLE_ADDRESSES, (ArrayList<? extends Parcelable>) addressList);
        message.setData(bundle);
        handler.sendMessage(message);
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
