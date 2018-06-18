package jobservice.xplorer.com.br.xplorerjobservice.notification;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;

public class HelperTaskStackBuilder {

    private static HelperTaskStackBuilder instance = null;

    private HelperTaskStackBuilder() {}


    public static HelperTaskStackBuilder getInstance() {
        if (instance == null) {
            instance = new HelperTaskStackBuilder();
        }
        return instance;
    }

    /**
     * Links
     * https://developer.android.com/reference/android/support/v4/app/TaskStackBuilder
     * https://developer.android.com/training/notify-user/navigation
     *
     * {@link TaskStackBuilder}
     *
     *
     **/

    public TaskStackBuilder getTaskStackBuilder(Context context, int requestCode, Intent intent
            , int flags) {
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context);
        //
        taskStackBuilder.addParentStack(intent.getComponent());
        //
        taskStackBuilder.addNextIntent(intent);
        return taskStackBuilder;
    }

}
