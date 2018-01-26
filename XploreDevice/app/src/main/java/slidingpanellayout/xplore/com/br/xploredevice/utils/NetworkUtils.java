package slidingpanellayout.xplore.com.br.xploredevice.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

/**
 * Created by r028367 on 26/01/2018.
 */

public class NetworkUtils {


    public static final String NOT_CONNECTED = "-";
    public static final String UNDEFINED_NETWORK_TYPE = "???";

    public static WifiManager getWifiManager(Context context) {
        return (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }


    public static ConnectivityManager getConnectivityManager(Context context) {
        return (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public static NetworkInfo getNetworkInfo(Context context) {
        return getConnectivityManager(context).getActiveNetworkInfo();
    }

    public static boolean isAvailable(Context context) {
        return  getNetworkInfo(context).isAvailable();
    }


    public static boolean isRoaming(Context context) {
        return  getNetworkInfo(context).isRoaming();
    }


    public static boolean isConnectedOrConnecting(Context context) {
        return  getNetworkInfo(context).isConnectedOrConnecting();
    }

    public static boolean isFailover(Context context) {
        return  getNetworkInfo(context).isFailover();
    }


    public static boolean isConnected(Context context) {
        return  getNetworkInfo(context).isConnected();
    }



    public static String getTypeConnectivity(Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context);

        if(networkInfo == null || ! isConnected(context))
            return NOT_CONNECTED;

        int typeNetwork = networkInfo.getType();

        String type = UNDEFINED_NETWORK_TYPE ;

        if(typeNetwork == ConnectivityManager.TYPE_WIFI) {
            return "Wifi";
        }
        else {
            if(typeNetwork == ConnectivityManager.TYPE_MOBILE) {
                int subtypeNetowrk = networkInfo.getSubtype();
                switch (subtypeNetowrk) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                        type = "2g";
                        break;
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                        type = "3g";
                        break;
                    case TelephonyManager.NETWORK_TYPE_LTE:
                        type = "4g";
                        break;
                }
            }
        }

        return type;
    }

    public static NetworkInfo.DetailedState getDetailedState(Context context) {
        return  getNetworkInfo(context).getDetailedState();
    }


    public static NetworkInfo.State getStateNetWorkInfo(Context context) {
        return getNetworkInfo(context).getState();
    }

}
