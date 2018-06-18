package br.com.icomon.xplorerresultreceiver.resultreceiver;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.ResultReceiver;

/**
 * Created by r028367 on 29/03/2018.
 *
 * Componente que permite que uma Service se comunique com outros componentes
 * da aplicacação android, por exemplo com uma Activity que iniciou um serviço
 * em Background e está aguardando o resultado do serviço.
 *
 */

public class MyResultReceiver extends ResultReceiver {

    private Handler handler;

    public static final int MESSAGE_WHAT = 0x13;

    public MyResultReceiver(Handler handler) {
        super(handler);
        this.handler = handler;
    }

    @Override
    public void send(int resultCode, Bundle resultData) {
        super.send(resultCode, resultData);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        super.onReceiveResult(resultCode, resultData);
        Message message = new Message();
        message.setData(resultData);
        message.what = MESSAGE_WHAT;
        this.handler.sendMessage(message);
    }
}
