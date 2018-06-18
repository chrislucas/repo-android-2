package br.com.telecom.xplore.xplorecustomview.background;

import android.util.Log;

/**
 * Created by r028367 on 22/12/2017.
 */

public class UpdateSurfaceViewOnBackground extends Thread {

    private boolean isRunning;
    private long sleepThread;

    public interface UpdateSurfaceView {
        void update();
    }

    private UpdateSurfaceView updateSurfaceView;

    public UpdateSurfaceViewOnBackground(UpdateSurfaceView updateSurfaceView) {
        this.updateSurfaceView = updateSurfaceView;
    }

    public UpdateSurfaceViewOnBackground(UpdateSurfaceView updateSurfaceView, long sleepThread) {
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
