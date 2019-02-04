package slidingpanellayout.xplore.com.br.xploredevice.activities;

import android.Manifest;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.UserDictionary;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import slidingpanellayout.xplore.com.br.xploredevice.R;
import slidingpanellayout.xplore.com.br.xploredevice.utils.ArrayUtils;
import slidingpanellayout.xplore.com.br.xploredevice.utils.wrapperproviders.WrapperContentProvider;
import slidingpanellayout.xplore.com.br.xploredevice.utils.wrapperproviders.WrapperUserDictionaryContentProvider;

public class UserDictActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dict);

        Uri uri = WrapperContentProvider.getUriWithAppendedId(UserDictionary.CONTENT_URI, 1);
        Log.i("URI_USER_DICT", String.format("PATH URI: %s.\nURI: %s.\n"
                , uri.getPath(), uri.toString()));

        Uri uri2 = WrapperContentProvider.getUriWithAppendedId(UserDictionary.Words.CONTENT_URI, 1);
        Log.i("URI_USER_DICT", String.format("PATH URI: %s.\nURI: %s.\n"
                , uri2.getPath(), uri2.toString()));
        sample();
    }

    public void sample() {
        final String subArray [] = new String[3];
        ArrayUtils
                .buildSubArray(WrapperUserDictionaryContentProvider.ALL_COLUMNS, subArray, 0, 3);

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                WrapperUserDictionaryContentProvider.makeSimpleQuery(
                        UserDictActivity.this.getApplicationContext()
                        , subArray
                        , null
                        , null
                        , null
                );
            }
        });

        WrapperUserDictionaryContentProvider.makeSimpleQuery(
                this.getApplicationContext()
                , WrapperUserDictionaryContentProvider.ALL_COLUMNS
                , UserDictionary.Words.WORD + " = ?"
                , new String [] {"inteligência"}
                , null
        );

        WrapperUserDictionaryContentProvider.makeSimpleQuery(
                this.getApplicationContext()
                , new String[] {UserDictionary.Words._ID, UserDictionary.Words.WORD, UserDictionary.Words.LOCALE}
                ,null
                , null
                , null
        );
    }
}
