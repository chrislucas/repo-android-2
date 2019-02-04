package xplorer.restrictionbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.List;

public class UtilsBroadcastReceiver {


    public static void sendBroadcast(Intent intent, Context context,  int flag) {
        PackageManager packageManager = context.getPackageManager();
        /**
         *
         **/
        List<ResolveInfo> broadcastReceivers = packageManager.queryBroadcastReceivers(intent, flag);
        for (ResolveInfo resolveInfo : broadcastReceivers) {
            Intent explicitIntent = new Intent(intent);
            String packageName = resolveInfo.activityInfo.applicationInfo.packageName;
            String appName = resolveInfo.activityInfo.name;
            ComponentName componentName = new ComponentName(packageName, appName);
            explicitIntent.setComponent(componentName);
            context.sendBroadcast(explicitIntent);
        }
    }

    public static void contextRegisterReceiver(Context context, BroadcastReceiver receiver, IntentFilter intentFilter) {
        context.registerReceiver(receiver, intentFilter);
    }

    public static void contextUnregisterReceiver(Context context, BroadcastReceiver receiver) {
        context.unregisterReceiver(receiver);
    }
}
