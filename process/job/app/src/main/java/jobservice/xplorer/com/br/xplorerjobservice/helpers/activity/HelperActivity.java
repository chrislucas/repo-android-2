package jobservice.xplorer.com.br.xplorerjobservice.helpers.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.List;

public class HelperActivity {


    private static ActivityManager getActivityManager(Context context) {
        return (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static List<ActivityManager.AppTask> getAppTasks(Context context) {
        ActivityManager am = getActivityManager(context);
        return am.getAppTasks();
    }

    public static List<ActivityManager.RunningAppProcessInfo> getAppProcessInfo(Context context) {
        ActivityManager am = getActivityManager(context);
        return am.getRunningAppProcesses();
    }

    public static List<ActivityManager.ProcessErrorStateInfo> getProcessesInErrorState(Context context) {
        ActivityManager am = getActivityManager(context);
        return am.getProcessesInErrorState();
    }


}
