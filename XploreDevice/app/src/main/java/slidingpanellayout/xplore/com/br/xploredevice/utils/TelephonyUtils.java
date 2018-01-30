package slidingpanellayout.xplore.com.br.xploredevice.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;

import java.util.List;

/**
 * Created by r028367 on 26/01/2018.
 */

public class TelephonyUtils {

    public static final int REQUEST_CODE_READ_PHONE_STATE = 0x01;
    public static final int REQUEST_CODE_ACCESS_COARSE_LOCATION = 0x02;

    private static TelephonyManager getTelephonyManager(Context context) {
        return (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    }

    @SuppressLint("HardwareIds")
    public static String getIMEIDevice(Activity activity, DialogInterface.OnClickListener onClickListener) {
        TelephonyManager manager = getTelephonyManager(activity);
        if (ActivityCompat.checkSelfPermission(activity
                , Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_PHONE_STATE)) {
                showAlertDialog(activity
                        , "Permissão para acesso do seu dispositvo"
                        , String.format("Para o bom funcionamento desse App precisamos " +
                                        "da sua permissão ´para acessar informações do seu Aparelho.\n%s"
                                , "READ_PHONE_STATE")
                        , onClickListener
                );
            }

            ActivityCompat.requestPermissions(
                    activity
                    , new String[]{Manifest.permission.READ_PHONE_STATE}
                    , REQUEST_CODE_READ_PHONE_STATE
            );

            return null;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return manager.getImei();
        } else {
            return manager.getDeviceId();
        }
    }

    public static List<CellInfo> getListCellInfo(Activity activity
            , DialogInterface.OnClickListener onClickListener) {
        TelephonyManager manager = getTelephonyManager(activity);
        if (ActivityCompat.checkSelfPermission(activity
                , Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(activity
                    , new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}
                    , REQUEST_CODE_ACCESS_COARSE_LOCATION);

            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                showAlertDialog(activity
                        , "Permissão para acesso do seu dispositvo"
                        , String.format("Para o bom funcionamento desse App precisamos " +
                                        "da sua permissão ´para acessar informações do seu Aparelho.\n%s"
                                , "ACCESS_COARSE_LOCATION")
                        , onClickListener
                );
            }

            return null;
        } else {
            return manager.getAllCellInfo();
        }
    }

    public static String getMeID(Activity activity, DialogInterface.OnClickListener onClickListener) {
        TelephonyManager manager = getTelephonyManager(activity);
        if (ActivityCompat.checkSelfPermission(activity
                , Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_PHONE_STATE)) {
                showAlertDialog(activity
                        , "Permissão para acesso do seu dispositvo"
                        , String.format("Para o bom funcionamento desse App precisamos " +
                                        "da sua permissão ´para acessar informações do seu Aparelho.\n%s"
                                , "READ_PHONE_STATE")
                        , onClickListener
                );
            }

            ActivityCompat.requestPermissions(
                    activity
                    , new String[]{Manifest.permission.READ_PHONE_STATE}
                    , REQUEST_CODE_READ_PHONE_STATE
            );

            return null;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return manager.getMeid();
        } else {
            return getIMEIDevice(activity, onClickListener);
        }
    }

    @SuppressLint("HardwareIds")
    public static String getTelephonySubscriberId(Activity activity
            , DialogInterface.OnClickListener onClickListener) {
        TelephonyManager manager = getTelephonyManager(activity);
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_PHONE_STATE)) {
                showAlertDialog(activity
                        , "Permissão para acesso do seu dispositvo"
                        , String.format("Para o bom funcionamento desse App precisamos " +
                                        "da sua permissão ´para acessar informações do seu Aparelho.\n%s"
                                , "READ_PHONE_STATE")
                        , onClickListener
                );
            }
            ActivityCompat.requestPermissions(
                    activity
                    , new String[]{Manifest.permission.READ_PHONE_STATE}
                    , REQUEST_CODE_READ_PHONE_STATE
            );
            return null;
        } else
            return manager.getSubscriberId();
    }


    public static ServiceState getTelephonyServiceState(Activity activity, DialogInterface.OnClickListener onClickListener) {
        TelephonyManager manager = getTelephonyManager(activity);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_PHONE_STATE)) {
                    showAlertDialog(activity
                            , "Permissão para acesso do seu dispositvo"
                            , String.format("Para o bom funcionamento desse App precisamos " +
                                            "da sua permissão ´para acessar informações do seu Aparelho.\n%s"
                                    , "READ_PHONE_STATE")
                            , onClickListener
                    );
                }
                ActivityCompat.requestPermissions(
                        activity
                        , new String[]{Manifest.permission.READ_PHONE_STATE}
                        , REQUEST_CODE_READ_PHONE_STATE
                );
                return null;
            }
            return manager.getServiceState();
        }
        return null;
    }

    public static void m(Activity activity, DialogInterface.OnClickListener onClickListener) {
        TelephonyManager manager = getTelephonyManager(activity);
    }

    public static void addPhoneStateListeners(Activity activity, PhoneStateListener phoneStateListener) {

        if(ActivityCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity
                    , new String[] {Manifest.permission.ACCESS_COARSE_LOCATION}
                    , REQUEST_CODE_ACCESS_COARSE_LOCATION);
        }

        else {
            getTelephonyManager(activity.getApplicationContext()).listen(phoneStateListener
                    , PhoneStateListener.LISTEN_SIGNAL_STRENGTHS
                            | PhoneStateListener.LISTEN_CALL_STATE
                            | PhoneStateListener.LISTEN_CELL_INFO
                            | PhoneStateListener.LISTEN_CELL_LOCATION
                            | PhoneStateListener.LISTEN_SERVICE_STATE
                            | PhoneStateListener.LISTEN_DATA_ACTIVITY
                            | PhoneStateListener.LISTEN_CALL_FORWARDING_INDICATOR
                            | PhoneStateListener.LISTEN_MESSAGE_WAITING_INDICATOR
                            | PhoneStateListener.LISTEN_DATA_CONNECTION_STATE
            );
        }
    }

    private static void showAlertDialog(Activity activity
            , String title, String message, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity.getApplicationContext());
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setPositiveButton("Ok", onClickListener);
        alertDialogBuilder.create().show();
    }

}
