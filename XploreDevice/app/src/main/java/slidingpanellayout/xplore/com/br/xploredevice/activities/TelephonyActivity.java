package slidingpanellayout.xplore.com.br.xploredevice.activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import slidingpanellayout.xplore.com.br.xploredevice.R;
import slidingpanellayout.xplore.com.br.xploredevice.utils.wrapperproviders.WrapperContentProvider;
import slidingpanellayout.xplore.com.br.xploredevice.utils.wrapperproviders.WrapperTelephonyContentProvider;

public class TelephonyActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION_READ_SMS = 0x0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telephony);

        if( ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_SMS)) {
                // Mostrar uma mensagem caso necessario
            }
            ActivityCompat.requestPermissions(
                    this
                , new String[] {
                    Manifest.permission.READ_SMS
                }
                , REQUEST_PERMISSION_READ_SMS
            );
        }

        else {
            execute();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_PERMISSION_READ_SMS) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                execute();
            }
        }
    }

    private void execute() {
        logUri("URI_DATA_SMS", WrapperContentProvider.getUriWithAppendedId(Telephony.Sms.CONTENT_URI, 1));
        logUri("URI_DATA_CARRIERS", WrapperContentProvider.getUriWithAppendedId(Telephony.Carriers.CONTENT_URI, 1));
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                getCarriers(TelephonyActivity.this.getApplicationContext());
            }
        });
    }

    private void logUri(String tag, Uri uri) {
        Log.i(tag,
                String.format("Path: %s.\nUserInfo: %s.\nScheme: %s.\nQuery: %s.\nPackage: %s.\n"
                        , uri.getPath()
                        , uri.getUserInfo()
                        , uri.getScheme()
                        , uri.getQuery()
                        , Telephony.Sms.getDefaultSmsPackage(this)
                )
        );
    }

    private void getSmsContent(Context context) {
        WrapperTelephonyContentProvider.executeSimpleQuery(
            context
            , Telephony.Sms.CONTENT_URI
            , new WrapperTelephonyContentProvider.CProviderSms()
            , WrapperTelephonyContentProvider.CProviderSms.COLUMNS
            , null
            , null
            , null
        );
    }

    private void getSmsConversation(Context context) {
        WrapperTelephonyContentProvider.executeSimpleQuery(
            context
            , Telephony.Sms.Conversations.CONTENT_URI
            , new WrapperTelephonyContentProvider.CProviderConversation()
            , WrapperTelephonyContentProvider.CProviderConversation.COLUMNS
            , null
            , null
            , null
        );
    }

    private void getCarriers(Context context) {
        WrapperTelephonyContentProvider.executeSimpleQuery(
            context
            , Telephony.Carriers.CONTENT_URI
            , new WrapperTelephonyContentProvider.CProviderCarriers()
            , WrapperTelephonyContentProvider.CProviderCarriers.COLUMNS
            , null
            , null
            , null
        );
    }
}
