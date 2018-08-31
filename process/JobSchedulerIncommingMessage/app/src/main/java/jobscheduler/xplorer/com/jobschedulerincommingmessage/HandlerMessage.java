package jobscheduler.xplorer.com.jobschedulerincommingmessage;

import android.os.Handler;
import android.os.Message;

public class HandlerMessage extends Handler {

    private DelegateMessage delegateMessage;

    public HandlerMessage(DelegateMessage delegateMessage) {
        super();
        this.delegateMessage = delegateMessage;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        this.delegateMessage.processMessage(msg);
    }
}
