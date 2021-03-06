package notification.xplorer.createnotifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

import vc.com.icomon.helpernotification.notification.HelperNotification;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {


    private static final int ID = 0xff;
    private static final String TAG = Main2Activity.class.getSimpleName();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.simple_notification:
                simpleNotification();
                break;
            case R.id.ongoing_notification:
                onGoingNotification();
                break;
            case R.id.big_notification:
                notificationWithBigTextStyle();
                break;
            case R.id.expandable_notification:
                expandableNotification();
                break;
            case R.id.open_activity_2:
                openActivityWithTap();
                break;
            case R.id.custom_notification:
                notificationWithCustomView();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findViewById(R.id.simple_notification).setOnClickListener(this);
        findViewById(R.id.ongoing_notification).setOnClickListener(this);
        findViewById(R.id.big_notification).setOnClickListener(this);
        findViewById(R.id.expandable_notification).setOnClickListener(this);
        findViewById(R.id.open_activity_2).setOnClickListener(this);
        findViewById(R.id.custom_notification).setOnClickListener(this);
    }

    private void test() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String channelId = "\"0x33\"";
            Notification.Builder b = new Notification.Builder(this, channelId);
            b.setContentTitle("Notificacao Simples : )")
                    .setContentText("Aqui temos uma simples notificação maroto !!!")
                    .setChannelId(channelId)
                    // para a notificacao sumir quando o usuario tocar nela
                    .setContentIntent(PendingIntent.getActivity(this,  0, new Intent(), 0));
            b.setSmallIcon(R.drawable.ic_notifications);
            b.setAutoCancel(true);

            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);

            b.setChannelId(channelId);

            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (nm != null) {
                nm.createNotificationChannel(channel);
                nm.notify("T", 3000, b.build());
            }
        }
    }

    private void simpleNotification() {
        HelperNotification helperNotification = HelperNotification.getInstance();
        NotificationCompat.Builder builder = helperNotification.getNotificationCompatBuilder(this
                , "Notificacao Simples : )"
                , "Aqui temos uma simples notificação maroto !!!"
                , TAG);
        builder.setSmallIcon(R.drawable.ic_notifications);
        builder.setAutoCancel(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(TAG
                    , "Channel human readable title"
                    , NotificationManager.IMPORTANCE_DEFAULT);
            compatShowNotification(helperNotification, builder, channel, TAG, ID);
        }
        else {
            compatShowNotification(helperNotification, builder, ID);
        }
    }

    private NotificationCompat.Builder builderOnGoing;
    private int progress = 0, max = 1000;

    private void onGoingNotification() {
        final HelperNotification helperNotification = HelperNotification.getInstance();
        builderOnGoing = helperNotification.getNotificationCompatBuilder(this
                , "Notificacao Simples : )"
                , "Aqui temos notificação com progress bar!"
                , TAG);

        builderOnGoing.setSmallIcon(R.drawable.ic_notifications);
        builderOnGoing.setAutoCancel(false);
        builderOnGoing.setOngoing(true);
        builderOnGoing.setProgress(max, progress, false);
        final Handler handler = new Handler();
        final long delay = 100;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, delay);
                // atualiza progresso
                builderOnGoing.setProgress(max, progress++, false);
                // mostra notificacao com progresso atualizado
                helperNotification.show(Main2Activity.this, builderOnGoing.build(), ID);
                if (progress == max) {
                    // remove o callback quando atingir uma condicao
                    handler.removeCallbacks(this);
                    // remove a notificacao da tela
                    helperNotification.cancel(Main2Activity.this, ID);
                }
            }
        }, delay);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    TAG, "Channel human readable title", NotificationManager.IMPORTANCE_DEFAULT);
            builderOnGoing.setChannelId(TAG);
            helperNotification.show(this, builderOnGoing.build(), channel, ID);

        }
        else {
            // mostra a notificacao
            helperNotification.show(this, builderOnGoing.build(), ID);
        }
    }

    private void notificationWithBigTextStyle() {
        HelperNotification helperNotification = HelperNotification.getInstance();
        NotificationCompat.Builder builder = helperNotification.getNotificationCompatBuilder(this
                , "Notificação com Estilo"
                , "Uma grande Nofiticação para vc :)"
                , TAG);
        builder.setSmallIcon(R.drawable.ic_notifications);
        builder.setStyle(new NotificationCompat.BigTextStyle()
                .setBigContentTitle("Texto Grande"));
        builder.setAutoCancel(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(TAG
                    , "Channel human readable title"
                    , NotificationManager.IMPORTANCE_DEFAULT);
            compatShowNotification(helperNotification, builder, channel, TAG, ID);
        }
        else {
            compatShowNotification(helperNotification, builder, ID);
        }
    }

    private void expandableNotification() {
        HelperNotification helperNotification = HelperNotification.getInstance();
        NotificationCompat.Builder builder = helperNotification
                .getNotificationCompatBuilder(this
                        , "Notificação expandível"
                        , "Uma notificação expandível marota :)"
                        , TAG);
        builder.setSmallIcon(R.drawable.ic_notifications);
        builder.setStyle(new NotificationCompat.BigPictureStyle()
                .bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.bigpic))
                .bigLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.big_ic_notification_sample))
        );
        builder.setAutoCancel(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(TAG
                    , "Channel human readable title"
                    , NotificationManager.IMPORTANCE_DEFAULT);
            compatShowNotification(helperNotification, builder, channel, TAG, ID);
        }
        else {
            compatShowNotification(helperNotification, builder, ID);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void compatShowNotification(HelperNotification helperNotification
            , NotificationCompat.Builder builder, NotificationChannel channel, String channelId, int notificationId) {
        builder.setChannelId(channelId);
        helperNotification.show(this, builder.build(), channel, notificationId);
    }

    private void compatShowNotification(HelperNotification helperNotification
            , NotificationCompat.Builder builder, int id) {
        builder.setChannelId(TAG);
        helperNotification.show(this, builder.build(), id);
    }

    public static final int REQUEST_CODE_OPEN_ACTIVITY_2 = 0xfa;

    public void openActivityWithTap() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        HelperNotification helperNotification = HelperNotification.getInstance();
        NotificationCompat.Builder builder = helperNotification.getNotificationBuilderToOpenActivity(
                this
                , "Abrir Activity2"
                , "Abrindo uma activity via Notificacao"
                , TAG
                , intent
                , REQUEST_CODE_OPEN_ACTIVITY_2
                , PendingIntent.FLAG_ONE_SHOT
        );
        if (builder != null) {
            builder.setSmallIcon(R.drawable.ic_notifications);
            builder.setStyle(new NotificationCompat.BigPictureStyle()
                    .bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.bigpic))
                    .bigLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.big_ic_notification_sample))
            );
            builder.setAutoCancel(true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(TAG
                        , "Channel human readable title"
                        , NotificationManager.IMPORTANCE_DEFAULT);
                compatShowNotification(helperNotification, builder, channel, TAG, ID);
            }
            else {
                compatShowNotification(helperNotification, builder, ID);
            }
        }
    }

    public void notificationWithCustomView() {
        HelperNotification helperNotification = HelperNotification.getInstance();
        NotificationCompat.Builder builder = helperNotification.getNotificationCompatBuilder(this
                , "Notificação Usando CustomView"
                , "Uma notificação com customView Marota"
                , TAG);

        RemoteViews layout1 = new RemoteViews(getPackageName(), R.layout.custom_layout_notification);
        RemoteViews layout2 = new RemoteViews(getPackageName(), R.layout.custom_layout_notification2);
        builder.setSmallIcon(R.drawable.ic_notifications);
        builder.setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .setCustomContentView(layout1)
                .setCustomBigContentView(layout2)
                // para dar suporte a versoes de S.O > 4.1
                .setContent(layout1);

        builder.setAutoCancel(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(TAG
                    , "Channel human readable title"
                    , NotificationManager.IMPORTANCE_DEFAULT);
            compatShowNotification(helperNotification, builder, channel, TAG, ID);
        }
        else {
            compatShowNotification(helperNotification, builder, ID);
        }
    }
}
