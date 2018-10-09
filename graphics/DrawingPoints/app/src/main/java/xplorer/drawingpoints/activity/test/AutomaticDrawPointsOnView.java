package xplorer.drawingpoints.activity.test;


import android.os.Handler;
import android.util.Log;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


import xplorer.drawingpoints.activity.views.Dot2DView;

public class AutomaticDrawPointsOnView {

    private Dot2DView mCanvas;

    private DrawDots drawDots;

    public  class DrawDots implements Runnable {
        private boolean isRunning;

        DrawDots() { this.isRunning = true; }

        void interrupt() { isRunning = false; }

        @Override
        public void run() {
            while (isRunning) {
                mCanvas.drawDot();
            }
        }
    }

    private Handler handler;

    public AutomaticDrawPointsOnView(Dot2DView mCanvas) {
        this.mCanvas = mCanvas;
    }

    public void start(long interval, TimeUnit timeUnit) {
        drawDots = new DrawDots();
        new Handler().postDelayed(drawDots, interval);
    }

    public void stop() {
    }
}
