package xplore.com.br.xplorerscreenoverlay.services;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import xplore.com.br.xplorerscreenoverlay.R;

public class OverlayService extends Service {

    private View viewOriginCoord;
    private Button overlayedButton;
    private WindowManager windowManager;

    private int offsetX, offsetY, originX, originY;
    private boolean moving;

    public OverlayService() {}

    private final View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            boolean answer = true;
            float x = event.getRawX();
            float y = event.getRawY();
            int eventActionMasked = event.getActionMasked();
            switch (eventActionMasked) {
                case MotionEvent.ACTION_DOWN:
                    moving = false;
                    int location [] = new int[2];
                    overlayedButton.getLocationOnScreen(location);
                    originX = location[0];
                    originY = location[1];
                    offsetX = (int)(originX - x);
                    offsetY = (int)(originY - y);
                    Log.i("MOTION_EVENT_DOWN"
                            , String.format("Posicao inicial: (%d %d)"
                                    , originX, originY));
                    Log.i("MOTION_EVENT_DOWN"
                            , String.format("Tela tocada em: (%f, %f).\nDeslocamento da posicao de origem (%d, %d)"
                                    , x, y, offsetX, offsetY));
                    break;
                case MotionEvent.ACTION_MOVE:
                    int [] viewLocationOnScreen = new int[2];
                    viewOriginCoord.getLocationOnScreen(viewLocationOnScreen);
                    // Parametros do layout do Botao que esta sobreposto na tela
                    WindowManager.LayoutParams paramsButton = (WindowManager.LayoutParams) overlayedButton.getLayoutParams();
                    int newX  = (int) (offsetX + x);
                    int newY  = (int) (offsetY + y);
                    int diffX = newX - originX < 0 ? -(newX - originX) : newX - originX;
                    int diffY = newY - originY < 0 ? -(newY - originY) : newY - originY;
                    // se o sensor detectou movimento porem nao ha deslocamento nas coordenadas X ou Y, entao nao ocorreu movimento
                    moving = !(diffX < 1 && diffY < 1 && !moving);
                    Log.i("MOTION_EVENT_MOVE"
                            , String.format("Localizacao da viewOriginCoord :(%d, %d).\nDiferenca: (%d, %d).\nMovimentado ? %s"
                                    , viewLocationOnScreen[0], viewLocationOnScreen[1], diffX, diffY, moving));
                    Log.i("MOTION_EVENT_MOVE"
                            , String.format("Tela tocada em: (%f, %f).\nNova posicao: (%d, %d)"
                                    , x, y, newX, newY));
                    paramsButton.x = newX - viewLocationOnScreen[0];
                    paramsButton.y = newY - viewLocationOnScreen[1];
                    windowManager.updateViewLayout(overlayedButton, paramsButton);
                    break;
                case MotionEvent.ACTION_UP:
                    answer = moving;
                    break;
                case MotionEvent.ACTION_OUTSIDE:
                    Log.i("TOUCH_OUTSIDE", v.toString());
                    break;
            }
            return answer;
        }
    };

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
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
        overlayedButton.setOnClickListener(onClickListener);
        overlayedButton.setOnTouchListener(onTouchListener);
        overlayedButton.setPadding(20,20,20,20);
        overlayedButton.setText(getString(R.string.button_overlayed_screen));
        overlayedButton.setTextColor(ContextCompat.getColor(this, R.color.white));
        overlayedButton.setBackgroundColor(ContextCompat.getColor(this, R.color.viewOverlay));
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

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
                , WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                , PixelFormat.TRANSLUCENT);
/*
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
         * */
        WindowManager.LayoutParams paramsView = new WindowManager
                .LayoutParams(
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
        paramsView.width    = 0;
        paramsView.height   = 0;
        windowManager.addView(viewOriginCoord, paramsView);
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
