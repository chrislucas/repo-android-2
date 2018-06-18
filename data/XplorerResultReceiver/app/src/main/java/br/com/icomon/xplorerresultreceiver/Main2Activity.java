package br.com.icomon.xplorerresultreceiver;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import br.com.icomon.xplorerresultreceiver.handlers.MyHandler;
import br.com.icomon.xplorerresultreceiver.resultreceiver.MyResultReceiver;
import br.com.icomon.xplorerresultreceiver.service.MyIntentService;

public class Main2Activity extends AppCompatActivity implements MyHandler.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = new Intent(this, MyIntentService.class);

        MyResultReceiver resultReceiver = new MyResultReceiver(new MyHandler(this));
        Bundle bundle = new Bundle();
        bundle.putParcelable(MyIntentService.BUNDLE_RESULT_RECEIVER, resultReceiver);
        intent.putExtras(bundle);

        startService(intent);
    }

    @Override
    public void doSomething(Message message) {
        Toast.makeText(this, "Mensagem Produzida após ...", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }
}
