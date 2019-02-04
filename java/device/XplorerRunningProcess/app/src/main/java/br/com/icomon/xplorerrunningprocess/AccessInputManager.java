package br.com.icomon.xplorerrunningprocess;

import android.content.Context;
import android.hardware.input.InputManager;
import android.os.Handler;
import android.os.Message;

/**
 * Created by r028367 on 22/03/2018.
 */

public class AccessInputManager {


    public static InputManager get(Context context) {
        return (InputManager) context.getSystemService(Context.INPUT_SERVICE);
    }


    private static class MyHandler extends Handler {
        public MyHandler() {
            super();
        }

        public MyHandler(Callback callback) {
            super(callback);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }

        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
        }

        @Override
        public String getMessageName(Message message) {
            return super.getMessageName(message);
        }

        @Override
        public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
            return super.sendMessageAtTime(msg, uptimeMillis);
        }
    }

    public static void t(Context context) {
        InputManager inputManager = get(context);
        inputManager.registerInputDeviceListener(new InputManager.InputDeviceListener() {
            @Override
            public void onInputDeviceAdded(int deviceId) {

            }

            @Override
            public void onInputDeviceRemoved(int deviceId) {

            }

            @Override
            public void onInputDeviceChanged(int deviceId) {

            }
        }, new MyHandler()
        );
    }

}
