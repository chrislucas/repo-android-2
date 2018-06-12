package br.com.xplorer.utilsgeolocation.utils;

/**
 * Created by r028367 on 20/03/2018.
 */

public class ConfigLocationRequest {
    public static final long DEFAULT_INTERVAL_SET_LOCATION = 30 * 1000;
    public static final long DEFAULT_FAST_INTERVAL_SET_LOCATION = 3 * 1000;

    private long interval;
    private long fastInterval;
    private int priority;

    public ConfigLocationRequest(long interval, long fastInterval, @LocationRequestPriority int priority) {
        this.interval = interval;
        this.fastInterval = fastInterval;
        this.priority = priority;
    }

    public long getInterval() {
        return interval;
    }

    public long getFastInterval() {
        return fastInterval;
    }

    public int getPriority() {
        return priority;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public void setFastInterval(long fastInterval) {
        this.fastInterval = fastInterval;
    }

    public void setPriority(@LocationRequestPriority int priority) {
        this.priority = priority;
    }
}
