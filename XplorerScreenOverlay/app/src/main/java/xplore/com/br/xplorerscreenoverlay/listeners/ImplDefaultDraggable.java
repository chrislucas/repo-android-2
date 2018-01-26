package xplore.com.br.xplorerscreenoverlay.listeners;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by r028367 on 22/01/2018.
 */

public class ImplDefaultDraggable implements View.OnTouchListener {

    private int offsetX, offsetY, originX, originY;
    private boolean moving;
    private View overlayButton, viewOriginCoordinate;
    private WindowManager windowManager;

    public ImplDefaultDraggable(View overlayButton, View viewOriginCoordinate, WindowManager windowManage) {
        this.overlayButton = overlayButton;
        this.viewOriginCoordinate = viewOriginCoordinate;
        this.windowManager = windowManage;
    }

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
                overlayButton.getLocationOnScreen(location);
                originX = location[0];
                originY = location[1];
                /**
                 *
                 * */
                offsetX = (int)(originX-x);
                offsetY = (int)(originY-y);
                Log.i("MOTION_EVENT_DOWN"
                        , String.format("Origem do botao: (%d,%d)"
                                , originX, originY));
                Log.i("MOTION_EVENT_DOWN", String.format("Toque no botao em: (%f,%f)." +
                        "\nDiferença entre Toque/Origem(%d, %d)", x, y, offsetX, offsetY));
                break;
            case MotionEvent.ACTION_MOVE:
                int [] viewLocationOnScreen = new int[2];
                viewOriginCoordinate.getLocationOnScreen(viewLocationOnScreen);
                // Parametros do layout do Botao que esta sobreposto na tela
                WindowManager.LayoutParams paramsButton = (WindowManager.LayoutParams) overlayButton.getLayoutParams();
                int newX  = (int) (offsetX + x);
                int newY  = (int) (offsetY + y);
                int diffX = Math.abs(newX - originX);
                int diffY = Math.abs(newY - originY);
                // se o sensor detectou movimento porem nao ha deslocamento nas coordenadas X ou Y, entao nao ocorreu movimento
                moving = !(diffX < 1 && diffY < 1 && ! moving);
                StringBuilder sb = new StringBuilder();
                    sb.append("1) Toque no botao em: (%f, %f).\n");
                    sb.append("Origem: (%d, %d).\n");
                    sb.append("Deslocamento: (%d, %d).\n");
                    sb.append("Deslocamento + Toque: (%d, %d).\n");
                    sb.append("Deslocamento + Toque - origem: (%d %d).\n");
                    Log.i("MOTION_EVENT_MOVE"
                        , String.format(sb.toString()
                                , x
                                , y
                                , originX
                                , originY
                                , offsetX
                                , offsetY
                                , newX
                                , newY
                                , newX - viewLocationOnScreen[0]
                                , newY - viewLocationOnScreen[1])
                    );
                    /*
                sb = new StringBuilder();
                sb.append("1) Toque no botao em: (%f, %f).\n");
                sb.append("Origem: (%d, %d).\n");
                Log.i("MOTION_EVENT_MOVE"
                        , String.format(sb.toString()
                                , x
                                , y
                                , originX
                                , originY
                        )
                );
                */
                    /*
                    sb = new StringBuilder();
                    sb.append("2) ViewOriginCoord :(%d, %d).\n");
                    sb.append("Diferenca: (%d, %d).\n");
                    sb.append("Movimentou-se ? %s.\n");
                    Log.i("MOTION_EVENT_MOVE"
                        , String.format(sb.toString()
                                , viewLocationOnScreen[0]
                                , viewLocationOnScreen[1]
                                , diffX
                                , diffY
                                , moving)
                    );
                    */
                paramsButton.x = newX - viewLocationOnScreen[0];
                paramsButton.y = newY - viewLocationOnScreen[1];
                windowManager.updateViewLayout(overlayButton, paramsButton);
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
}
