package xplorer.drawingpoints.activity.test;


import android.os.Handler;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import xplorer.drawingpoints.activity.views.Dot2DView;

public class AutomaticDrawPointsOnView {

    private Dot2DView mCanvas;

    private DrawDots drawDots;

    public  class DrawDots implements Runnable {
        private boolean isRunning;

        private Handler handler;
        private long interval;

        DrawDots(long interval) {
            this.handler    = new Handler();
            this.interval   = interval;
        }

        void interrupt() {
            this.isRunning = false;
            this.handler.removeCallbacks(this);
        }

        private void start() {
            this.isRunning  = true;
        }

        @Override
        public void run() {
            if (isRunning) {
                mCanvas.drawSquare();
                handler.postDelayed(this, interval);
            }
        }
    }


    public AutomaticDrawPointsOnView(Dot2DView mCanvas, long interval) {
        this.mCanvas = mCanvas;
        this.drawDots = new DrawDots(interval);
    }

    public void start() {
        drawDots.start();
        new Handler().post(drawDots);
    }

    public void stop() {
        this.drawDots.interrupt();
    }
}
