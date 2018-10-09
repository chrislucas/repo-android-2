package xplorer.drawingpoints.activity.helpers;

import android.util.ArrayMap;
import android.view.MotionEvent;

public class MapMotionEvents {

    public static ArrayMap<Integer, String> EVENTS = new ArrayMap<>();

    static {
        EVENTS.put(MotionEvent.ACTION_CANCEL, "MotionEvent.ACTION_CANCEL");
        EVENTS.put(MotionEvent.ACTION_DOWN, "MotionEvent.ACTION_DOWN");
        EVENTS.put(MotionEvent.ACTION_UP, "MotionEvent.ACTION_UP");
        EVENTS.put(MotionEvent.ACTION_MOVE, "MotionEvent.ACTION_MOVE");
        EVENTS.put(MotionEvent.ACTION_HOVER_MOVE, "MotionEvent.ACTION_HOVER_MOVE");
    }
}
