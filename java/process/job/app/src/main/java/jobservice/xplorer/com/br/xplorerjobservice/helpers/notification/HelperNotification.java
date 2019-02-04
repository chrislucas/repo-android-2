package jobservice.xplorer.com.br.xplorerjobservice.helpers.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.media.MediaBrowserCompat;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

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
        return builder
                .setContentTitle(title)
                .setContentText(text)
                .setChannelId(channelId)
                // para a notificacao sumir quando o usuario tocar nela
                .setContentIntent(PendingIntent.getActivity(context,  0, new Intent(), 0));
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

    public void show(Context context, Notification notification, int id) {
        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        manager.notify(id, notification);
    }

    public void cancel(Context context, int id) {
        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        manager.cancel(id);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({NotificationManager.IMPORTANCE_DEFAULT
            , NotificationManager.IMPORTANCE_HIGH
            , NotificationManager.IMPORTANCE_LOW
            //, NotificationManager.IMPORTANCE_MAX
            , NotificationManager.IMPORTANCE_MIN
            , NotificationManager.IMPORTANCE_NONE
            , NotificationManager.IMPORTANCE_UNSPECIFIED
        })
    public @interface Importance{}

    public NotificationChannel createNotificationChannel(Context context, CharSequence name
            , String description, String idChannel,  @Importance int importance) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(idChannel, name, importance);
            notificationChannel.setDescription(description);
            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
                return notificationChannel;
            }
        }
        return null;
    }
}
