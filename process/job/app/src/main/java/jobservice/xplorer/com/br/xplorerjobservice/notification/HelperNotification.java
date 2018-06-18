package jobservice.xplorer.com.br.xplorerjobservice.notification;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

public class HelperNotification {

    private static HelperNotification instance = null;

    private HelperNotification() { }

    public synchronized static HelperNotification getInstance() {
        if (instance == null) {
            instance = new HelperNotification();
        }
        return instance;
    }


    /**
     *
     *
     * {@link android.widget.RemoteViews}
     * {@link android.widget.RemoteViews.RemoteView}
     *
     * Classe que descreve a hierarquia de Views que podem ser mostradas em outros
     * processos. Esse objeto eh inflado a partir de um arquivo de layout e atraves
     * dele podemos modificar alguns atributos do elemento inflado
     *
     * https://developer.android.com/reference/android/widget/RemoteViews
     *
     * @param context
     *
     * O objeto context eh responsavel por inflar a View
     *
     * @param title
     *
     * @param text
     *
     * @param channelId
     * */
    public NotificationCompat.Builder getNotificationCompatBuilder(Context context
            , CharSequence title, CharSequence text, String channelId) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId);
        builder.setContentTitle(title);
        builder.setContentText(text);
        builder.setChannelId(channelId);
        return builder;
    }


    @Nullable
    public NotificationCompat.Builder getNotificationBuilderToOpenActivity(Context context
            , CharSequence title, CharSequence text, String channelId, Intent intent, int requestCode, int flags) {
        NotificationCompat.Builder builder = getNotificationCompatBuilder(context, title, text, channelId);
        HelperPendingIntent instance = HelperPendingIntent.getInstance();
        if (instance != null) {
            PendingIntent pendingIntent = instance.getPendingIntentActivity(context, requestCode, intent , flags);
            builder.setContentIntent(pendingIntent);
            return builder;
        }
        return null;
    }

    /**
     * Usando a flag {@link PendingIntent#FLAG_NO_CREATE}
     * */
    @Nullable
    public NotificationCompat.Builder getNotificationBuilderToOpenActivity(Context context
            , CharSequence title, CharSequence text, String channelId, Intent intent, int requestCode) {
        return getNotificationBuilderToOpenActivity(context, title, text, channelId, intent
                , requestCode, PendingIntent.FLAG_ONE_SHOT);
    }

}
