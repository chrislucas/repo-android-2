package xplore.com.br.xplorerscreenoverlay.listeners;


import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by r028367 on 22/01/2018.
 */

public class ImplDefaultDraggable2 implements View.OnTouchListener {
    private int [] outLocationDraggableView = new int[2];
    private int [] outLocationOriginView = new int[2];

    private float xTouch, yTouch, offsetX, offsetY;

    private View draggableView, originView;
    private WindowManager windowManager;

    private boolean moving;

    public ImplDefaultDraggable2(View draggableView, View originView, WindowManager windowManager) {
        this.draggableView = draggableView;
        this.originView = originView;
        this.windowManager = windowManager;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int actionMaskedEvent = event.getActionMasked();
        xTouch = event.getX();
        yTouch = event.getY();
        Log.i("ON_TOUCH", String.format("(%f, %f)", xTouch, yTouch));
        switch (actionMaskedEvent) {
            case MotionEvent.ACTION_DOWN:
                draggableView.getLocationOnScreen(outLocationDraggableView);
                // diferenca entre o toque em X e a posicao do botao na tela
                offsetX = outLocationDraggableView[0] - xTouch;
                // idem Y
                offsetY = outLocationDraggableView[1] - yTouch;
                moving = false;
                break;

            case MotionEvent.ACTION_MOVE:
                originView.getLocationOnScreen(outLocationOriginView);
                WindowManager.LayoutParams draggableViewParams = (WindowManager.LayoutParams) draggableView.getLayoutParams();
                int newX = (int)(offsetX + xTouch);
                int newY = (int)(offsetY + yTouch);
                int diffX = Math.abs(newX - outLocationDraggableView[0]);
                int diffY = Math.abs(newY - outLocationDraggableView[1]);
                Log.i("ACTION_MOVE", String.format(
                        "Old LayoutParamsDrag - XY (%d, %d).\nNew LayoutParamsDrag - XY (%d, %d).\nOld Location - XY (%d, %d)\nDiffXY(%d, %d)"
                        , draggableViewParams.x
                        , draggableViewParams.y
                        , newX
                        , newY
                        , outLocationDraggableView[0]
                        , outLocationDraggableView[1]
                        , diffX
                        , diffY
                    )
                );
                moving = diffX > 0 && diffY > 0;
                draggableViewParams.x = newX - outLocationOriginView[0];
                draggableViewParams.y = newY - outLocationOriginView[1];
                windowManager.updateViewLayout(draggableView, draggableViewParams);
                break;

            case MotionEvent.ACTION_UP:
                //log("ACTION_UP");
                moving = false;
                break;

            case MotionEvent.ACTION_OUTSIDE:
                moving = false;
                break;
        }
        return moving;
    }

    private void log(String tag) {
        Log.i(tag, String.format(
                        "DraggableView Location (%d %d)." +
                        "\nOrigin Location(%d %d)."
                , outLocationDraggableView[0]
                , outLocationDraggableView[1]
                , outLocationOriginView[0]
                , outLocationOriginView[1]
            )
        );
    }

}
