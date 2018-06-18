package xplore.com.br.xplorerscreenoverlay.services;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import xplore.com.br.xplorerscreenoverlay.R;
import xplore.com.br.xplorerscreenoverlay.listeners.ImplDefaultDraggable2;
import xplore.com.br.xplorerscreenoverlay.utils.DisplayMetricsUtils;

public class OverlayService extends Service {

    private View viewOriginCoord;
    private Button overlayedButton;
    private WindowManager windowManager;


    public OverlayService() {}

    private View.OnTouchListener touchListenerOnButton;

    private final View.OnClickListener clickListenerOnButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int [] pos = new int [2];
            v.getLocationOnScreen(pos);
            Log.i("ON_CLICK", String.format("Click - View na Posicao(%d %d)", pos[0], pos[1]));
        }
    };

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onCreate() {
        super.onCreate();
        overlayedButton = new Button(this);
        overlayedButton.setPadding(20,20,20,20);
        overlayedButton.setText(getString(R.string.button_overlayed_screen));
        overlayedButton.setTextColor(ContextCompat.getColor(this, R.color.white));
        overlayedButton.setBackgroundColor(ContextCompat.getColor(this, R.color.viewOverlay));
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        Display defaultDisplay = null;
        if (windowManager != null) {
            defaultDisplay = windowManager.getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getMetrics(displayMetrics);
            DisplayMetricsUtils.log(displayMetrics);
        }

        /**
         * WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG
         * WindowManager.LayoutParams.TYPE_APPLICATION_PANEL
         * WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
         * WindowManager.LayoutParams.TYPE_APPLICATION_PANEL
         * Build.VERSION.SDK_INT < Build.VERSION_CODES.O ? WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY : WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
         * */

        /**
         * flag
         * WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
         * WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
         * WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
         * */

        WindowManager.LayoutParams paramsButton = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT
                , WindowManager.LayoutParams.WRAP_CONTENT
                , WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
                , WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                , PixelFormat.TRANSLUCENT);
/**
 *
        WindowManager.LayoutParams paramsButton = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT
                , WindowManager.LayoutParams.MATCH_PARENT
                , WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
                , WindowManager.LayoutParams.FLAG_FULLSCREEN
                , PixelFormat.TRANSLUCENT);
*/
        paramsButton.gravity = Gravity.START | Gravity.TOP;
        paramsButton.x = 0;
        paramsButton.y = 0;
        windowManager.addView(overlayedButton, paramsButton);

        /**
         * Definir parametros de layout para a view que indica qual a origem da coordenada
         * do dispositivo.
         **/
        WindowManager.LayoutParams paramsView = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT
                , WindowManager.LayoutParams.WRAP_CONTENT
                , WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
                , WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                , PixelFormat.TRANSLUCENT);
        viewOriginCoord = new View(this);
        viewOriginCoord.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        paramsView.gravity = Gravity.START | Gravity.TOP;
        // posicao de origem da View
        paramsView.x        = 0;
        paramsView.y        = 0;
        // dimansao da view
        paramsView.width    = 5;
        paramsView.height   = 5;
        windowManager.addView(viewOriginCoord, paramsView);
        touchListenerOnButton = new ImplDefaultDraggable2(overlayedButton, viewOriginCoord, windowManager);
        overlayedButton.setOnClickListener(clickListenerOnButton);
        overlayedButton.setOnTouchListener(touchListenerOnButton);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(overlayedButton != null) {
            windowManager.removeView(overlayedButton);
            windowManager.removeView(viewOriginCoord);
            overlayedButton = null;
            viewOriginCoord = null;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return  null;
    }
}
