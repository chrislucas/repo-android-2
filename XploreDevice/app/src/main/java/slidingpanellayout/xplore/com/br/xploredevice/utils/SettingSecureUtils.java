package slidingpanellayout.xplore.com.br.xploredevice.utils;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.UriPermission;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;

import java.util.List;
import java.util.Locale;

/**
 * Created by r028367 on 26/01/2018.
 */

public class SettingSecureUtils {

    @SuppressLint("HardwareIds")
    public static String androidId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }


    public static String allowedGeolocationOrigin(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ALLOWED_GEOLOCATION_ORIGINS);
    }

    public static void loggingUriPermissionInstance(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        List<UriPermission> persistedUriPermissions = contentResolver.getPersistedUriPermissions();
        for (UriPermission uriPermission : persistedUriPermissions) {
            loggingUriPermissionInstance(uriPermission);
        }
        List<UriPermission> outgoingPersistedUriPermissions
                = contentResolver.getOutgoingPersistedUriPermissions();
        for (UriPermission uriPermission : outgoingPersistedUriPermissions) {
            loggingUriPermissionInstance(uriPermission);
        }
    }


    private static void loggingUriPermissionInstance(UriPermission uriPermission) {
        Uri uri = uriPermission.getUri();
        String message = String.format(Locale.getDefault()
                , "URI path: %s.\nAuthority: %s." +
                        "\nEncoded Authority: %s.\nHost: %s.\nPort: %d.\nQuery: %s." +
                        "\nFragment: %s.\nScheme: %s.\nUserInfo: %s\n"
                , uri.getPath()
                , uri.getAuthority()
                , uri.getEncodedAuthority()
                , uri.getHost()
                , uri.getPort()
                , uri.getQuery()
                , uri.getFragment()
                , uri.getScheme()
                , uri.getUserInfo()
        );
        Log.i("URI_PERMISSION", message);
    }

}
