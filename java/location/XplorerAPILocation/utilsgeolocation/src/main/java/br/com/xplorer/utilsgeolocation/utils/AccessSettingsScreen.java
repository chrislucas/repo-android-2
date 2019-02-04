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
         * https://developer.android.com/reference/android/net/Uri.html#fromParts(java.lang.String,%20java.lang.String,%20java.lan.String)
         * https://stackoverflow.com/questions/48485068/difference-between-uri-fromparts-and-uri-parse
         * */
        // BuildConfig.APPLICATION_ID
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        Log.i("OPEN_APP_D_SETTINGS", uri.toString());
        intent.setData(uri);
        //Intent.FLAG_ACTIVITY_NEW_TASK, Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void openApplicationDevelopmentSettings(Context context) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS);
        context.startActivity(intent);
    }

    public static void openLocationSourceSettings(Context context) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        context.startActivity(intent);
    }

}
