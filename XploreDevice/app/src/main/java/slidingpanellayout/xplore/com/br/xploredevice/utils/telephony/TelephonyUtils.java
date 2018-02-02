package slidingpanellayout.xplore.com.br.xploredevice.utils.telephony;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.telephony.CellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;

import java.util.List;

import slidingpanellayout.xplore.com.br.xploredevice.utils.telephony.callbacks.LogCellTowerInfo;

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

    /**
     * Adicionando Um listener para capturar informacoes de eventos que podem ocorrer com o estado
     * do telefone. Esses eventos podem ser conferidos na class {@link PhoneStateListener#LISTEN_NONE}
     *
     * */
    public static void addPhoneStateListeners(Activity activity, PhoneStateListener phoneStateListener) {
        if (ActivityCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity
                    , new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}
                    , REQUEST_CODE_ACCESS_COARSE_LOCATION);
        } else {
            getTelephonyManager(activity.getApplicationContext()).listen(phoneStateListener
                    ,PhoneStateListener.LISTEN_CALL_STATE
                            | PhoneStateListener.LISTEN_CALL_FORWARDING_INDICATOR
                            | PhoneStateListener.LISTEN_CELL_INFO
                            | PhoneStateListener.LISTEN_CELL_LOCATION
                            | PhoneStateListener.LISTEN_DATA_ACTIVITY
                            | PhoneStateListener.LISTEN_DATA_CONNECTION_STATE
                            | PhoneStateListener.LISTEN_MESSAGE_WAITING_INDICATOR
                            | PhoneStateListener.LISTEN_SIGNAL_STRENGTHS
                            | PhoneStateListener.LISTEN_SERVICE_STATE
            );
        }
    }

    public interface CallbackCellTowerInfo {
        public void callback(List<CellInfo> cellInfos);
    }

    /**
     * https://www.codeproject.com/Questions/1210021/Android-getallcellinfo-getneighboringcellinfo-dete
     * */

    public static void explorerCellTowerInfo(Activity activity) {
        TelephonyManager telephonyManager = getTelephonyManager(activity.getApplicationContext());
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity
                    , new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}
                    , REQUEST_CODE_ACCESS_COARSE_LOCATION);
            return;
        }

        else {
            LogCellTowerInfo logCellTowerInfo = new LogCellTowerInfo();
            logCellTowerInfo.callback(telephonyManager.getAllCellInfo());
        }
    }

    public static void explorerCellTowerInfo(Activity activity, CallbackCellTowerInfo callbackCellTowerInfo) {
        TelephonyManager telephonyManager = getTelephonyManager(activity.getApplicationContext());
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(activity
                    , new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}
                    , REQUEST_CODE_ACCESS_COARSE_LOCATION);
            return;
        }
        else {
            callbackCellTowerInfo.callback(telephonyManager.getAllCellInfo());
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
