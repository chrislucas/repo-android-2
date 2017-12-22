package br.com.telecom.xplore.xplorecustomview.background;

import android.util.Log;

/**
 * Created by r028367 on 22/12/2017.
 */

public class InfiniteThreadUpdateSurfaceView extends Thread {

    private boolean isRunning;
    private long sleepThread = 0;

    public interface UpdateSurfaceView {
        void update();
    }

    private UpdateSurfaceView updateSurfaceView;

    public InfiniteThreadUpdateSurfaceView(UpdateSurfaceView updateSurfaceView) {
        this.updateSurfaceView = updateSurfaceView;
    }

    public InfiniteThreadUpdateSurfaceView(UpdateSurfaceView updateSurfaceView, long sleepThread) {
        this(updateSurfaceView);
        this.sleepThread = sleepThread;
    }

    @Override
    public void run() {
        while(isRunning) {
            if(sleepThread > 0) {
                try {
                    sleep(sleepThread);
                } catch (InterruptedException e) {
                    Log.e("UPDATE_SURFACE", e.getMessage());
                }
            }
            updateSurfaceView.update();
        }
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public void setSleepThread(long sleepThread) {
        this.sleepThread = sleepThread;
    }
}
