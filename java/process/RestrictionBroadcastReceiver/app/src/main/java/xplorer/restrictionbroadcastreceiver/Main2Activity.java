package xplorer.restrictionbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentProvider;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class Main2Activity extends AppCompatActivity {


    /**
     * https://developer.android.com/guide/components/broadcasts#context-registered-recievers
     *
     * Mudancas no comportamento do sistema de broaddcast
     *
     * Para o android 9.
     *
     * A recpecao da acao {@link Intent#ACTION_MANAGE_NETWORK_USAGE} atraves de
     * um {@link IntentFilter} num Broadcast nao informa dados do usuario mais
     *
     * O sistema de broadcast do WIFI nao contem mais informacoes sobre SSIDs e BSSIDs
     *
     * or scan result
     *
     * Android 8
     *
     * Para apps cujo target eh a api 26+, nao sera permitido declarar receivers para
     * boa parte dos broadcasts implicitos (broadcasts cujo filtro nao aponta para a propria aplicacao).
     *
     * Entretanto o metodo {@link android.content.Context#registerReceiver(BroadcastReceiver, IntentFilter)}
     * ainda pode ser usado
     *
     * Android 7 api24+
     *
     * nao enviam os seguites broadcasts
     * {@link android.hardware.Camera#ACTION_NEW_PICTURE}
     * e {@link android.hardware.Camera#ACTION_NEW_VIDEO}}
     *
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        /**
         * Context register Receiver
         * */
        //IntentFilter intentFilter = new IntentFilter(MyReceiverTest.ACTION_TEST_1);
        //registerReceiver(new MyReceiverTest(), intentFilter);
        Intent explicit = new Intent(); // MyReceiverTest.ACTION_TEST_1 // Intent.ACTION_BOOT_COMPLETED
        //explicit.setAction(Intent.ACTION_POWER_CONNECTED);
        /**
         * Intent.ACTION_INPUT_METHOD_CHANGED)
         * */
        explicit.setAction(MyReceiverTest.ACTION_TEST_1);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            sendBroadcast(explicit);
        }
        else {
            sendBroadcastOnAndroidO(explicit);
        }

        startActivity(new Intent(Intent.ACTION_BUG_REPORT));
    }

    private void sendBroadcastOnAndroidO(Intent explicit) {
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> matches = packageManager.queryBroadcastReceivers(explicit, 0);
        for (ResolveInfo resolveInfo : matches) {
            String packageName = resolveInfo.activityInfo.applicationInfo.packageName;
            String packageName2 = resolveInfo.activityInfo.packageName;
            Log.i("RESOLVE_INFO"
                    , String.format("PckName: %s\n%s", packageName, packageName2));
            ComponentName componentName = new ComponentName(packageName2
                    , resolveInfo.activityInfo.name);
            explicit.setComponent(componentName);
            sendBroadcast(explicit);
        }
    }
}
