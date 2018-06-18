package br.com.xplorer.utilsgeolocation.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by r028367 on 16/03/2018.
 */

public class NetworkingConnection {

    public static boolean simpleVerificationIfIsConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo[] infoNetWorks = manager.getAllNetworkInfo();
            for(NetworkInfo net : infoNetWorks) {
                if(net != null) {
                    /**
                     * Indicates whether network connectivity exists or is in the process
                     * of being established. This is good for applications that need to
                     * do anything related to the network other than read or write data.
                     * For the latter, call {@link #isConnected()} instead, which guarantees
                     * that the network is fully usable.
                     * @return {@code true} if network connectivity exists or is in the process
                     * of being established, {@code false} otherwise.
                     */
                    Log.i("CONNECTED_OR_CONNECTING", String.valueOf(net.isConnectedOrConnecting()));
                    /**
                     * Indicates whether network connectivity exists and it is possible to establish
                     * connections and pass data.
                     * <p>Always call this beforeRequest attempting to perform data transactions.
                     * @return {@code true} if network connectivity exists, {@code false} otherwise.
                     */
                    boolean isConnected = net.isConnected();
                    if(isConnected)
                        return true;
                }
            }
        }
        return false;
    }

    public static boolean testMobileConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        }
        return networkInfo != null && networkInfo.isConnected();
    }


    public static boolean testWifiConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        }
        return networkInfo != null && networkInfo.isConnected();
    }

    /**
     * Pesquisar
     * https://android.googlesource.com/platform/frameworks/base/+/master/core/java/android/net
     * https://android.googlesource.com/platform/frameworks/base/+/master/core/java/android/net/IConnectivityManager.aidl
     * https://stackoverflow.com/questions/26539445/the-setmobiledataenabled-method-is-no-longer-callable-as-of-android-l-and-later
     * https://github.com/moezbhatti/qksms/issues/598
     * */

    public static void enabledMobileNetwork(Context context, boolean status) {
        try {
            final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null) {
                final Class clazzNameConnManager = Class.forName(connectivityManager.getClass().getName());
                /**
                 *
                 * atributo mService da classe  {@link ConnectivityManager}
                 * */
                final Field field = clazzNameConnManager.getDeclaredField("mService");
                // tornando o atributo da classe ConnectivityManager public
                field.setAccessible(true);
                // valor do campo mService
                Object manager = field.get(connectivityManager);
                final Class<?> className = Class.forName(manager.getClass().getName());
                final Method setMethodDataEnabledMethod = className.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
                setMethodDataEnabledMethod.setAccessible(true);
                setMethodDataEnabledMethod.invoke(manager, status);
            }

        } catch (NoSuchFieldException | NoSuchMethodException | ClassNotFoundException
                | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            Log.e("SET_MOBILE_DATA", e.getMessage());
        }
    }
}
