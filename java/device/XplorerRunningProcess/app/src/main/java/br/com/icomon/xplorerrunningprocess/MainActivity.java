package br.com.icomon.xplorerrunningprocess;

import android.app.Instrumentation;
import android.os.SystemClock;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String [] commands = {
        "adb shell ls"
        ,"su -c adb shell ls -R"
        ,"input tap 100 100"
        ,"ls ./"
        ,"su -c ls -ra /system"
        ,"adb shell ls -Ra"
        ,"adb shell ps"
        ,"adb shell pwd"
        ,"adb shell ls -s"
        ,"adb shell ls -a"
        ,"adb shell ls -n"
        ,"adb shell ls -i"
        ,"adb shell dumpstate"
        ,"adb shell am start -a\n android.intent.action.VIEW -d"
        ,"adb shell am start -t image/* -a android.intent.action.VIEW"
    };

    private static final String ADB_SHELL = "adb shell ps";

    private EditText textViewCommand;
    private TextView textViewResult;
    private Button buttonTapTest, buttonTestCommand;
    private String defaultCommand;

    private Instrumentation instrumentation;


    @Override
    public void onClick(View v) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewCommand = findViewById(R.id.text_view_command);
        textViewResult = findViewById(R.id.text_view_result_process);
        buttonTapTest = findViewById(R.id.button_sample_tap);
        buttonTestCommand = findViewById(R.id.button_exec_command);
        instrumentation = new Instrumentation();
    }


    @Override
    protected void onResume() {
        super.onResume();
        buttonTapTest.post(new Runnable() {
            @Override
            public void run() {
                Input.main(new String[] {"touchscreen", "tap"
                        , String.valueOf(buttonTapTest.getX()), String.valueOf(buttonTapTest.getY())});
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void executeCommand(View view) {
        String strCommand = textViewCommand.getText().toString();
        if(strCommand.isEmpty()) {
            textViewCommand.setError("Caixa de comando vazia");
            return;
        }
        /**
         * Criar o processo que executara o comando
         * */
        executeCommand(strCommand);
    }

    public void tap(View view) {
        Toast.makeText(this, "TAP", Toast.LENGTH_LONG).show();
    }

    private void runTapCommand(int px, int py) {
        String command =  String.format(Locale.getDefault()
                ,"/system/bin/input tap %d %d\n"
                , px
                , py
        );
        Log.i("RUN_TAP_COMMAND", command);
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("su");
            DataOutputStream os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(command);
            os.writeBytes("exit\n");
            os.flush();
            os.close();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            printException(e);
        }
    }

    private void runTapCommandWithInstrumentation(int px, int py) {
        long uptTimeMillis = SystemClock.uptimeMillis();
        instrumentation.sendKeyDownUpSync(KeyEvent.KEYCODE_A);
        MotionEvent.obtain(uptTimeMillis, uptTimeMillis, MotionEvent.ACTION_DOWN, px, py, 0);
        MotionEvent.obtain(uptTimeMillis, uptTimeMillis, MotionEvent.ACTION_UP, px, py, 0);
    }

    private void runSu() {
        try {
            Process su = new ProcessBuilder().command("su").redirectErrorStream(true).start();
            OutputStream out =  su.getOutputStream();
        } catch (IOException e) {
            printException(e);
        }
    }

    private void executeCommand(String command) {
        StringBuilder sb = new StringBuilder();
        Log.i("INPUT_COMMAND", command);
        runSu();
        boolean fail = false;
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Map<String, String> environment  = processBuilder.environment();
            if(environment != null) {
                for (Map.Entry<String, String> entry : environment.entrySet()) {
                    Log.i("ENVIRONMENT", String.format("%s, %s", entry.getKey(), entry.getValue()));
                }
            }
            processBuilder.start();
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            BufferedReader reader = getBufferedReader(process.getInputStream());
            String in = null;
            while ( (in = reader.readLine()) != null) {
                Log.i("READ_LINE_PROCESS", in);
                sb.append(in).append("\n");
            }
        } catch (IOException | InterruptedException ioex) {
            printException(ioex);
            sb.append(ioex.getMessage() == null ? "Não foi possível recuperar a exceção" : ioex.getMessage());
            fail = true;
        }
        finally {
            if(fail)
                textViewResult.setTextColor(ContextCompat.getColor(this, R.color.red));
            String result = sb.toString();
            textViewResult.setText(result.isEmpty()
                    ? String.format("Nenhum resultado foi obtido executando o comando '%s'"
                    , command) : result);
        }
    }


    private BufferedReader getBufferedReader(InputStream in) {
       return new BufferedReader(new InputStreamReader(in));
    }

    private void printException(Exception ioex) {
        String message = ioex.getMessage() == null ? "Não foi possível recuperar a exceção" : ioex.getMessage();
        Log.e("RUNTIME_IO_EXCP", message);
    }

    private void startProcessTest() {
        try {
            Process process = Runtime.getRuntime().exec(commands[3]);
            BufferedReader reader = getBufferedReader(process.getInputStream());
            String in = null;
            while ( (in = reader.readLine()) != null) {
                Log.i("READ_LINE_PROCESS", in);
            }
        } catch (IOException e) {
            printException(e);
        }
    }

    private void startAdbShell() {
        try {
            Process process = (new ProcessBuilder(ADB_SHELL)).start();
            BufferedReader reader = getBufferedReader(process.getInputStream());
            String in = null;
            while ( (in = reader.readLine()) != null) {
                Log.i("READ_LINE_PROCESS", in);
            }
        } catch (IOException e) {
            printException(e);
        }
    }


}
