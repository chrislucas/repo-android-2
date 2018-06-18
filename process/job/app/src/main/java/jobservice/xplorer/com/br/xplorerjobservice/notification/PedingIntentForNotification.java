package jobservice.xplorer.com.br.xplorerjobservice.notification;


import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@IntDef({
        PedingIntentForNotification.SIMPLE_PENDING_INTENT
        , PedingIntentForNotification.PENDING_INTENT_TASK_BUILDER
        , PedingIntentForNotification.PENDING_INTENT_START_SERVICE
        , PedingIntentForNotification.PENDING_INTENT_START_BROADCAST
        , PedingIntentForNotification.PENDING_INTENT_FOREGROUND_SERVICE
        , PedingIntentForNotification.PENDING_INTENT_ACTIVITIES})

@interface PedingIntentForNotification {
    int SIMPLE_PENDING_INTENT = 0
        , PENDING_INTENT_TASK_BUILDER = 1
        , PENDING_INTENT_START_SERVICE = 2
        , PENDING_INTENT_START_BROADCAST = 3
        , PENDING_INTENT_FOREGROUND_SERVICE = 4
        , PENDING_INTENT_ACTIVITIES = 5;
}
