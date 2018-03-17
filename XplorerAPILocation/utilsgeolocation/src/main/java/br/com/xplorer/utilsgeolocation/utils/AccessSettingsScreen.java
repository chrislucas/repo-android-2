package br.com.xplorer.utilsgeolocation.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.BuildConfig;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * Created by r028367 on 16/03/2018.
 */

public class AccessSettingsScreen {

    public static void openApplicationDetailsSettings(Context context) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);

        /**
         * https://developer.android.com/reference/android/net/Uri.html#fromParts(java.lang.String,%20java.lang.String,%20java.lang.String)
         * https://stackoverflow.com/questions/48485068/difference-between-uri-fromparts-and-uri-parse
         * */

        Uri uri = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null);
        Log.i("OPEN_APP_D_SETTINGS", uri.getPath());

        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void openApplicationDevelopmentSettings(ContextCompat activity) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS);
    }

    public static void openLocationSourceSettings(Context activity) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
    }

}
